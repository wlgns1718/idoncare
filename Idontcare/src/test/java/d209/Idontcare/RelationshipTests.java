package d209.Idontcare;

import d209.Idontcare.common.exception.*;
import d209.Idontcare.relationship.dto.req.RequestRelationshipReqDto;
import d209.Idontcare.relationship.dto.res.ReceivedRequestResDto;
import d209.Idontcare.relationship.entity.RelationshipRequest;
import d209.Idontcare.relationship.service.RelationshipService;
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
  private UserRepository userRepository;
  
  
  @Test
  @DisplayName("부모 -> 아이 관계 리퀘스트 테스트")
  void requestRelationship(){
    User parent = userRepository.findAll().stream().filter((u) -> u.getType() == User.Type.PARENT).toList().get(0);
    User child = userRepository.findAll().stream().filter((u) -> u.getType() == User.Type.CHILD).toList().get(0);
    
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
    User parent = userRepository.findAll().stream().filter((u) -> u.getType() == User.Type.PARENT).toList().get(0);
    User child = userRepository.findAll().stream().filter((u) -> u.getType() == User.Type.CHILD).toList().get(0);

    /* 새로운 부모 생성 */
    User newParent = User.builder()
        .phoneNumber("01011112222")
        .name("아빠지훈")
        .type(User.Type.PARENT)
        .build();
    newParent.setUUID();

    userRepository.save(newParent);

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
    System.out.println(requests);
    assert requests.size() == 2;
  }
}
