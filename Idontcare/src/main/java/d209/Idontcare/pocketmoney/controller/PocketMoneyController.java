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
import d209.Idontcare.user.entity.Role;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Tag(name="용돈관리 API")
@RestController
@RequestMapping("/api/pocketmoney")
@RequiredArgsConstructor
@Slf4j
public class PocketMoneyController {

    private final PocketMoneyService pocketMoneyService;
    
    @GetMapping("/regular")
    @Operation(summary = "정기용돈 등록 목록 조회", description = "부모가 아이에게 등록한 정기용돈 목록 조회")
    @ApiResponses(value = {
        @ApiResponse(responseCode="200", description = "성공",
            content=@Content(array = @ArraySchema(schema = @Schema(implementation= GetRegularPocketMoneysResDto.class)))),
        @ApiResponse(responseCode= AuthenticationException.CODE, description = AuthenticationException.DESCRIPTION),
        @ApiResponse(responseCode= MustParentException.CODE, description = MustParentException.DESCRIPTION)
    })
    @LoginOnly(level = Level.PARENT_OR_CHILD)
    public ResponseDto getRegularPocketMoneyRequest(HttpServletRequest request){
        Long userId = (Long)request.getAttribute("userId");
        Role role = (Role)request.getAttribute("role");
        List<GetRegularPocketMoneysResDto> result = pocketMoneyService.getRegularPocketMoneys(userId, role);
        return ResponseDto.success(result);
    }
    
    
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

    //부모가 정기용돈 해제
    @DeleteMapping("/regular")
    @Operation(summary="정기용돈 삭제", description="부모가 아이에게 등록 된 정기용돈을 삭제")
    @ApiResponses(value = {
        @ApiResponse(responseCode="200", description = "성공",
            content=@Content(schema = @Schema(implementation= Void.class))),
        @ApiResponse(responseCode= AuthenticationException.CODE, description = AuthenticationException.DESCRIPTION),
        @ApiResponse(responseCode= AuthorizationException.CODE, description = AuthorizationException.DESCRIPTION),
        @ApiResponse(responseCode= MustParentException.CODE, description = MustParentException.DESCRIPTION),
        @ApiResponse(responseCode= BadRequestException.CODE, description = BadRequestException.DESCRIPTION),
        @ApiResponse(responseCode= NoSuchContentException.CODE, description = NoSuchContentException.DESCRIPTION),
    })
    @LoginOnly(level = Level.PARENT_ONLY)
    public ResponseDto deleteRegularPocketMoney(@Valid @RequestBody DeleteRegularPocketMoneyReqDto req, HttpServletRequest request){
        Long parentUserId = (Long)request.getAttribute("userId");
        
        pocketMoneyService.deleteRegularPocketMoney(parentUserId, req);
        
        return ResponseDto.success(null);
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
    public ResponseDto sendPocketMoney(@Valid @RequestBody SendPocketMoneyReqDto req, HttpServletRequest request, BindingResult bindingResult){
        Long parentUserId = (Long)request.getAttribute("userId");
        
        ErrorHandler.ErrorHandling(bindingResult);
        
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
    public ResponseDto requestPocketMoney(@Valid @RequestBody RequestPocketMoneyReqDto req, HttpServletRequest request, BindingResult bindingResult){
        Long childUserId = (Long)request.getAttribute("userId");
        ErrorHandler.ErrorHandling(bindingResult);
        
        pocketMoneyService.requestPocketMoney(childUserId, req);
        return ResponseDto.success(null);
    }
    
    //부모또는 아이가 용돈 조르기 목록을 볼 수 있다
    @GetMapping("/request")
    @Operation(summary="조르기 목록 조회", description="부모가 또는 아이가 조르기 목록을 볼 수 있다")
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
    public ResponseDto processPocketMoneyRequest(@Valid @RequestBody ProcessPocketMoneyRequestReqDto req, HttpServletRequest request, BindingResult bindingResult){
        Long parentUserId = (Long)request.getAttribute("userId");
        
        ErrorHandler.ErrorHandling(bindingResult);
        
        pocketMoneyService.processPocketMoneyRequest(parentUserId, req);
        return ResponseDto.success(null);
    }

    /* 매일 오전 01시 정기용돈 이체 */
    @Scheduled(cron = "0 0 1 * * *")
    public void executeRegularPocketMoney(){
        LocalDateTime now = LocalDateTime.now();
        pocketMoneyService.executeRegularPocketMoney(now);
    }
    
    @PostMapping("/regular/test/{day}")
    @Operation(summary = "정기용돈 지급 테스트용", description = "정기용돈 지급 테스트")
    public void testRegularPocketMoney(@PathVariable("day") Integer day){
        int year = 2000 + day / 10_000;
        int month = (day % 10_000) / 100;
        int date = day % 100;
        
        LocalDateTime now = LocalDateTime.of(year, month, date, 0, 0);
        pocketMoneyService.executeRegularPocketMoney(now);
    }
    
    @GetMapping("/time/test")
    @Operation(summary="서버시간 확인용", description="시간확인 해보자")
    public String timeTest(){
        return LocalDateTime.now().toString();
    }

    @GetMapping("/regular/rejected/{regularPocketMoneyId}")
    @Operation(summary = "정기용돈 미입금 내역 조회")
    @ApiResponses(value = {
        @ApiResponse(responseCode="200", description = "성공",
            content=@Content(array = @ArraySchema(schema = @Schema(implementation= GetRegularPocketMoneyRejectedResDto.class)))),
        @ApiResponse(responseCode= AuthenticationException.CODE, description = AuthenticationException.DESCRIPTION),
        @ApiResponse(responseCode= AuthorizationException.CODE, description = AuthorizationException.DESCRIPTION),
        @ApiResponse(responseCode= NoSuchContentException.CODE, description = NoSuchContentException.DESCRIPTION),
        @ApiResponse(responseCode= MustParentException.CODE, description = MustParentException.DESCRIPTION)
    })
    @LoginOnly(level=Level.PARENT_ONLY)
    public ResponseDto getRegularPocketMoneyRejectedList(@PathVariable("regularPocketMoneyId") Long regularPocketMoneyId, HttpServletRequest request){
        Long parentUserId = (Long)request.getAttribute("userId");
        List<GetRegularPocketMoneyRejectedResDto> list = pocketMoneyService.getRegularPocketMoneyRejectedList(parentUserId, regularPocketMoneyId);
        return ResponseDto.success(list);
    }
    
    @PutMapping("/regular/rejected")
    @Operation(summary = "정기용돈 미입금 내역 처리")
    @ApiResponses(value = {
        @ApiResponse(responseCode="200", description = "성공",
            content=@Content(array = @ArraySchema(schema = @Schema(implementation= Void.class)))),
        @ApiResponse(responseCode= AuthorizationException.CODE, description = AuthorizationException.DESCRIPTION),
        @ApiResponse(responseCode= AuthenticationException.CODE, description = AuthenticationException.DESCRIPTION),
        @ApiResponse(responseCode= NoSuchContentException.CODE, description = NoSuchContentException.DESCRIPTION),
        @ApiResponse(responseCode= VirtualAccountException.CODE, description = "돈이 부족합니다")
    })
    @LoginOnly(level = Level.PARENT_ONLY)
    public ResponseDto processRegularPocketMoneyRejected(@RequestBody ProcessPcoketMoneyRejectedReqDto req, HttpServletRequest request){
        Long parentUserId = (Long)request.getAttribute("userId");

        pocketMoneyService.processRegularPocketMoneyRejected(parentUserId, req);
        
        return ResponseDto.success(null);
    }
}
