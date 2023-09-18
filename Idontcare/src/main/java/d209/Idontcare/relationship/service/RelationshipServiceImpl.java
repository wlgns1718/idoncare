package d209.Idontcare.relationship.service;

import d209.Idontcare.TUser;
import d209.Idontcare.TUserService;
import d209.Idontcare.common.dto.APIResultDto;
import d209.Idontcare.common.exception.*;
import d209.Idontcare.common.service.APIService;
import d209.Idontcare.relationship.dto.req.*;
import d209.Idontcare.relationship.dto.res.*;
import d209.Idontcare.relationship.entity.*;
import d209.Idontcare.relationship.repository.*;
import lombok.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.Tuple;
import java.util.*;

@Transactional
@RequiredArgsConstructor
@Service
public class RelationshipServiceImpl implements RelationshipService{
  
  private final TUserService TUserService;
  private final RelationshipRepository relationshipRepository;
  private final RelationshipRequestRepository relationshipRequestRepository;
  
  @Override
  public RelationshipRequest requestRelationship(TUser parent, RequestRelationshipReqDto req){
    
    if(parent.getType() != TUser.Type.PARENT){
      throw new MustParentException();
    }
    
    TUser child = null;
    try{
      child = TUserService.findByPhoneNumber(req.getChildPhoneNumber());
    } catch(NoSuchUserException e) {
      throw new NoSuchUserException("해당 자녀를 찾을 수 없습니다");
    }
    
    if(child.getType() != TUser.Type.CHILD) throw new MustChildException("자녀에게만 요청할 수 있습니다");
    
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
  public List<ReceivedRequestResDto> getReceivedRequestList(TUser child) {
    if(child.getType() != TUser.Type.CHILD) throw new MustChildException();
    
    List<Tuple> requests = relationshipRequestRepository.findAllByChild(child);
    
    return requests.stream().map(ReceivedRequestResDto::new).toList();
  }
  
  @Override
  public void processReceivedRequest(TUser child, ProcessReceivedRequestReqDto req){
    if(child.getType() != TUser.Type.CHILD) throw new MustChildException();
    
    Long relationRequestId = req.getRelationRequestId();
    ProcessReceivedRequestReqDto.Type type = req.getProcess();
    
    RelationshipRequest request = relationshipRequestRepository.findById(relationRequestId).orElseThrow(() -> new NoSuchContentException("해당 관계 요청을 찾을 수 없습니다"));
    TUser parent = request.getParent();
    TUser savedChild = request.getChild();
    
    if( !savedChild.equals(child) ) throw new AuthorizationException();
    
    relationshipRequestRepository.delete(request);
  
    if(type == ProcessReceivedRequestReqDto.Type.ACCEPT){
      //승낙이면
      Relationship relationship = Relationship.builder()
                                  .parent(parent)
                                  .child(child)
                                  .build();
          
      relationshipRepository.save(relationship);
      
      /* TODO : 승낙되었다는 알람을 주자 */
    }
    else if(type == ProcessReceivedRequestReqDto.Type.REJECT){
      //거절이면
      /* TODO : 거절했다는 알람을 주자 */
    }
  }
  
  @Transactional(readOnly = true)
  @Override
  public List<RelationshipResDto> getRelationshipList(TUser user) throws MustChildException {
    List<RelationshipResDto> list = new LinkedList<>();
    if(user.getType() == TUser.Type.PARENT){
      //부모 이면
      list = relationshipRepository.findAllByParent(user).stream().map(RelationshipResDto::new).toList();
    }
    else if(user.getType() == TUser.Type.CHILD){
      //자식 이면
      list = relationshipRepository.findAllByChild(user).stream().map(RelationshipResDto::new).toList();
    }
    
    return list;
  }
  

}
