package d209.Idontcare.account.controller;

import d209.Idontcare.account.dto.req.TransactionHistoryRes;
import d209.Idontcare.account.dto.req.VirtualToVirtualReq;
import d209.Idontcare.account.exception.TransactionHistoryException;
import d209.Idontcare.account.exception.VirtualAccountException;
import d209.Idontcare.account.service.TransactionHistoryService;
import d209.Idontcare.account.service.VirtualAccountService;
import d209.Idontcare.common.annotation.LoginOnly;
import d209.Idontcare.common.dto.ResponseDto;
import d209.Idontcare.common.service.APIService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Tag(name="가상 계좌 API")
@RestController
@RequestMapping("/api/virtual")
@RequiredArgsConstructor
public class VirtualAccount {

    private final VirtualAccountService virtualAccountService;
    private final TransactionHistoryService transactionHistoryService;

    //0. 예시
//    @GetMapping("/transaction")
//    @LoginOnly(level = LoginOnly.Level.PARENT_OR_CHILD)
//    public ResponseDto findTransaction(@RequestBody AuthRequestDto authRequestDto, HttpServletRequest request) throws Exception{
//    Long userId = (Long) request.getAttribute("userId");
//    Role role = (Role) request.getAttribute("role");
//        try{
//            APIResultDto result = APIBuilder.build()
//                    .url(url + "/openbanking/oauth/2.0/token")
//                    .method(HttpMethod.POST)
//                    .body(authRequestDto)
//                    .execute();
//            System.out.println(result.getBody());
//            return ResponseDto.success(((Map<String, String>) result.getBody()).get("data"));
//        } catch (Exception e){
//            Map<String, String> errorCode = ObjectMapper.findErrorCode(e.getMessage());
//            return ResponseDto.fail(errorCode);
//        }
//    }

    //가상계좌 잔액 조회
    @GetMapping("/balance")
    @Operation(summary = "가상 계좌 잔액 조회", description = "본인 가상 계좌의 잔액을 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
    })
    @LoginOnly(level = LoginOnly.Level.PARENT_ONLY)
    public ResponseDto<?> accountBalance(HttpServletRequest request) throws Exception {
        //토큰에 대한 사용자 userId
        Long userId = (Long) request.getAttribute("userId");
        Map<String, String> map = new HashMap<>();
        try {
            Long userBalance = virtualAccountService.userBalance(userId);
            map.put("balance", Long.toString(userBalance));
            return ResponseDto.success(map);
        } catch (Exception e) {
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
//    @LoginOnly(level = LoginOnly.Level.PARENT_ONLY)
    public ResponseDto<?> accountYearMonth(@PathVariable("Year") String year, @PathVariable("Month") String month, HttpServletRequest request) throws Exception {
        //토큰에 대한 사용자 userId
//        Long userId = (Long) request.getAttribute("userId");
        Map<String, String> map = new HashMap<>();
        Long userId = 1L;
        try{
            List<TransactionHistoryRes> transactionHistoryRes = transactionHistoryService.userTransactionHistoryByDate(userId, Integer.parseInt(year), Integer.parseInt(month));
            return ResponseDto.success(transactionHistoryRes);
        }catch(TransactionHistoryException e){
            return ResponseDto.fail(e);
        }
    }

    //거래내역 키워드 검색
    @GetMapping("/content/{content}")
    @Operation(summary = "거래 내역 키워드 검색", description = "거래 내역 키워드로 검색")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "204", description = "거래 내역 없음"),
    })
//    @LoginOnly(level = LoginOnly.Level.PARENT_ONLY)
    public ResponseDto<?> accountContent(@PathVariable("content") String content, HttpServletRequest request) throws Exception {
        //토큰에 대한 사용자 userId
//        Long userId = (Long) request.getAttribute("userId");
//        Map<String, String> map = new HashMap<>();
        Long userId = 1L;
        Map<String, String> map = new HashMap<>();
        try{
        List<TransactionHistoryRes> transactionHistoryRes = transactionHistoryService.userTransactionHistoryByContent(userId, content);
        return ResponseDto.success(transactionHistoryRes);
        }catch(TransactionHistoryException e){
            return ResponseDto.fail(e);
        }
    }

    //계좌이체 (가상계좌 → 가상계좌)
    @PostMapping("")
    @Operation(summary = "가상 계좌에서 가상 계좌로 송금", description = "가상에서 가상으로 송금")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "가상 게좌에서 가상 계좌로 송금 완료"),
            @ApiResponse(responseCode = "402", description = "가상 계좌의 잔액 부족")
    })
//    @LoginOnly(level = LoginOnly.Level.PARENT_ONLY)
    public ResponseDto<?> virtualToVirtual(@RequestBody VirtualToVirtualReq payment, HttpServletRequest request) throws Exception {
        //토큰에 대한 사용자 userId
//        Long userId = (Long) request.getAttribute("userId");
//        Map<String, String> map = new HashMap<>();
        Long userId = 1L;
        Map<String, String> map = new HashMap<>();
        try{
            Long virtualAccount = virtualAccountService.userAccount(userId);
            virtualAccountService.VirtualPayment(payment, virtualAccount);
            return ResponseDto.success("가상 계좌에서 가상 계좌로 송금 완료");
        }catch (VirtualAccountException e){
            return ResponseDto.fail(e);
        }
    }
}
