package d209.Idontcare;

import d209.Idontcare.common.APIBuilder;
import d209.Idontcare.pocketmoney.service.PocketMoneyService;
import d209.Idontcare.relationship.dto.req.ProcessReceivedRequestReqDto;
import d209.Idontcare.relationship.dto.req.RequestRelationshipReqDto;
import d209.Idontcare.relationship.entity.RelationshipRequest;
import d209.Idontcare.relationship.service.RelationshipService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;

@SpringBootTest
public class PocketMoneyRequestTests {
  
  @Autowired
  private PocketMoneyService pocketMoneyService;
  
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
  @DisplayName("용돈 조르기 테스트")
  void test(){
    TUser parent = TUserRepository.findAll().stream().filter((u) -> u.getType() == TUser.Type.PARENT).toList().get(0);
    TUser child = TUserRepository.findAll().stream().filter((u) -> u.getType() == TUser.Type.CHILD).toList().get(0);
  
    
  }
}
