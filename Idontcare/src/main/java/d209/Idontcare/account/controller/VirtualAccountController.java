package d209.Idontcare.account.controller;

import d209.Idontcare.account.dto.req.ActiveReq;
import d209.Idontcare.account.dto.req.ActiveRes;
import d209.Idontcare.account.dto.req.VirtualToVirtualReq;
import d209.Idontcare.account.dto.res.DailyHistoryRes;
import d209.Idontcare.account.dto.res.MonthTransactionHistoryRes;
import d209.Idontcare.account.exception.TransactionHistoryException;
import d209.Idontcare.account.exception.VirtualAccountException;
import d209.Idontcare.account.service.TransactionHistoryService;
import d209.Idontcare.account.service.VirtualAccountService;
import d209.Idontcare.common.annotation.LoginOnly;
import d209.Idontcare.common.dto.ResponseDto;
import d209.Idontcare.common.exception.MustChildException;
import d209.Idontcare.mission.dto.GetMissionInfoDto;
import d209.Idontcare.pocketmoney.dto.res.GetRegularPocketMoneysResDto;
import d209.Idontcare.user.entity.Role;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Tag(name="가상 계좌 API")
@RestController
@RequestMapping("/api/virtual")
@RequiredArgsConstructor
public class VirtualAccountController {

    private final VirtualAccountService virtualAccountService;
    private final TransactionHistoryService transactionHistoryService;

