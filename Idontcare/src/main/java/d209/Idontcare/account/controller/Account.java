package d209.Idontcare.account.controller;

import d209.Idontcare.account.dto.req.*;
import d209.Idontcare.account.dto.res.RealAccountRes;
import d209.Idontcare.account.exception.VirtualAccountException;
import d209.Idontcare.account.service.EncryptService;
import d209.Idontcare.account.service.RealAccountService;
import d209.Idontcare.account.service.VirtualAccountService;
import d209.Idontcare.common.APIBuilder;
import d209.Idontcare.common.ObjectMapper;
import d209.Idontcare.common.dto.APIResultDto;
import d209.Idontcare.common.dto.ResponseDto;
import d209.Idontcare.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Tag(name="가상 계좌 API")
@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class Account {

    public final EncryptService encryptService;
    public final RealAccountService realAccountService;
    public final VirtualAccountService virtualAccountService;
    public final UserService userService;

    @Value("${openbank.url}")
    private String url;

    @Value("${idontcare.account}")
    private String iDontCareAccount;

    @Value("${idontcare.bankcode}")
    private String iDontCareBankCode;

    //사용자 인증
    @PostMapping("/auth")
    @Operation(summary = "사용자 인증", description = "사용자 인증")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "402", description = "계좌를 등록 하지 않았음")
    })
//    @LoginOnly(level = LoginOnly.Level.PARENT_OR_CHILD)
    public ResponseDto findTransaction(@RequestBody AuthReq authReq, HttpServletRequest request) throws Exception{
        Map<String, String> map = new HashMap<>();
        try{
            APIResultDto result = APIBuilder.build()
                    .url(url + "/openbanking/oauth/2.0/token")
                    .method(HttpMethod.POST)
                    .body(authReq)
                    .execute();
            System.out.println(result.getBody());
            map.put("msg", ((Map<String, String>) result.getBody()).get("msg") );
            map.put("data", ((Map<String, String>) result.getBody()).get("data"));
            return ResponseDto.success(map);
        } catch (Exception e){
            Map<String, String> errorCode = ObjectMapper.findErrorCode(e.getMessage());
            return ResponseDto.fail(errorCode);
        }
    }

    //충전 계좌 등록
    @PostMapping("/regist")
    @Operation(summary = "충전 계좌 등록", description = "실계좌 조희")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "402", description = "계좌를 등록 하지 않았음")
    })
//    @LoginOnly(level = LoginOnly.Level.PARENT_OR_CHILD)
    public ResponseDto findTransaction(@RequestBody InquiryReq inquiryReq, HttpServletRequest request) throws Exception{
        try{
            APIResultDto result = APIBuilder.build()
                    .url(url + "/openbanking/oauth/2.0/pin")
                    .method(HttpMethod.POST)
                    .body(inquiryReq)
                    .execute();
            System.out.println(result.getBody());
            return ResponseDto.success(((Map<String, String>) result.getBody()).get("data"));
        } catch (Exception e){
            Map<String, String> errorCode = ObjectMapper.findErrorCode(e.getMessage());
            return ResponseDto.fail(errorCode);
        }
    }

    //은행 리스트 조회
    @GetMapping("/bank")
    @Operation(summary = "은행 리스트 조회", description = "등록 된 전체 은행 리스트와 이미지 경로")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
    })
//    @LoginOnly(level = LoginOnly.Level.PARENT_ONLY)
    public ResponseDto<?> BankList(HttpServletRequest request) throws Exception {
        //토큰에 대한 사용자 userId
        Long userId = (Long) request.getAttribute("userId");
        Map<String, String> map = new HashMap<>();
        try{
            APIResultDto result = APIBuilder.build()
                    .url(url + "/openbanking/bank/image")
                    .method(HttpMethod.GET)
                    .execute();
            return ResponseDto.success(((Map<String, Object>) result.getBody()).get("data"));
        } catch (Exception e){
            Map<String, String> errorCode = ObjectMapper.findErrorCode(e.getMessage());
            return ResponseDto.fail(errorCode);
        }
    }

    //계좌 충전 창에서 실계좌를 등록했는지 조회
    @GetMapping("/my")
    @Operation(summary = "실계좌 조회", description = "실계좌 조희")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "402", description = "계좌를 등록 하지 않았음")
    })
//    @LoginOnly(level = LoginOnly.Level.PARENT_ONLY)
    public ResponseDto<?> findMyRealAccount(HttpServletRequest request) throws Exception {
        //토큰에 대한 사용자 userId
//        Long userId = (Long) request.getAttribute("userId");
        Map<String, String> map = new HashMap<>();
        Long userId = 1L;
        try{
            RealAccountRes realAccount = realAccountService.findRealAccount(userId);
            return ResponseDto.success(realAccount);
        } catch (VirtualAccountException e){
            return ResponseDto.fail(e);
        }
    }

    //계좌 이체 유효
    @GetMapping("")
    @Operation(summary = "계좌이체시 실 계좌 유효성 체크", description = "실계좌 유효성 체크")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "404", description = "일치하는 고객이 없을 때 발생.")
    })
