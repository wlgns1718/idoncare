package d209.Idontcare.account.controller;

import d209.Idontcare.account.dto.res.RealAccountRes;
import d209.Idontcare.account.exception.VirtualAccountException;
import d209.Idontcare.account.service.EncryptService;
import d209.Idontcare.account.service.RealAccountService;
import d209.Idontcare.common.APIBuilder;
import d209.Idontcare.common.ObjectMapper;
import d209.Idontcare.common.annotation.LoginOnly;
import d209.Idontcare.common.dto.APIResultDto;
import d209.Idontcare.common.dto.ResponseDto;
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

    @Value("${openbank.url}")
    private String url;

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
            System.out.println(result.getBody());
            return ResponseDto.success(((Map<String, Object>) result.getBody()).get("data"));
        } catch (Exception e){
            Map<String, String> errorCode = ObjectMapper.findErrorCode(e.getMessage());
            return ResponseDto.fail(errorCode);
        }
    }


    //계좌 충전 창에서 실계좌가 있는지 조회
    @GetMapping("/my")
    @Operation(summary = "실계좌 조회", description = "실계좌 조희")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "402", description = "계좌를 등록 하지 않았음")
    })
//    @LoginOnly(level = LoginOnly.Level.PARENT_ONLY)
    public ResponseDto<?> findReadAccount(HttpServletRequest request) throws Exception {
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

//    //충전 계좌 등록
//    @PostMapping("/create")
//    @Operation(summary = "충전 계좌 등록", description = "실계좌 조희")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "성공"),
//            @ApiResponse(responseCode = "402", description = "계좌를 등록 하지 않았음")
//    })
//    @LoginOnly(level = LoginOnly.Level.PARENT_OR_CHILD)
//    public ResponseDto findTransaction(@RequestBody AuthRequestDto authRequestDto, HttpServletRequest request) throws Exception{
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



}
