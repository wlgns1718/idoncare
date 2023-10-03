package KFTC.openBank.controller;

import KFTC.openBank.dto.*;
import KFTC.openBank.exception.*;
import KFTC.openBank.service.AccountService;
import KFTC.openBank.service.MobileService;
import KFTC.openBank.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/openbanking")
@RequiredArgsConstructor
@CrossOrigin("*")
@Slf4j
@Tag(name = "AccountController", description = "계좌 관련 컨트롤러")
public class AccountController {

    public final AccountService accountService;
    public final MobileService mobileService;
    public final UserService userService;

    //1-1 OAuth인증 accessToken 발급
    @Operation(operationId = "Auth", summary = "OAuth인증 이용자 등록", description = "OAuth인증 accessToken", tags = {"AccountController"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "토큰을 발급하였습니다.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))
            ),
            @ApiResponse(responseCode = "404", description = "사용자 인증을 요청한 이용자의 정보가 올바르지 않을 때"),
            @ApiResponse(responseCode = "409", description = "사용자가 이미 인증을 했을 때")
    })
    @PostMapping("/oauth/2.0/token")
    public ResponseEntity<?> token(@RequestBody AuthRequestDto authRequestDto, HttpServletRequest httpServletRequest) {
        try {
            if (mobileService.findMobile(authRequestDto)) {
                userService.createUser(authRequestDto);
            }
        } catch (MobileException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDto("404", e.getMessage(), null));
        } catch (UserException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto("409", e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto("500", e.getMessage(), null));
        }
        //가상 토큰 발급
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto("200", "토큰 발급 성공", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c"));
    }

    //1-2 OAuth인증 계좌 유효성 체크
    @Operation(operationId = "Auth", summary = "OAuth인증 계좌 등록", description = "OAuth인증 핀번호 발급", tags = {"AccountController"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "핀번호를 발급하였습니다.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))
            ),
            @ApiResponse(responseCode = "404", description = "계좌 번호, 은행과 사용자가 일치하지 않을 때"),
    })
    @PostMapping("/oauth/2.0/valid")
    public ResponseEntity<?> validAccount(@RequestBody AccountVerifiRequesDto accountVerifiReqDto, HttpServletRequest httpServletRequest) {
        try {
            accountService.validAccount(accountVerifiReqDto);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto("200", "계좌 유효성 체크 완료", null));
        } catch (MobileException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDto("404", e.getMessage(), null));
        } catch (UserException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto("409", e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto("500", e.getMessage(), null));
        }
    }

    //1-3 OAuth인증 계좌 등록
    @Operation(operationId = "Auth", summary = "OAuth인증 계좌 등록", description = "OAuth인증 핀번호 발급", tags = {"AccountController"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "핀번호를 발급하였습니다.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))
            ),
            @ApiResponse(responseCode = "404", description = "계좌 번호, 은행과 사용자가 일치하지 않을 때"),
    })
    @PostMapping("/oauth/2.0/regist")
    public ResponseEntity<?> pinAccount(@RequestBody AccountRegistRequesDto requesDto, HttpServletRequest httpServletRequest) {
        try {
            requesDto.setTranDtime(LocalDateTime.now());
            String pinNumber = accountService.registAccount(requesDto);
            Map<String, String> map = new HashMap<>();
            map.put("pinNumber", pinNumber);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto("200", "계좌 등록 완료", map));
        } catch (MobileException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDto("404", e.getMessage(), null));
        } catch (UserException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto("409", e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto("500", e.getMessage(), null));
        }
    }

    //1-4 OAuth인증 계좌 삭제
    @Operation(operationId = "Auth", summary = "OAuth인증 계좌 삭제", description = "OAuth인증 핀번호 삭제", tags = {"AccountController"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "핀번호를 삭제합니다.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))
            ),
    })
    @DeleteMapping("/oauth/2.0/pin")
    public ResponseEntity<?> pinNumberDelete(@RequestBody AccountDeleteRequestDto requesDto, HttpServletRequest httpServletRequest) {
        try {
            accountService.deleteAccount(requesDto);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto("200", "계좌 삭제 완료.", null));
        } catch (MobileException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDto("404", e.getMessage(), null));
        } catch (UserException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto("409", e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto("500", e.getMessage(), null));
        }
    }

    //2.잔액 조회
    @Operation(operationId = "balance", summary = "잔액 조회", description = "미리 등록한 계좌의 잔액 조회", tags = {"AccountController"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 잔액 조회하였습니다.",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = BalanceResponseDto.class))
            ),
            @ApiResponse(responseCode = "404", description = "요청한 핀테크 이용번호는 등록 되어 있지 않을 때")
    })
    @PostMapping("/account/balance/fin_num")
    public ResponseEntity<?> balance(@RequestBody BalanceRequestDto balanceRequestDto, HttpServletRequest request){
        balanceRequestDto.setTranDtime(LocalDateTime.now());
        String token = request.getHeader("Authorization");
        try{
            BalanceResponseDto balance = accountService.findBalance(balanceRequestDto.getFintechUseName());
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto("200", "잔액 조회 완료", balance));
        }catch (AccountException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDto("400", e.getMessage(), null));
        }
    }


    //3.계좌 실명 조회
    @Operation(operationId = "real_name", summary = "계좌 실명 조회", description = "은행에 계좌 실명 조회", tags = {"AccountController"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 실명을 조회하였습니다.",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = InquiryResponseDto.class))
            ),
            @ApiResponse(responseCode = "404", description = "요청한 계좌 정보에 해당하는 실명이 없을 때.")
    })
    @PostMapping("/inquiry/real_name")
    public ResponseEntity<?> realName(@RequestBody InquiryRequestDto inquiryRequestDto, HttpServletRequest request){
        inquiryRequestDto.setTranDtime(LocalDateTime.now());
        String token = request.getHeader("Authorization");
        try{
            InquiryResponseDto realName = accountService.findRealName(inquiryRequestDto);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto("200", "계좌 실명 조회 완료", realName));
        } catch (AccountException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDto("400", e.getMessage(), null));
        }
    }



    //4.거래 내역 조회
    @Operation(operationId = "transaction_list", summary = "거래 내역 조회", description = "은행 계좌 거래 내역 조회", tags = {"AccountController"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 거래 내역을 불러왔습니다..",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TransactionResponseDto.class))
            ),
            @ApiResponse(responseCode = "404", description = "거래 내역이 0일 때.")
    })
    @PostMapping("/account/transaction_list/fin_num")
    public ResponseEntity<?> transactionList(@RequestBody TransactionRequestDto transactionRequestDto, HttpServletRequest request){
        transactionRequestDto.setTranDtime(LocalDateTime.now());
        String token = request.getHeader("Authorization");
        try {
            TransactionResponseDto transactionList = accountService.findTransactionList(transactionRequestDto);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto("200","거래 내역 조회 완료", transactionList));
        }catch (TransactionHistoryException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDto("404", e.getMessage(), null));
        }
    }



    //5.입금 이체
    @Operation(operationId = "deposit", summary = "입금 이체", description = "핀테크에서 목적지에 입금 이체", tags = {"AccountController"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 입금 이체를 성공하였습니다.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DepositResponseDto.class))
            ),
            @ApiResponse(responseCode = "404", description = "핀테크 이용 번호가 등록 된 것이 아닐 때, 입금을 원하는 계좌가 없을 때"),
            @ApiResponse(responseCode = "500", description = "출금 또는 입금 중 오류가 발생.")
    })
    @PostMapping("/transfer/deposit/fin_num")
    public ResponseEntity<?> deposit(@RequestBody DepositRequestDto depositRequestDto, HttpServletRequest request){
        String token = request.getHeader("Authorization");
        try{
            DepositResponseDto depositResponseDto = accountService.depositLogic(depositRequestDto);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto("200", "입금 이체 완료", depositResponseDto));
        } catch (AccountException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDto("404 ", e.getMessage(), null));
        }catch (BankAccountException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto("500", e.getMessage(), null));
        }
    }


    //6.출금 이체
    @Operation(operationId = "withdraw", summary = "출금 이체", description = "고객의 계좌에서 핀테크로 출금 이체", tags = {"AccountController"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 출금 이체를 성공하였습니다.",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = WithdrawReponseDto.class))
            ),
            @ApiResponse(responseCode = "404", description = "핀테크 이용 번호가 등록 된 것이 아닐 때, 잔액 부족, 입금을 원하는 계좌가 없을 때, 이용자가 일치 하지 않을 때"),
            @ApiResponse(responseCode = "500", description = "출금 또는 입금 중 오류가 발생.")
        })
    @PostMapping("/transfer/withdraw/fin_num")
    public ResponseEntity<?> withdraw(@RequestBody WithdrawRequestDto withdrawRequestDto, HttpServletRequest request){
        String token = request.getHeader("Authorization");
        withdrawRequestDto.setTranDtime(LocalDateTime.now());
        try{
            WithdrawReponseDto withdraw = accountService.withdrawLogic(withdrawRequestDto);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto("200", "출금 이체 완료", withdraw));
        } catch (AccountException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDto("404 ", e.getMessage(), null));
        }catch (BankAccountException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto("500", e.getMessage(), null));
        }
    }


    //7.은행 이미지 경로 전송
    @Operation(operationId = "images", summary = "은행 이미지 출력", description = "각 은행의 원형 이미지 출력", tags = {"AccountController"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 출금 이체를 성공하였습니다.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BankRequestDto.class))
            )
    })
    @GetMapping("/bank/image")
    public ResponseEntity<?> allImages(){
        log.info("이미지 경로 전송 메서드 호출");
        try{
            List<BankRequestDto> bankRequestDtos = accountService.selectImage();
            log.info("이미지 경로 전송 메서드 반환");
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto("200", "이미지 경로 출력 완료", bankRequestDtos));
        } catch (BankAccountException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto("500", e.getMessage(), null));
        }
    }


    //8.수취조회
    @Operation(operationId = "receive", summary = "수취 조회", description = "핀테크에서 계좌와 은행 이름으로 수취 조회", tags = {"AccountController"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "해당 사용자 호출 완료",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ReceiveRequestDto.class))
            ),
            @ApiResponse(responseCode = "404", description = "일치하는 고객이 없을 때 발생."),
    })
    @PostMapping("/inquiry/receive")
    public ResponseEntity<?> inquirtReceive(@RequestBody ReceiveRequestDto receiveRequestDto, HttpServletRequest request){
        String token = request.getHeader("Authorization");
        try{
            ReceiveResponseDto client = accountService.findClient(receiveRequestDto);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto("200", "해당 사용자 호출 완료", client));
        } catch (AccountException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDto("404", e.getMessage(), null));
        }
    }
}

