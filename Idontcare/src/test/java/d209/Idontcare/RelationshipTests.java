package d209.Idontcare;

import d209.Idontcare.common.exception.*;
import d209.Idontcare.relationship.dto.req.ProcessReceivedRequestReqDto;
import d209.Idontcare.relationship.dto.req.RequestRelationshipReqDto;
import d209.Idontcare.relationship.dto.res.ReceivedRequestResDto;
import d209.Idontcare.relationship.dto.res.RelationshipResDto;
import d209.Idontcare.relationship.entity.RelationshipRequest;
import d209.Idontcare.relationship.service.RelationshipService;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class RelationshipTests {
  
  @Autowired
  private RelationshipService relationshipService;
  
  @Autowired
  private TUserRepository TUserRepository;
  
  void connectParentChild(TUser parent, TUser child){
    /* 관계 맺기 */
    String phoneNumber = child.getPhoneNumber();
    RequestRelationshipReqDto req = RequestRelationshipReqDto.builder()
        .childPhoneNumber(phoneNumber)
        .build();
    
    RelationshipRequest request = relationshipService.requestRelationship(parent, req);
    
    ProcessReceivedRequestReqDto accept = ProcessReceivedRequestReqDto.builder()
            .process(ProcessReceivedRequestReqDto.Type.ACCEPT)
            .relationRequestId(request.getRelationshipRequestId())
            .build();
    relationshipService.processReceivedRequest(child, accept);
  }
  
  @Test
  @DisplayName("부모 -> 아이 관계 리퀘스트 테스트")
  void requestRelationship(){
    TUser parent = TUserRepository.findAll().stream().filter((u) -> u.getType() == TUser.Type.PARENT).toList().get(0);
    TUser child = TUserRepository.findAll().stream().filter((u) -> u.getType() == TUser.Type.CHILD).toList().get(0);
    
    String phoneNumber = child.getPhoneNumber();
    
    RequestRelationshipReqDto req = RequestRelationshipReqDto.builder()
        .childPhoneNumber(phoneNumber)
        .build();

    /* 정상적으로 등록 */
    RelationshipRequest saved = relationshipService.requestRelationship(parent, req);
    assert saved.getRelationshipRequestId().equals(1L);
    
    /* 중복 등록 */
    RequestRelationshipReqDto finalReq = req;
    Assertions.assertThrows(DuplicatedException.class, () -> relationshipService.requestRelationship(parent, finalReq));
    
    req = RequestRelationshipReqDto.builder()
        .childPhoneNumber("01011112222")
        .build();
    
    /* 없는 자식에 대한 처리 */
    RequestRelationshipReqDto finalReq1 = req;
    Assertions.assertThrows(NoSuchUserException.class, () -> relationshipService.requestRelationship(parent, finalReq1));
  }
  
  @Test
  @DisplayName("아이가 요청받은 목록 조회 테스트")
  void requestListTest(){
    TUser parent = TUserRepository.findAll().stream().filter((u) -> u.getType() == TUser.Type.PARENT).toList().get(0);
    TUser child = TUserRepository.findAll().stream().filter((u) -> u.getType() == TUser.Type.CHILD).toList().get(0);

    /* 새로운 부모 생성 */
    TUser newParent = TUser.builder()
        .phoneNumber("01011112222")
        .name("아빠지훈")
        .type(TUser.Type.PARENT)
        .build();
    newParent.setUUID();

    TUserRepository.save(newParent);

//    /* 관계 맺기 */
    String phoneNumber = child.getPhoneNumber();
    RequestRelationshipReqDto req = RequestRelationshipReqDto.builder()
        .childPhoneNumber(phoneNumber)
        .build();


    /* 엄마의 등록 */
    RelationshipRequest saved = relationshipService.requestRelationship(parent, req);
    assert saved.getRelationshipRequestId().equals(1L);

    /* 아빠의 등록 */
    RelationshipRequest saved2 = relationshipService.requestRelationship(newParent, req);
    assert saved2.getRelationshipRequestId().equals(2L);
    
    List<ReceivedRequestResDto> requests = relationshipService.getReceivedRequestList(child);
    assert requests.size() == 2;
  }
  
  @Test
  @DisplayName("아이가 요청받은 목록 조회 테스트")
  void processRequestTest(){
    TUser parent = TUserRepository.findAll().stream().filter((u) -> u.getType() == TUser.Type.PARENT).toList().get(0);
    TUser child = TUserRepository.findAll().stream().filter((u) -> u.getType() == TUser.Type.CHILD).toList().get(0);
    
    /* 관계 맺기 */
    String phoneNumber = child.getPhoneNumber();
    RequestRelationshipReqDto req = RequestRelationshipReqDto.builder()
        .childPhoneNumber(phoneNumber)
        .build();
    
    RelationshipRequest saved = relationshipService.requestRelationship(parent, req);
    assert saved.getRelationshipRequestId().equals(1L);
    
    /* 요청에 대해 ACCEPT 성공 처리 */
    ProcessReceivedRequestReqDto processReq = ProcessReceivedRequestReqDto.builder()
                                        .process(ProcessReceivedRequestReqDto.Type.ACCEPT)
                                        .relationRequestId(saved.getRelationshipRequestId())
                                        .build();
    try{
      relationshipService.processReceivedRequest(child, processReq);
    } catch(CommonException e){
      Assertions.fail(e.getMessage());
    }
    
    /* 성공적으로 관계가 맺어졌는지 확인 */
    List<RelationshipResDto> listByChild = relationshipService.getRelationshipList(child);
    assert listByChild.size() == 1;
    
    List<RelationshipResDto> listByParent = relationshipService.getRelationshipList(parent);
    assert listByParent.size() == 1;
    
    System.out.println(listByChild);
    System.out.println(listByParent);
    
    TUser newParent = TUser.builder()
        .name("아빠지훈")
        .type(TUser.Type.PARENT)
        .phoneNumber("01015932475")
        .build();
    newParent.setUUID();
    
    TUserRepository.save(newParent);
    
    /* 새롭게 관계를 맺고 확인 */
    connectParentChild(newParent, child);
    listByChild = relationshipService.getRelationshipList(child);
    assert listByChild.size() == 2;
    
    listByParent = relationshipService.getRelationshipList(parent);
    assert listByParent.size() == 1;
    assert listByChild.get(0).getRelationshipId().equals(listByParent.get(0).getRelationshipId());
    
    listByParent = relationshipService.getRelationshipList(newParent);
    assert listByParent.size() == 1;
    assert listByChild.get(1).getRelationshipId().equals(listByParent.get(0).getRelationshipId());
    
  }
}
