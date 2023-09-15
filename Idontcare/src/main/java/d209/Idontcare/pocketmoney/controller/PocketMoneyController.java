package d209.Idontcare.pocketmoney.controller;

import d209.Idontcare.TUser;
import d209.Idontcare.TUserRepository;
import d209.Idontcare.common.dto.ResponseDto;
import d209.Idontcare.common.exception.AuthenticationException;
import d209.Idontcare.common.exception.CommonException;
import d209.Idontcare.common.exception.MustParentException;
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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

@Tag(name="용돈관리 API")
@RestController
@RequestMapping("/api/pocketmoney")
@RequiredArgsConstructor
public class PocketMoneyController {

    private final TUserRepository TUserRepository;
    private final PocketMoneyService pocketMoneyService;
    
    //부모가 아이에게 정기용돈 등록
    @PostMapping("/regular")
    @Operation(summary="정기용돈 등록", description="부모가 아이에게 정기적으로 줄 용돈을 설정")
    @ApiResponses(value = {
        @ApiResponse(responseCode="200", description = "성공",
            content=@Content(schema = @Schema(implementation= RegistRegularPocketMoneyResDto.class))),
        @ApiResponse(responseCode= AuthenticationException.CODE, description = AuthenticationException.DESCRIPTION),
        @ApiResponse(responseCode= MustParentException.CODE, description = MustParentException.DESCRIPTION)
    })
    public ResponseDto registRegularPocketMoney(@Valid @RequestBody RegistRegularPocketMoneyReqDto req){
        
        /* TODO : 요청한 사람에 대해 검증 코드 추가 필요 */
        TUser parent = TUserRepository.findAll().stream().filter((u) -> u.getType() == TUser.Type.PARENT).toList().get(0);
        
        if(parent == null)  //로그인 되지 않은 경우
            return ResponseDto.fail(new AuthenticationException());
        
        try{
            RegularPocketMoney saved = pocketMoneyService.registryRegularPocketMoney(parent, req, LocalDateTime.now());
            RegistRegularPocketMoneyResDto result = RegistRegularPocketMoneyResDto.builder()
                                                            .regularPocketMoneyId(saved.getRegularPocketMoneyId())
                                                            .build();
            return ResponseDto.success(result);
        } catch(CommonException e){
            return ResponseDto.fail(e);
        }
        
    }
}
