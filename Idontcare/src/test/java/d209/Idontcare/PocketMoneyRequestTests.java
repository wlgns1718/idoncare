package d209.Idontcare;

import d209.Idontcare.pocketmoney.dto.req.ProcessPocketMoneyRequestReqDto;
import d209.Idontcare.pocketmoney.dto.req.RequestPocketMoneyReqDto;
import d209.Idontcare.pocketmoney.dto.res.GetPocketMoneyRequestResDto;
import d209.Idontcare.pocketmoney.entity.PocketMoneyRequest;
import d209.Idontcare.pocketmoney.service.PocketMoneyService;
import d209.Idontcare.relationship.dto.req.ProcessReceivedRequestReqDto;
import d209.Idontcare.relationship.dto.req.RequestRelationshipReqDto;
import d209.Idontcare.relationship.entity.RelationshipRequest;
import d209.Idontcare.relationship.service.RelationshipService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
public class PocketMoneyRequestTests {
  
  @Autowired
  private PocketMoneyService pocketMoneyService;
  
  @Autowired
  private RelationshipService relationshipService;
  
  @Autowired
  private TUserRepository tUserRepository;
  
  
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
    TUser parent = tUserRepository.findAll().stream().filter((u) -> u.getType() == TUser.Type.PARENT).toList().get(0);
    TUser child = tUserRepository.findAll().stream().filter((u) -> u.getType() == TUser.Type.CHILD).toList().get(0);

    connectParentChild(parent, child);
    RequestPocketMoneyReqDto req = RequestPocketMoneyReqDto
        .builder()
        .parentUserId(parent.getUserId())
        .content("돈줘")
        .amount(10_000)
        .build();
    
    pocketMoneyService.requestPocketMoney(child, req);
    
    List<GetPocketMoneyRequestResDto> list = pocketMoneyService.getPocketMoneyRequest(parent);
    System.out.println(list);
    assert !list.isEmpty();
    
    GetPocketMoneyRequestResDto request1 = list.get(0);
    
    ProcessPocketMoneyRequestReqDto processReq =  ProcessPocketMoneyRequestReqDto.builder()
                                                    .pocketMoneyRequestId(request1.getPocketMoneyRequestId())
                                                    .type(ProcessPocketMoneyRequestReqDto.Type.ACCEPT)
                                                    .build();
    
    TUser newParent = TUser.builder()
        .phoneNumber("123456789")
        .type(TUser.Type.PARENT)
        .name("아빠지훈")
        .build();
    newParent.setUUID();
    
    tUserRepository.save(newParent);
    
    try{
      pocketMoneyService.processPocketMoneyRequest(newParent, processReq);
      Assertions.fail("오류가 발생해야 한다");
    } catch(Exception e){}
    
    pocketMoneyService.processPocketMoneyRequest(parent, processReq);
    
    list = pocketMoneyService.getPocketMoneyRequest(parent);
    assert list.get(0).getType() != PocketMoneyRequest.Type.REJECT;
    assert list.get(0).getType() == PocketMoneyRequest.Type.ACCEPTED;
    
    
    list = pocketMoneyService.getPocketMoneyRequest(newParent);
    System.out.println(list);
    assert list.isEmpty();
  }
}
