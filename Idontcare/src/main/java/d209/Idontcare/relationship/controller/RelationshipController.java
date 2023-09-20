package d209.Idontcare.relationship.controller;

import d209.Idontcare.TUser;
import d209.Idontcare.TUserRepository;
import d209.Idontcare.common.annotation.LoginOnly;
import d209.Idontcare.common.dto.ResponseDto;
import d209.Idontcare.common.exception.*;
import d209.Idontcare.relationship.dto.req.ProcessReceivedRequestReqDto;
import d209.Idontcare.relationship.dto.res.ReceivedRequestResDto;
import d209.Idontcare.relationship.dto.req.RequestRelationshipReqDto;
import d209.Idontcare.relationship.dto.res.RelationshipResDto;
import d209.Idontcare.relationship.dto.res.RequestRelationshipResDto;
import d209.Idontcare.relationship.entity.RelationshipRequest;
import d209.Idontcare.relationship.service.RelationshipService;
import d209.Idontcare.user.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Tag(name="관계관리 API")
@RestController
@RequestMapping("/api/relationship")
@RequiredArgsConstructor
public class RelationshipController {

  private final RelationshipService relationshipService;
  private final TUserRepository TUserRepository;
  
  @GetMapping("")
  @Operation(summary="부모 자식 관계 리스트", description = "부모와 자식 간에 맺어져 있는 관계 리스트를 반환합니다")
  @ApiResponses(value = {
      @ApiResponse(responseCode="200", description = "성공",
        content=@Content(schema = @Schema(implementation = RelationshipResDto.RelationshipResDtoResult.class))),
      @ApiResponse(responseCode=AuthenticationException.CODE, description = AuthenticationException.DESCRIPTION),
  })
  @LoginOnly(level = LoginOnly.Level.PARENT_OR_CHILD)
  public ResponseDto<?> getRelationshipList(HttpServletRequest request){
    User user = (User)request.getAttribute("user");
    
    List<RelationshipResDto> list = relationshipService.getRelationshipList(user);
    
    return ResponseDto.success(new RelationshipResDto.RelationshipResDtoResult(list));
  }
  
  
  @PostMapping("/request")
  @Operation(summary="관계 맺기 요청", description="부모가 아이에게 관계 맺기를 요청합니다")
  @ApiResponses(value = {
      @ApiResponse(responseCode="200", description = "성공",
        content=@Content(schema = @Schema(implementation= RequestRelationshipResDto.class))),
      @ApiResponse(responseCode=AuthenticationException.CODE, description = AuthenticationException.DESCRIPTION),
      @ApiResponse(responseCode=MustParentException.CODE, description = MustParentException.DESCRIPTION),
      @ApiResponse(responseCode=MustChildException.CODE, description = MustChildException.DESCRIPTION),
      @ApiResponse(responseCode=NoSuchUserException.CODE, description = NoSuchUserException.DESCRIPTION),
      @ApiResponse(responseCode=DuplicatedException.CODE, description = "이미 리퀘스트 한 경우"),
  })
  @LoginOnly(level = LoginOnly.Level.PARENT_ONLY)
  public ResponseDto<?> requestRelationship(@Valid @RequestBody RequestRelationshipReqDto req, HttpServletRequest request){
    User parent = (User)request.getAttribute("user");
    
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
          content=@Content(schema = @Schema(implementation= ReceivedRequestResDto.ReceivedRequestResDtoResult.class))),
      @ApiResponse(responseCode=MustChildException.CODE, description = MustChildException.DESCRIPTION),
  })
  @LoginOnly(level = LoginOnly.Level.CHILD_ONLY)
  public ResponseDto<?> getReceivedRequestList(HttpServletRequest request){
    User child = (User)request.getAttribute("request");

    List<ReceivedRequestResDto> requests = relationshipService.getReceivedRequestList(child);
    return ResponseDto.success(new ReceivedRequestResDto.ReceivedRequestResDtoResult(requests));
  }
  
  @PutMapping("/child/request")
  @Operation(summary="요청에 대해 수락 처리", description="아이가 부모로부터 받은 요청에 대해 처리")
  @ApiResponses(value = {
      @ApiResponse(responseCode="200", description = "성공",
          content=@Content(schema = @Schema(implementation= Void.class))),
      @ApiResponse(responseCode=MustChildException.CODE, description = MustChildException.DESCRIPTION),
      @ApiResponse(responseCode=NoSuchContentException.CODE, description = "해당하는 관계 요청이 없는 경우"),
      @ApiResponse(responseCode=AuthorizationException.CODE, description = AuthorizationException.DESCRIPTION),
  })
  @LoginOnly(level = LoginOnly.Level.CHILD_ONLY)
  public ResponseDto<?> processReceivedRequest(@RequestBody ProcessReceivedRequestReqDto req, HttpServletRequest request){
    
    User child = (User)request.getAttribute("user");
    
    relationshipService.processReceivedRequest(child, req);
    return ResponseDto.success(null);
  }
}
