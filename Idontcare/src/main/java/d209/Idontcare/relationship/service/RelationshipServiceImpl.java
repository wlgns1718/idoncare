package d209.Idontcare.relationship.service;

import d209.Idontcare.User;
import d209.Idontcare.UserService;
import d209.Idontcare.common.exception.*;
import d209.Idontcare.relationship.dto.req.RequestRelationshipReqDto;
import d209.Idontcare.relationship.dto.res.ReceivedRequestResDto;
import d209.Idontcare.relationship.entity.RelationshipRequest;
import d209.Idontcare.relationship.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Tuple;
import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class RelationshipServiceImpl implements RelationshipService{
  
  private final UserService userService;
  private final RelationshipRepository relationshipRepository;
  private final RelationshipRequestRepository relationshipRequestRepository;
  
  @Override
  public RelationshipRequest requestRelationship(User parent, RequestRelationshipReqDto req){
    
    if(parent.getType() != User.Type.PARENT){
      throw new MustParentException();
    }
    
    User child = null;
    try{
      child = userService.findByPhoneNumber(req.getChildPhoneNumber());
    } catch(NoSuchUserException e) {
      throw new NoSuchUserException("해당 자녀를 찾을 수 없습니다");
    }
    
    if(child.getType() != User.Type.CHILD) throw new MustChildException("자녀에게만 요청할 수 있습니다");
    
    relationshipRepository.findOneByParentAndChild(parent.getUserId(), child.getUserId()).ifPresent((r) -> {throw new DuplicatedException("이미 자식입니다"); });
    relationshipRequestRepository.findOneByParentAndChild(parent.getUserId(), child.getUserId()).ifPresent((r) -> {throw new DuplicatedException("이미 요청되었습니다"); });
    
    RelationshipRequest relationshipRequest = RelationshipRequest.builder()
                                  .parent(parent)
                                  .child(child)
                                  .build();
    
    RelationshipRequest saved = relationshipRequestRepository.save(relationshipRequest);
    
    return saved;
  }
  
  @Override
  @Transactional(readOnly=true)
  public List<ReceivedRequestResDto> getReceivedRequestList(User child) throws MustChildException {
    if(child.getType() != User.Type.CHILD) throw new MustChildException();
    
    List<Tuple> requests = relationshipRequestRepository.findAllByChild(child);
    
    return requests.stream().map(ReceivedRequestResDto::new).toList();
  }
}
