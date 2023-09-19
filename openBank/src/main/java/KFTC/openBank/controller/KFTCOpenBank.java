package KFTC.openBank.controller;


import KFTC.openBank.dto.MobileRequestDto;
import KFTC.openBank.dto.ResponseDto;
import KFTC.openBank.exception.MobileException;
import KFTC.openBank.service.OpenBankService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/openbanking/account")
@RequiredArgsConstructor
@CrossOrigin("*")
@Tag(name = "KFTCOpenBank", description = "오픈 뱅킹 컨트롤러")
public class KFTCOpenBank {

    private final OpenBankService openBankService;

//    @Operation(operationId = "oauth", summary = "OAuth인증", description = "OAuth인증을 한다..", tags = {"KFTCOpenBank"})
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Successful Operation"),
//            @ApiResponse(responseCode = "400", description = "Invalid"),
//            @ApiResponse(responseCode = "404", description = "Not found")
//    })
//    @GetMapping("/oauth/2.0/token/{name}")
//    public ResponseEntity oauth(@PathVariable("name") int i) {
//        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
//        System.out.println(request.getHeader("Accept-Language"));
//        if(i == 1){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDto(400,"테스트 실패", null));
//        }
//        Test helloWorld = new Test("Hello world");
//        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(200,"테스트 성공", helloWorld));
//    }


//    //1.1OAuth인증
//    @PostMapping("/oauth/2.0/authorization")
//    public ResponseEntity<?> authorization() {
//        Test helloWorld = new Test("Hello world");
//        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(HttpStatus.OK, "테스트 성공", helloWorld));
//    }
//    private final MobileService mobileService;
//
//
//    //3.계좌 실명 조회
//    @PostMapping("/inquiry/real_name")
//    public void realName(){
//    }
//
//    //4.거래 내역 조회
//    @GetMapping("/account/transaction_list/fin_num")
//    public void transactionList(){
//
//    }
//
//    //5.입금 이체
//    @PostMapping("/transfer/deposit/fin_num")
//    public void deposit(){
//
//    }
//
//    //6.출금 이체
//    @PostMapping("/transfer/withdraw/fin_num")
//    public void withdraw(){
//    }
}
