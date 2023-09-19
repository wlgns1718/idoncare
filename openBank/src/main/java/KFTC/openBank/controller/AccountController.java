package KFTC.openBank.controller;

import KFTC.openBank.dto.*;
import KFTC.openBank.exception.AccountException;
import KFTC.openBank.exception.BackAccountException;
import KFTC.openBank.exception.MobileException;
import KFTC.openBank.exception.TransactionHistoryException;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/openbanking")
@RequiredArgsConstructor
@CrossOrigin("*")
@Tag(name = "AccountController", description = "계좌 관련 컨트롤러")
public class AccountController {

    public final AccountService accountService;
    public final MobileService mobileService;
    public final UserService userService;

    //1. OAuth인증 accessToken 발급
    @Operation(operationId = "Auth", summary = "OAuth인증", description = "OAuth인증 accessToken", tags = {"AccountController"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "토큰을 발급하였습니다.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))
            ),
            @ApiResponse(responseCode = "404", description = "사용자 인증을 요청한 이용자의 정보가 올바르지 않을 때")
    })
    @PostMapping("/oauth/2.0/token")
    public ResponseEntity<?> token(@RequestBody AuthRequestDto authRequestDto, HttpServletRequest httpServletRequest) {
        try {
            if (mobileService.findMobile(authRequestDto)) {
                userService.createUser(authRequestDto);
            }
        } catch (MobileException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDto("404", e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto("500", e.getMessage(), null));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto("200", "토큰 발급 성공", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c"));
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
    @GetMapping("/account/balance/fin_num")
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
    @GetMapping("/inquiry/real_name")
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
    @GetMapping("/account/transaction_list/fin_num")
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
//    @PostMapping("/transfer/deposit/fin_num")
//    public ResponseEntity<?> deposit(@RequestBody WithdrawRequestDto withdrawRequestDto, HttpServletRequest request){
//        String token = request.getHeader("Authorization");
//        try{
//            InquiryResponseDto realName = accountService.findRealName(inquiryRequestDto);
//            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto("200", "계좌 실명 조회 완료", realName));
//        } catch (AccountException e){
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDto("400", e.getMessage(), null));
//        }
//    }

    //6.출금 이체
    @Operation(operationId = "withdraw", summary = "출금 이체", description = "고객의 계좌에서 출금 이체", tags = {"AccountController"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 출금 이체를 성공하였습니다.",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = WithdrawReponseDto.class))
            ),
            @ApiResponse(responseCode = "404", description = "핀테크 이용 번호가 등록 된 것이 아닐 때, 잔액 부족, 입금을 원하는 계좌가 없을 때"),
            @ApiResponse(responseCode = "500", description = "출금 또는 입금 중 오류가 발생.")
        })
    @PostMapping("/transfer/withdraw/fin_num")
    public ResponseEntity<?> withdraw(@RequestBody WithdrawRequestDto withdrawRequestDto, HttpServletRequest request){
        withdrawRequestDto.setTranDtime(LocalDateTime.now());
        String token = request.getHeader("Authorization");
        try{
            WithdrawReponseDto withdraw = accountService.withdrawLogic(withdrawRequestDto);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto("200", "출금 완료", withdraw));
        } catch (AccountException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDto("404 ", e.getMessage(), null));
        }catch (BackAccountException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto("500", e.getMessage(), null));
        }
    }
}
