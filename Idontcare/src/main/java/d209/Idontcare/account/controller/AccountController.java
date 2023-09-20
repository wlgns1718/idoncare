package d209.Idontcare.account.controller;

import d209.Idontcare.TUser;
import d209.Idontcare.TUserRepository;
import d209.Idontcare.account.dto.req.TransactionRequestDto;
import d209.Idontcare.common.dto.APIResultDto;
import d209.Idontcare.common.dto.ResponseDto;
import d209.Idontcare.common.exception.AuthenticationException;
import d209.Idontcare.common.exception.CommonException;
import d209.Idontcare.common.exception.MustParentException;
import d209.Idontcare.common.service.APIService;
import d209.Idontcare.pocketmoney.dto.req.RegistRegularPocketMoneyReqDto;
import d209.Idontcare.pocketmoney.dto.res.RegistRegularPocketMoneyResDto;
import d209.Idontcare.pocketmoney.entity.RegularPocketMoney;
import d209.Idontcare.pocketmoney.service.PocketMoneyService;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

@Tag(name="가상 계좌 API")
@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountController {

    private final APIService apiService;

    //1. 계좌 확인
    @GetMapping("/transaction")
    public ResponseDto<?> findTransaction(@RequestBody TransactionRequestDto transactionRequestDto, HttpServletRequest request){
        APIResultDto get = apiService.get("http://127.0.0.1:8080/openbanking/account/transaction_list/fin_num", null, transactionRequestDto);
        return null;
    }

    //2. 실제 계좌에서 가상 계좌로 보내기

    //3. 가상 계좌에서 실계좌로 보내기

    //4. 가상 계좌에서 가상 계좌로 보내기

    //5. 결제 기능
}