//    @LoginOnly(level = LoginOnly.Level.PARENT_ONLY)
    public ResponseDto<?> findRealAccount(@RequestBody ReceiveReq receiveReq, HttpServletRequest request) throws Exception {
        //토큰에 대한 사용자 userId
        Long userId = (Long) request.getAttribute("userId");
        Map<String, String> map = new HashMap<>();
        try{
            APIResultDto result = APIBuilder.build()
                .url(url + "/openbanking/inquiry/receive")
                .method(HttpMethod.POST)
                .body(receiveReq)
                .execute();
            return ResponseDto.success(((Map<String, Object>) result.getBody()).get("data"));
        } catch (Exception e){
            Map<String, String> errorCode = ObjectMapper.findErrorCode(e.getMessage());
            return ResponseDto.fail(errorCode);
        }
    }



//    충전 (출금계좌 → 가상계좌 )
    @PostMapping("/charge")
    @Operation(summary = "등록한 출금 계좌에서 가상 계좌로 돈 출금", description = "충전")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
    })
//    @LoginOnly(level = LoginOnly.Level.PARENT_ONLY)
    public ResponseDto<?> chargeMoney(@RequestBody ChargeAccountRes chargeAccountRes, HttpServletRequest request) throws Exception {
        //토큰에 대한 사용자 userId
//        Long userId = (Long) request.getAttribute("userId");
        Map<String, String> map = new HashMap<>();
        Long userId = 1L;
        try {
        WithdrawReq withdrawReq = WithdrawReq.builder()
                .fintechUseNum(chargeAccountRes.getPinNumber())
                .bankTranId(null)
                .wdPrintContent("아이돈케어 계좌로 입금")
                .cntrAccountNum(iDontCareAccount)
                .cntrAccountBankCodeStd(iDontCareBankCode)
                .tranAmt(chargeAccountRes.getMoney())
                .tranDtime(null)
                .reqClientName(userService.findByUserId(userId).get().getName())
                .recvClientName("아이돈케어")
                .recvClientBankCode(iDontCareBankCode)
                .recvClientAccountNum(iDontCareAccount)
                .recvDpsPrintContent("아이돈케어 가상 계좌에" + chargeAccountRes.getMoney() + "원 충전")
                .build();
            APIResultDto result = APIBuilder.build()
                    .url(url + "/openbanking/transfer/withdraw/fin_num")
                    .method(HttpMethod.POST)
                    .body(withdrawReq)
                    .execute();
            virtualAccountService.charge(userId, chargeAccountRes.getMoney());
            return ResponseDto.success(((Map<String, Object>) result.getBody()).get("data"));
        } catch (VirtualAccountException e){
            Map<String, String> errorCode = ObjectMapper.findErrorCode(e.getMessage());
            return ResponseDto.fail(errorCode);
        }
    }

    //계좌이체 (가상계좌 → 실계좌)
    @PostMapping("")
    @Operation(summary = "가상 계좌에서 실 계좌로 송금(나에게로 보내기 혹은 타인에게 보내기)", description = "가상에서 실계좌로 송금")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "가상 계좌에서 실 계좌로 송금 완료"),
            @ApiResponse(responseCode = "402", description = "가상 계좌의 잔액 부족")
    })
//    @LoginOnly(level = LoginOnly.Level.PARENT_ONLY)
    public ResponseDto<?> virtualToReal(@RequestBody VirtualToRealReq payment, HttpServletRequest request) throws Exception {
        //토큰에 대한 사용자 userId
//        Long userId = (Long) request.getAttribute("userId");
//        Map<String, String> map = new HashMap<>();
        Long userId = 1L;
        Map<String, String> map = new HashMap<>();
        try{
            //가상 계좌 잔액이 출금 요청 잔액 보다 작으면 exception
            Long balance = virtualAccountService.userBalance(userId);
            if(balance < payment.getTranAmt()) throw new VirtualAccountException(402, "계좌의 잔액이 부족합니다!");
            DepositReq depositReq = DepositReq.builder()
                    .cntrAccountNum(iDontCareAccount)
                    .cntrAccountBankCodeStd(iDontCareBankCode)
                    .reqClientName(userService.findNameByUserId(userId))
                    .recvClientName(payment.getName())
                    .recvClientBankCode(payment.getBankCode())
                    .recvClientAccountNum(payment.getAccountNum())
                    .recvDpsPrintContent(payment.getPrintContent())
                    .tranAmt(payment.getTranAmt())
                    .build();
            APIResultDto result = APIBuilder.build()
                    .url(url + "/openbanking/transfer/deposit/fin_num")
                    .method(HttpMethod.POST)
                    .body(depositReq)
                    .execute();
            //내 실계좌로 보내기
            if(userService.findNameByUserId(userId).equals(payment.getName())){
                virtualAccountService.paymentMe(userId, payment.getTranAmt());
            }
            //타인의 실계좌로 보내기
            else{
                virtualAccountService.paymentAnother(userId, payment.getTranAmt(), payment.getName());
            }
            return ResponseDto.success(((Map<String, Object>) result.getBody()).get("data"));
        }catch (VirtualAccountException e){
            Map<String, String> errorCode = new HashMap<>();
            errorCode.put("code", Integer.toString(e.getCode()));
            errorCode.put("error", e.getMessage());
            return ResponseDto.fail(errorCode);
        }catch (Exception e){
            Map<String, String> errorCode = ObjectMapper.findErrorCode(e.getMessage());
            return ResponseDto.fail(errorCode);
        }
    }
}
