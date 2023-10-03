package d209.Idontcare.account.controller;

import d209.Idontcare.account.dto.req.*;
import d209.Idontcare.account.dto.res.ChargeAccountRes;
import d209.Idontcare.account.dto.res.RealAccountRes;
import d209.Idontcare.account.exception.VirtualAccountException;
import d209.Idontcare.account.service.EncryptService;
import d209.Idontcare.account.service.RealAccountService;
import d209.Idontcare.account.service.VirtualAccountService;
import d209.Idontcare.common.APIBuilder;
import d209.Idontcare.common.ObjectMapper;
import d209.Idontcare.common.annotation.LoginOnly;
import d209.Idontcare.common.dto.APIResultDto;
import d209.Idontcare.common.dto.ResponseDto;
import d209.Idontcare.user.entity.User;
import d209.Idontcare.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Tag(name="실계좌 API")
@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
@Slf4j
public class AccountController {

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
            @ApiResponse(responseCode = "402", description = "사용자 등록을 하지 못함.")
    })
    @LoginOnly(level = LoginOnly.Level.PARENT_OR_CHILD)
    public ResponseDto findTransaction(@RequestBody AuthReq authReq, HttpServletRequest request) throws Exception {
        Map<String, String> map = new HashMap<>();
        Long userId = (Long) request.getAttribute("userId");
        User user = userService.findByUserId(userId).get();
        if(!user.getName().equals(authReq.getName())){
            map.put("code", "402");
            map.put("error", "본인 명의만 등록 가능합니다.");
            return ResponseDto.fail(map);
        }
        try {
            APIResultDto result = APIBuilder.build()
                    .url(url + "/openbanking/oauth/2.0/token")
                    .method(HttpMethod.POST)
                    .body(authReq)
                    .execute();
            System.out.println(result.getBody());
            map.put("msg", ((Map<String, String>) result.getBody()).get("msg"));
            map.put("data", ((Map<String, String>) result.getBody()).get("data"));
            return ResponseDto.success(map);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            Map<String, String> errorCode = ObjectMapper.findErrorCode(e.getMessage());
            return ResponseDto.fail(errorCode);
        }
    }

    //충전 계좌 유효성 체크
    @PostMapping("/valid")
    @Operation(summary = "충전 계좌 유효성 체크", description = "충전 계좌 유효성 체크")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "402", description = "계좌가 없습니다.")
    })
    @LoginOnly(level = LoginOnly.Level.PARENT_OR_CHILD)
    public ResponseDto validateAccount(@RequestBody InquiryReq inquiryReq, HttpServletRequest request) throws Exception {
        //토큰에 대한 사용자 userId
        Long userId = (Long) request.getAttribute("userId");
        User user = userService.findByUserId(userId).get();
        Map<String, String> map = new HashMap<>();
            try {
                APIResultDto result = APIBuilder.build()
                        .url(url + "/openbanking/inquiry/receive")
                        .method(HttpMethod.POST)
                        .body(inquiryReq)
                        .execute();
                com.fasterxml.jackson.databind.ObjectMapper objectMapper = new com.fasterxml.jackson.databind.ObjectMapper();
                Map<String, Object> data = (Map<String, Object>) ((Map<String, Object>) result.getBody()).get("data");
                String clientName = (String) data.get("clientName");
                if(!user.getName().equals(clientName)){
                    map.put("code", "402");
                    map.put("error", "본인 명의만 등록 가능합니다.");
                    return ResponseDto.fail(map);
                }
                return ResponseDto.success(((Map<String, Object>) result.getBody()).get("data"));
            }catch (Exception e) {
            Map<String, String> errorCode = ObjectMapper.findErrorCode(e.getMessage());
            return ResponseDto.fail(errorCode);
        }
    }

