package d209.Idontcare.relationship.service;

import d209.Idontcare.common.dto.APIResultDto;
import d209.Idontcare.common.exception.*;
import d209.Idontcare.common.service.APIService;
import d209.Idontcare.relationship.dto.req.*;
import d209.Idontcare.relationship.dto.res.*;
import d209.Idontcare.relationship.entity.*;
import d209.Idontcare.relationship.repository.*;
import d209.Idontcare.user.entity.Role;
import d209.Idontcare.user.entity.User;
import d209.Idontcare.user.repository.UserRepository;
import d209.Idontcare.user.service.UserService;
import lombok.*;
import org.hibernate.annotations.Parent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.Tuple;
import java.util.*;

@Transactional
@RequiredArgsConstructor
@Service
public class RelationshipServiceImpl implements RelationshipService{

  private final UserService userService;
  
  private final UserRepository userRepository;
  private final RelationshipRepository relationshipRepository;
  private final RelationshipRequestRepository relationshipRequestRepository;
  
  @Override
  public RelationshipRequest requestRelationship(Long parentUserId, RequestRelationshipReqDto req){
    
    System.out.println(1);
    User child = userService.findByPhoneNumber(req.getChildPhoneNumber()).orElseThrow(NoSuchUserException::new);
    System.out.println(2);
    
    if( !child.isChild() ) throw new MustChildException("자녀에게만 요청할 수 있습니다");
    System.out.println(3);
    
    if(relationExistsByParentAndChild(parentUserId, child.getUserId())) throw new DuplicatedException("이미 자식입니다");
    System.out.println(4);
    
    if(relationRequestExistsByParentAndChild(parentUserId, child.getUserId())) throw new DuplicatedException("이미 요청되었습니다");
    System.out.println(5);
    
    User parent = userRepository.getReferenceById(parentUserId);
    System.out.println(6);
    
    RelationshipRequest relationshipRequest = RelationshipRequest.builder()
                                  .parent(parent)
                                  .child(child)
                                  .build();
    System.out.println(7);
    
    RelationshipRequest saved = relationshipRequestRepository.save(relationshipRequest);
    System.out.println(8);
    
    return saved;
  }
  
  @Override
  @Transactional(readOnly=true)
  public List<ReceivedRequestResDto> getReceivedRequestList(Long childUserId) {
    List<Tuple> requests = relationshipRequestRepository.findAllByChild(childUserId);
    
    return requests.stream().map(ReceivedRequestResDto::new).toList();
  }
  
  @Override
  public void processReceivedRequest(Long childUserId, ProcessReceivedRequestReqDto req){
    Long relationRequestId = req.getRelationRequestId();
    ProcessReceivedRequestReqDto.Type type = req.getProcess();
    
    RelationshipRequest request = relationshipRequestRepository.findById(relationRequestId).orElseThrow(() -> new NoSuchContentException("해당 관계 요청을 찾을 수 없습니다"));
    User parent = request.getParent();
    User savedChild = request.getChild();
    
    if( !savedChild.getUserId().equals(childUserId) ) throw new AuthorizationException();
    
    relationshipRequestRepository.delete(request);
  
    if(type == ProcessReceivedRequestReqDto.Type.ACCEPT){
      //승낙이면
      Relationship relationship = Relationship.builder()
                                  .parent(parent)
                                  .child(savedChild)
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
  public List<RelationshipResDto> getRelationshipList(Long userId, Role role) throws MustChildException {
    List<RelationshipResDto> list = new LinkedList<>();
    if(role == Role.PARENT){
      //부모 이면
      list = relationshipRepository.findAllByParent(userId).stream().map(RelationshipResDto::new).toList();
    }
    else if(role == Role.CHILD){
      //자식 이면
      list = relationshipRepository.findAllByChild(userId).stream().map(RelationshipResDto::new).toList();
    }
    
    return list;
  }
  
  @Override
  public boolean relationExistsByParentAndChild(Long parentUserId, Long childUserId) {
    PageRequest pageRequest = PageRequest.of(0, 1);
    Page<Long> page = relationshipRepository.existsByParentAndChild(parentUserId, childUserId, pageRequest);
    return page.getTotalElements() != 0;
  }
  
  private boolean relationRequestExistsByParentAndChild(Long parentUserId, Long childUserId){
    PageRequest pageRequest = PageRequest.of(0, 1);
    Page<Long> page = relationshipRequestRepository.existsByParentAndChild(parentUserId, childUserId, pageRequest);
    return page.getTotalElements() != 0;
  }
}