    //가상계좌 잔액 조회
    @GetMapping("/balance")
    @Operation(summary = "가상 계좌 잔액 조회", description = "본인 가상 계좌의 잔액을 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
    })
    @LoginOnly(level = LoginOnly.Level.PARENT_OR_CHILD)
    public ResponseDto<?> accountBalance(HttpServletRequest request) throws Exception {
        //토큰에 대한 사용자 userId
        Long userId = (Long) request.getAttribute("userId");
        try {
            Map<String, Long> map = new HashMap<>();
            Long userBalance = virtualAccountService.userBalance(userId);
            map.put("balance", userBalance);
            return ResponseDto.success(map);
        } catch (Exception e) {
            Map<String, String> map = new HashMap<>();
            map.put("code", "500");
            map.put("error", "가상 계좌 잔액 조회 도중 오류 발생");
            return ResponseDto.fail(map);
        }
    }

    //월별 가상계좌 거래내역 조회
    @GetMapping("/{Year}/{Month}")
    @Operation(summary = "월별 가상 계좌 거래내역 조회", description = "년월별 가상 계좌 거래내역 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "204", description = "거래 내역 없음"),
    })
    @LoginOnly(level = LoginOnly.Level.PARENT_OR_CHILD)
    public ResponseDto<?> accountYearMonth(@PathVariable("Year") String year, @PathVariable("Month") String month, HttpServletRequest request) throws Exception {
        //토큰에 대한 사용자 userId
        Long userId = (Long) request.getAttribute("userId");
        Map<String, String> map = new HashMap<>();
        try{
            List<MonthTransactionHistoryRes> result = transactionHistoryService.userTransactionHistoryByDate(userId, Integer.parseInt(year), Integer.parseInt(month));
            return ResponseDto.success(result);
        }catch(TransactionHistoryException e){
            return ResponseDto.fail(e);
        }catch (Exception e) {
            map.put("code", "500");
            map.put("error", e.getMessage());
            return ResponseDto.fail(map);
        }
    }

    //거래내역 키워드 검색
    @GetMapping("/content/{content}")
    @Operation(summary = "거래 내역 키워드 검색", description = "거래 내역 키워드로 검색")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "204", description = "거래 내역 없음"),
    })
    @LoginOnly(level = LoginOnly.Level.PARENT_OR_CHILD)
    public ResponseDto<?> accountContent(@PathVariable(value = "content", required = false) String content, HttpServletRequest request) throws Exception {
        //토큰에 대한 사용자 userId
        Long userId = (Long) request.getAttribute("userId");
        Map<String, String> map = new HashMap<>();
        try{
            List<MonthTransactionHistoryRes> result = transactionHistoryService.userTransactionHistoryByContent(userId, content);
            return ResponseDto.success(result);
        }catch(TransactionHistoryException e){
            return ResponseDto.fail(e);
        }catch (Exception e) {
            map.put("code", "500");
            map.put("error", e.getMessage());
            return ResponseDto.fail(map);
        }
    }

    //계좌이체 (가상계좌 → 가상계좌)
    @PostMapping("")
    @Operation(summary = "가상 계좌에서 가상 계좌로 송금", description = "가상에서 가상으로 송금")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "가상 게좌에서 가상 계좌로 송금 완료"),
            @ApiResponse(responseCode = "402", description = "가상 계좌의 잔액 부족")
    })
    @LoginOnly(level = LoginOnly.Level.PARENT_OR_CHILD)
    public ResponseDto<?> virtualToVirtual(@RequestBody VirtualToVirtualReq payment, HttpServletRequest request) throws Exception {
            //토큰에 대한 사용자 userId
        Long userId = (Long) request.getAttribute("userId");
        Map<String, String> map = new HashMap<>();
        if(payment.getUserId() == userId){
            map.put("code", "403");
            map.put("error", "자기 자신에게는 보낼 수 없습니다.");
            return ResponseDto.fail(map);
        }
        try{
            Long virtualAccount = virtualAccountService.userAccount(userId);
            virtualAccountService.virtualPayment(payment, virtualAccount);
            return ResponseDto.success("가상 계좌에서 가상 계좌로 송금 완료");
        }catch (VirtualAccountException e){
            return ResponseDto.fail(e);
        }catch (Exception e) {
            map.put("code", "500");
            map.put("error", e.getMessage());
            return ResponseDto.fail(map);
        }
    }

    //자녀의 활동 보고서(월)
    //현재 월의 최근 5개월
    @GetMapping("/active/{userId}/monthly")
    @Operation(summary = "월간 자녀 활동보고서", description = "자녀의 활동 보고서(자녀의 uuid 필요)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
    })
    @LoginOnly(level = LoginOnly.Level.PARENT_ONLY)
    public ResponseDto
        <?> findActiveReportForParent(@PathVariable("userId") Long userId, HttpServletRequest request) throws Exception {
        //토큰에 대한 사용자 userId
        Map<String, String> map = new HashMap<>();
        ActiveRes activeRes = transactionHistoryService.reportPerMonth(userId);
        
        try{
            return ResponseDto.success(activeRes);
        }catch (VirtualAccountException e){
            return ResponseDto.fail(e);
        }catch (Exception e) {
            map.put("code", "500");
            map.put("error", e.getMessage());
            System.out.println(map.get("error"));
            System.out.println(map.get("code"));
            return ResponseDto.fail(map);
        }
    }
    
    @GetMapping("/active/monthly")
    @Operation(summary = "월간 본인(자녀) 활동보고서", description = "본인(자녀)의 월별 보고서")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "성공"),
    })
    @LoginOnly(level = LoginOnly.Level.CHILD_ONLY)
    public ResponseDto<?> findActiveReport(HttpServletRequest request){
        Long childUserId = (Long)request.getAttribute("userId");
        ActiveRes activeRes = transactionHistoryService.reportPerMonth(childUserId);
        return ResponseDto.success(activeRes);
    }
    
    
    @GetMapping("/active/{userId}/daily")
    @Operation(summary = "일별 자녀 활동보고서", description = "자녀의 일별 활동보고서(자녀의 ID필요)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "성공",
            content=@Content(array = @ArraySchema(schema = @Schema(implementation= DailyHistoryRes.class)))),
    })
    @LoginOnly(level = LoginOnly.Level.PARENT_ONLY)
    public ResponseDto<?> findDailyActiveReportForParent(@PathVariable("userId") Long childUserId, HttpServletRequest request){
        Long parentUserId = (Long)request.getAttribute("userId");
        LocalDateTime day = LocalDateTime.now();
        
        List<DailyHistoryRes> result = transactionHistoryService.reportPerDailyForParent(parentUserId, childUserId, day);

        return ResponseDto.success(result);
    }
    
    @GetMapping("/active/daily")
    @Operation(summary = "일별 본인(자녀)의 활동보고서", description = "자녀 본인의 일별 보고서")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "성공",
            content=@Content(array = @ArraySchema(schema = @Schema(implementation= DailyHistoryRes.class)))),
        @ApiResponse(responseCode = MustChildException.CODE, description = MustChildException.DESCRIPTION),
    })
    @LoginOnly(level = LoginOnly.Level.CHILD_ONLY)
    public ResponseDto<?> findDailyActiveReport(HttpServletRequest request){
        Long childUserId = (Long)request.getAttribute("userId");
        
        LocalDateTime day = LocalDateTime.now();
        List<DailyHistoryRes> result = transactionHistoryService.reportPerDaily(childUserId, day);
        
        return ResponseDto.success(result);
    }
}