//    계좌 등록
    @PostMapping("/")
    @Operation(summary = "충전 계좌 등록", description = "은행 서버에 등록 되어 있는 실계좌를 핀테크 서비스에서 open bank 서비스를 이용하기 위해 등록")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
    })
    @LoginOnly(level = LoginOnly.Level.PARENT_OR_CHILD)
    public ResponseDto findTransaction(@RequestBody InquiryReq inquiryReq, HttpServletRequest request) throws Exception {
        try {
            Long userId = (Long) request.getAttribute("userId");
            User user = userService.findByUserId(userId).get();
            APIResultDto result = APIBuilder.build()
                    .url(url + "/openbanking/oauth/2.0/regist")
                    .method(HttpMethod.POST)
                    .body(AccountRegistReq.builder()
                            .name(user.getName())
                            .phoneNumber(user.getPhoneNumber())
                            .bankCodeStd(inquiryReq.getBankCodeStd())
                            .accountNum(inquiryReq.getAccountNum())
                            .tranDtime(null)
                            .finTechServiceId("1234512345")
                            .build())
                    .execute();
            Map<String, Object> data = (Map<String, Object>) ((Map<String, Object>) result.getBody()).get("data");
            String pinNumber = (String) data.get("pinNumber");
            realAccountService.saveRealAccount(inquiryReq.getAccountNum(), user, pinNumber, inquiryReq.getBankName(),  inquiryReq.getBankCodeStd());
            return ResponseDto.success(((Map<String, Object>) result.getBody()).get("data"));
        } catch (Exception e) {
            Map<String, String> errorCode = ObjectMapper.findErrorCode(e.getMessage());
            return ResponseDto.fail(errorCode);
        }
    }

