package d209.Idontcare.relationship.controller;

import d209.Idontcare.common.annotation.LoginOnly;
import d209.Idontcare.common.annotation.LoginOnly.Level;
import d209.Idontcare.common.dto.ResponseDto;
import d209.Idontcare.common.exception.*;
import d209.Idontcare.relationship.dto.req.*;
import d209.Idontcare.relationship.dto.res.*;
import d209.Idontcare.relationship.entity.RelationshipRequest;
import d209.Idontcare.relationship.service.RelationshipService;
import d209.Idontcare.user.entity.Role;
import d209.Idontcare.user.entity.User;
import d209.Idontcare.user.repository.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
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
  
  @GetMapping("")
  @Operation(summary="부모 자식 관계 리스트", description = "부모와 자식 간에 맺어져 있는 관계 리스트를 반환합니다")
  @ApiResponses(value = {
      @ApiResponse(responseCode="200", description = "성공",
        content=@Content(schema = @Schema(implementation = RelationshipResDto.RelationshipResDtoResult.class))),
      @ApiResponse(responseCode=AuthenticationException.CODE, description = AuthenticationException.DESCRIPTION),
  })
  @LoginOnly(level = Level.PARENT_OR_CHILD)
  public ResponseDto<?> getRelationshipList(HttpServletRequest request){
    Long userId = (Long)request.getAttribute("userId");
    Role role = (Role)request.getAttribute("role");
    
    List<RelationshipResDto> list = relationshipService.getRelationshipList(userId, role);
    
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
  @LoginOnly(level = Level.PARENT_ONLY)
  public ResponseDto<?> requestRelationship(@Valid @RequestBody RequestRelationshipReqDto req, HttpServletRequest request){
    Long parentUserId = (Long)request.getAttribute("userId");

    
    RelationshipRequest saved = relationshipService.requestRelationship(parentUserId, req);
    RequestRelationshipResDto result = RequestRelationshipResDto.builder()
                                        .requestRelationshipId(saved.getRelationshipRequestId())
                                        .build();

    return ResponseDto.success(result);
  }
  
  @GetMapping("/child/request")
  @Operation(summary="관계 요청 받은 리스트", description="아이가 부모로부터 받은 요청을 볼 수 있습니다")
  @ApiResponses(value = {
      @ApiResponse(responseCode="200", description = "성공",
          content=@Content(schema = @Schema(implementation= ReceivedRequestResDto.ReceivedRequestResDtoResult.class))),
      @ApiResponse(responseCode=MustChildException.CODE, description = MustChildException.DESCRIPTION),
  })
  @LoginOnly(level = Level.CHILD_ONLY)
  public ResponseDto<?> getReceivedRequestList(HttpServletRequest request){
    Long childUerId = (Long)request.getAttribute("userId");

    List<ReceivedRequestResDto> requests = relationshipService.getReceivedRequestList(childUerId);
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
      @ApiResponse(responseCode=MustParentException.CODE, description = MustParentException.DESCRIPTION),
  })
  @LoginOnly(level = Level.CHILD_ONLY)
  public ResponseDto<?> processReceivedRequest(@RequestBody ProcessReceivedRequestReqDto req, HttpServletRequest request){
    Long childUserId = (Long)request.getAttribute("userId");
    
    relationshipService.processReceivedRequest(childUserId, req);
    return ResponseDto.success(null);
  }
  
  @DeleteMapping("")
  @Operation(summary = "관계 삭제", description = "부모가 아이와의 관계를 삭제")
  @ApiResponses(value = {
      @ApiResponse(responseCode="200", description = "성공",
          content=@Content(schema = @Schema(implementation= Void.class))),
      @ApiResponse(responseCode=NoSuchContentException.CODE, description = "해당하는 관계 요청이 없는 경우"),
      @ApiResponse(responseCode=AuthenticationException.CODE, description = AuthenticationException.DESCRIPTION),
      @ApiResponse(responseCode=AuthorizationException.CODE, description = AuthorizationException.DESCRIPTION),
      @ApiResponse(responseCode=MustParentException.CODE, description = MustParentException.DESCRIPTION),
  })
  @LoginOnly(level = Level.PARENT_ONLY)
  public ResponseDto deleteRelationship(@RequestBody DeleteRelationshipReqDto req, HttpServletRequest request){
    Long parentUserId = (Long)request.getAttribute("userId");
    
    relationshipService.deleteRelationship(parentUserId, req.getRelationshipId());
    
    return ResponseDto.success(null);
  }
}
