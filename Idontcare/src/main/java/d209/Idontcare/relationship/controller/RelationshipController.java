package d209.Idontcare.relationship.controller;

import d209.Idontcare.User;
import d209.Idontcare.UserRepository;
import d209.Idontcare.common.dto.ResponseDto;
import d209.Idontcare.common.exception.*;
import d209.Idontcare.relationship.dto.req.ProcessReceivedRequestResDto;
import d209.Idontcare.relationship.dto.res.ReceivedRequestResDto;
import d209.Idontcare.relationship.dto.req.RequestRelationshipReqDto;
import d209.Idontcare.relationship.dto.res.RequestRelationshipResDto;
import d209.Idontcare.relationship.entity.RelationshipRequest;
import d209.Idontcare.relationship.service.RelationshipService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name="관계관리 API")
@RestController
@RequestMapping("/api/relationship")
@RequiredArgsConstructor
public class RelationshipController {

  private final RelationshipService relationshipService;
  private final UserRepository userRepository;
  
  @PostMapping("/request")
  @Operation(summary="관계 맺기 요청", description="부모가 아이에게 관계 맺기를 요청합니다")
  @ApiResponses(value = {
      @ApiResponse(responseCode="200", description = "성공",
        content=@Content(schema = @Schema(implementation= RequestRelationshipResDto.class))),
      @ApiResponse(responseCode=MustParentException.CODE, description = MustParentException.DESCRIPTION),
      @ApiResponse(responseCode=MustChildException.CODE, description = MustChildException.DESCRIPTION),
      @ApiResponse(responseCode=NoSuchUserException.CODE, description = NoSuchUserException.DESCRIPTION),
      @ApiResponse(responseCode=DuplicatedException.CODE, description = "이미 리퀘스트 한 경우"),
  })
  public ResponseDto<?> requestRelationship(@Valid @RequestBody RequestRelationshipReqDto req){
    /* TODO : 요청한 사람에 대해 검증 코드 추가 필요 */
    User parent = userRepository.findAll().stream().filter((u) -> u.getType() == User.Type.PARENT).toList().get(0);
    
    if(parent == null)  //로그인 되지 않은 경우
      return ResponseDto.fail(new AuthenticationException());
    
    try{
      RelationshipRequest saved = relationshipService.requestRelationship(parent, req);
      RequestRelationshipResDto result = RequestRelationshipResDto.builder()
                                          .requestRelationshipId(saved.getRelationshipRequestId())
                                          .build();

      return ResponseDto.success(result);
    } catch(CommonException e){
      return ResponseDto.fail(e);
    }
  }
  
  @GetMapping("/child/request")
  @Operation(summary="관계 요청 받은 리스트", description="아이가 부모로부터 받은 요청을 볼 수 있습니다")
  @ApiResponses(value = {
      @ApiResponse(responseCode="200", description = "성공",
          content=@Content(schema = @Schema(implementation= ReceivedRequestResDto.Result.class))),
      @ApiResponse(responseCode=MustChildException.CODE, description = MustChildException.DESCRIPTION),
  })
  public ResponseDto<?> getReceivedRequestList(){
  
    /* TODO : 로그인 된 사람 받아오기 필요 */
    User child = userRepository.findAll().stream().filter((u) -> u.getType() == User.Type.CHILD).toList().get(0);
    
    try{
      List<ReceivedRequestResDto> requests = relationshipService.getReceivedRequestList(child);
      return ResponseDto.success(new ReceivedRequestResDto.Result(requests));
    }catch(CommonException e){
     return ResponseDto.fail(e);
    }
  }
  
  @PostMapping("/child/request")
  @Operation(summary="요청에 대해 수락 처리", description="아이가 부모로부터 받은 요청에 대해 처리")
  public ResponseDto<?> processReceivedRequest(ProcessReceivedRequestResDto req){
    
    /* TODO : 로그인 된 사람 받아오기 필요 */
    User child = userRepository.findAll().stream().filter((u) -> u.getType() == User.Type.CHILD).toList().get(0);
    
    return null;
  }
}