//    계좌 삭제
    @DeleteMapping("/")
    @Operation(summary = "충전 계좌 삭제", description = "충전 계좌 삭제")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "402", description = "계좌 삭제 완료")
    })
    @LoginOnly(level = LoginOnly.Level.PARENT_OR_CHILD)
    public ResponseDto deleteAccount(@RequestBody AccountDeleteReq req, HttpServletRequest request) throws Exception {
        Long userId = (Long) request.getAttribute("userId");
        User user = userService.findByUserId(userId).get();
        try {
            APIResultDto result = APIBuilder.build()
                    .url(url + "/openbanking/oauth/2.0/pin")
                    .method(HttpMethod.DELETE)
                    .body(req)
                    .execute();
            realAccountService.deleteRealAccount(user);
            return ResponseDto.success(((Map<String, Object>) result.getBody()).get("msg"));
        } catch (Exception e) {
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
    @LoginOnly(level = LoginOnly.Level.PARENT_OR_CHILD)
    public ResponseDto<?> BankList(HttpServletRequest request) throws Exception {
        //토큰에 대한 사용자 userId
        log.info("은행 리스트 조회 함수 호출");
        Long userId = (Long) request.getAttribute("userId");
        Map<String, String> map = new HashMap<>();
        try {
            log.info("OPEN BANK API 호출 이전");
            APIResultDto result = APIBuilder.build()
                    .url(url + "/openbanking/bank/image")
                    .method(HttpMethod.GET)
                    .execute();
            log.info("OPEN BANK API 호출 이후");
            return ResponseDto.success(((Map<String, Object>) result.getBody()).get("data"));
        } catch (Exception e) {
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
    @LoginOnly(level = LoginOnly.Level.PARENT_OR_CHILD)
    public ResponseDto<?> findMyRealAccount(HttpServletRequest request) throws Exception {
        //토큰에 대한 사용자 userId
        Long userId = (Long) request.getAttribute("userId");
        Map<String, String> map = new HashMap<>();
        try {
            RealAccountRes realAccount = realAccountService.findRealAccount(userId);
            return ResponseDto.success(realAccount);
        } catch (VirtualAccountException e) {
            return ResponseDto.fail(e);
        }
    }

    //실계좌 유효성 체크
    @PostMapping("/check")
    @Operation(summary = "계좌이체시 실 계좌 유효성 체크", description = "실계좌 유효성 체크")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "404", description = "일치하는 고객이 없을 때 발생.")
    })
    @LoginOnly(level = LoginOnly.Level.PARENT_OR_CHILD)
    public ResponseDto<?> findRealAccount(@RequestBody ReceiveReq receiveReq, HttpServletRequest request) throws Exception {
        Map<String, String> map = new HashMap<>();
        try {
            APIResultDto result = APIBuilder.build()
                    .url(url + "/openbanking/inquiry/receive")
                    .method(HttpMethod.POST)
                    .body(receiveReq)
                    .execute();
            System.out.println(((Map<String, Object>) result.getBody()).get("data"));
            return ResponseDto.success(((Map<String, Object>) result.getBody()).get("data"));
        } catch (Exception e) {
            Map<String, String> errorCode = ObjectMapper.findErrorCode(e.getMessage());
            return ResponseDto.fail(errorCode);
        }
    }

    //충전 (출금계좌 → 가상계좌)
    @PostMapping("/charge")
    @Operation(summary = "등록한 출금 계좌에서 가상 계좌로 돈 출금", description = "충전")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
    })
    @LoginOnly(level = LoginOnly.Level.PARENT_OR_CHILD)
    public ResponseDto<?> chargeMoney(@RequestBody ChargeAccountRes chargeAccountRes, HttpServletRequest request) throws Exception {
        //토큰에 대한 사용자 userId
        Long userId = (Long) request.getAttribute("userId");
        Map<String, String> map = new HashMap<>();
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
            virtualAccountService.charge(userId, chargeAccountRes.getMoney(), chargeAccountRes.getType());
            return ResponseDto.success(((Map<String, Object>) result.getBody()).get("data"));
        } catch (VirtualAccountException e) {
            Map<String, String> errorCode = ObjectMapper.findErrorCode(e.getMessage());
            return ResponseDto.fail(errorCode);
        }catch (Exception e) {
            Map<String, String> errorCode = ObjectMapper.findErrorCode(e.getMessage());
            return ResponseDto.fail(errorCode);
        }
    }

    //계좌이체 (가상계좌 → 실계좌)
    @PostMapping("/pay")
    @Operation(summary = "가상 계좌에서 실 계좌로 송금(나에게로 보내기 혹은 타인에게 보내기)", description = "가상에서 실계좌로 송금")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "가상 계좌에서 실 계좌로 송금 완료"),
            @ApiResponse(responseCode = "402", description = "가상 계좌의 잔액 부족")
    })
    @LoginOnly(level = LoginOnly.Level.PARENT_OR_CHILD)
    public ResponseDto<?> virtualToReal(@RequestBody VirtualToRealReq payment, HttpServletRequest request) throws Exception {
        //토큰에 대한 사용자 userId
        Long userId = (Long) request.getAttribute("userId");
        Map<String, String> map = new HashMap<>();
        if (payment.getPrintContent().isBlank()) {
            payment.setPrintContent(payment.getName() + "결제");
        }
        try {
            //가상 계좌 잔액이 출금 요청 잔액 보다 작으면 exception
            Long balance = virtualAccountService.userBalance(userId);
            if (balance < payment.getTranAmt()) throw new VirtualAccountException(402, "계좌의 잔액이 부족합니다!");
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
            if (userService.findNameByUserId(userId).equals(payment.getName())) {
                virtualAccountService.paymentMe(userId, payment.getTranAmt(), payment.getType());
            }
            //타인의 실계좌로 보내기
            else {
                virtualAccountService.paymentAnother(userId, payment.getTranAmt(), payment.getName(), payment.getType());
            }
            return ResponseDto.success(((Map<String, Object>) result.getBody()).get("data"));
        } catch (VirtualAccountException e) {
                Map<String, String> errorCode = new HashMap<>();
                errorCode.put("code", Integer.toString(e.getCode()));
                errorCode.put("error", e.getMessage());
                return ResponseDto.fail(errorCode);
        } catch (Exception e) {
            Map<String, String> errorCode = ObjectMapper.findErrorCode(e.getMessage());
            return ResponseDto.fail(errorCode);
        }
    }
}