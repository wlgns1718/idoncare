package d209.Idontcare.pocketmoney.controller;

import d209.Idontcare.common.dto.ResponseDto;
import d209.Idontcare.common.exception.CommonException;
import d209.Idontcare.common.exception.MemberException;
import d209.Idontcare.pocketmoney.dto.req.RegistRegularPocketMoneyReqDto;
import d209.Idontcare.pocketmoney.dto.res.RegistRegularPocketMoneyResDto;
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

@Tag(name="용돈관리 API")
@RestController
@RequestMapping("/api/pocketmoney")
@RequiredArgsConstructor
public class PocketMoneyController {

    private static PocketMoneyService pocketMoneyService;
    
    //부모가 아이에게 정기용돈 등록
    @PostMapping("/regular")
    @Operation(summary="정기용돈 등록", description="부모가 아이에게 정기적으로 줄 용돈을 설정")
    @ApiResponses(value = {
        @ApiResponse(responseCode="200", description = "성공",
            content=@Content(schema = @Schema(implementation= RegistRegularPocketMoneyResDto.Swagger.class))),
        @ApiResponse(responseCode= MemberException.CODE, description = MemberException.DESCRIPTION)
    })
    public ResponseDto registRegularPocketMoney(@Valid @RequestBody RegistRegularPocketMoneyReqDto req){
        
        try{
            pocketMoneyService.registryRegularPocketMoney(req);
        } catch(CommonException e){
            return ResponseDto.fail(e);
        }
        
        return ResponseDto.success(new RegistRegularPocketMoneyResDto());
    }
}
