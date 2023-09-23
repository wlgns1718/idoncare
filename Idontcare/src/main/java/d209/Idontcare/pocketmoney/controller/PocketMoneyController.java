package d209.Idontcare.pocketmoney.controller;

import d209.Idontcare.account.exception.VirtualAccountException;
import d209.Idontcare.common.ErrorHandler;
import d209.Idontcare.common.annotation.LoginOnly;
import d209.Idontcare.common.dto.ResponseDto;
import d209.Idontcare.common.exception.*;
import d209.Idontcare.pocketmoney.dto.req.*;
import d209.Idontcare.pocketmoney.dto.res.*;
import d209.Idontcare.pocketmoney.entity.RegularPocketMoney;
import d209.Idontcare.pocketmoney.service.PocketMoneyService;
import d209.Idontcare.common.annotation.LoginOnly.Level;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Tag(name="용돈관리 API")
@RestController
@RequestMapping("/api/pocketmoney")
@RequiredArgsConstructor
public class PocketMoneyController {

    private final PocketMoneyService pocketMoneyService;
    
    //부모가 아이에게 정기용돈 등록
    @PostMapping("/regular")
    @Operation(summary="정기용돈 등록", description="부모가 아이에게 정기적으로 줄 용돈을 설정")
    @ApiResponses(value = {
        @ApiResponse(responseCode="200", description = "성공",
            content=@Content(schema = @Schema(implementation= RegistRegularPocketMoneyResDto.class))),
        @ApiResponse(responseCode= AuthenticationException.CODE, description = AuthenticationException.DESCRIPTION),
        @ApiResponse(responseCode= MustParentException.CODE, description = MustParentException.DESCRIPTION),
        @ApiResponse(responseCode= BadRequestException.CODE, description = BadRequestException.DESCRIPTION)
    })
    @LoginOnly(level = Level.PARENT_ONLY)
    public ResponseDto registRegularPocketMoney(@Valid @RequestBody RegistRegularPocketMoneyReqDto req,
                                                HttpServletRequest request,
                                                BindingResult bindingResult){
        Long parentUserId = (Long)request.getAttribute("userId");

        ErrorHandler.ErrorHandling(bindingResult);
        
        RegularPocketMoney saved = pocketMoneyService.registryRegularPocketMoney(parentUserId, req, LocalDateTime.now());
        RegistRegularPocketMoneyResDto result = RegistRegularPocketMoneyResDto.builder()
                                                        .regularPocketMoneyId(saved.getRegularPocketMoneyId())
                                                        .build();
        return ResponseDto.success(result);
    }

    //부모가 아이에게 일회성 용돈 전송
    @PostMapping("/send")
    @Operation(summary="일회성 용돈 전송", description="부모가 아이에게 일회성으로 용돈 전송")
    @ApiResponses(value = {
        @ApiResponse(responseCode="200", description = "성공",
            content=@Content(schema = @Schema(implementation= Void.class))),
        @ApiResponse(responseCode= AuthenticationException.CODE, description = AuthenticationException.DESCRIPTION),
        @ApiResponse(responseCode= MustParentException.CODE, description = MustParentException.DESCRIPTION),
        @ApiResponse(responseCode= MustChildException.CODE, description = MustChildException.DESCRIPTION)
    })
    @LoginOnly(level = Level.PARENT_ONLY)
    public ResponseDto sendPocketMoney(@Valid @RequestBody SendPocketMoneyReqDto req, HttpServletRequest request){
        Long parentUserId = (Long)request.getAttribute("userId");
        
        pocketMoneyService.sendPocketMoney(parentUserId, req);
        return ResponseDto.success(null);
    }
    
    //아이가 부모에게 용돈 조르기
    @PostMapping("/request")
    @Operation(summary="아이가 용돈 조르기", description="아이가 부모에게 용돈 조르기")
    @ApiResponses(value = {
        @ApiResponse(responseCode="200", description = "성공",
            content=@Content(schema = @Schema(implementation= Void.class))),
        @ApiResponse(responseCode= AuthenticationException.CODE, description = AuthenticationException.DESCRIPTION),
        @ApiResponse(responseCode= MustParentException.CODE, description = MustParentException.DESCRIPTION),
        @ApiResponse(responseCode= MustChildException.CODE, description = MustChildException.DESCRIPTION)
    })
    @LoginOnly(level = Level.CHILD_ONLY)
    public ResponseDto requestPocketMoney(@Valid @RequestBody RequestPocketMoneyReqDto req, HttpServletRequest request){
        Long childUserId = (Long)request.getAttribute("userId");
        
        pocketMoneyService.requestPocketMoney(childUserId, req);
        return ResponseDto.success(null);
    }
    
    //부모가 용돈 조르기 목록을 볼 수 있다
    @GetMapping("/request")
    @Operation(summary="조르기 목록 조회", description="부모가 아이의 조르기 목록을 볼 수 있다")
    @ApiResponses(value = {
        @ApiResponse(responseCode="200", description = "성공",
            content=@Content(schema = @Schema(implementation= GetPocketMoneyRequestResDto.GetPocketMoneyRequestResDtoResult.class))),
        @ApiResponse(responseCode= AuthenticationException.CODE, description = AuthenticationException.DESCRIPTION),
        @ApiResponse(responseCode= MustParentException.CODE, description = MustParentException.DESCRIPTION)
    })
    @LoginOnly(level = Level.PARENT_OR_CHILD)
    public ResponseDto getPocketMoneyRequest(HttpServletRequest request){
        Long userId = (Long)request.getAttribute("userId");
        
        List<GetPocketMoneyRequestResDto> list =  pocketMoneyService.getPocketMoneyRequest(userId);
        return ResponseDto.success(new GetPocketMoneyRequestResDto.GetPocketMoneyRequestResDtoResult(list));
    }
    
    //부모가 자녀의 용돈 조르기에 대해 처리할 수 있다
    @PutMapping("/request")
    @Operation(summary="조르기 처리", description="부모가 아이의 조르기에 대해 처리 할 수 있다")
    @ApiResponses(value = {
        @ApiResponse(responseCode="200", description = "성공",
            content=@Content(schema = @Schema(implementation= Void.class))),
        @ApiResponse(responseCode= AuthenticationException.CODE, description = AuthenticationException.DESCRIPTION),
        @ApiResponse(responseCode= MustParentException.CODE, description = MustParentException.DESCRIPTION),
        @ApiResponse(responseCode= NoSuchContentException.CODE, description = NoSuchContentException.DESCRIPTION),
        @ApiResponse(responseCode= VirtualAccountException.CODE, description = VirtualAccountException.DESCRIPTION)
    })
    @LoginOnly(level = Level.PARENT_ONLY)
    public ResponseDto processPocketMoneyRequest(@Valid @RequestBody ProcessPocketMoneyRequestReqDto req, HttpServletRequest request){
        Long parentUserId = (Long)request.getAttribute("userId");
        
        pocketMoneyService.processPocketMoneyRequest(parentUserId, req);
        return ResponseDto.success(null);
    }
}
