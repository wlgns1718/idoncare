package d209.Idontcare.pocketmoney.dto.res;

import d209.Idontcare.pocketmoney.entity.PocketMoneyRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Getter
public class GetPocketMoneyRequestResDto {
  private User parent;
  private User child;

  @Schema(description = "요청한 돈", example = "10000")
  private Integer amount;
  @Schema(description = "현재 상태", example = "REQUEST | CANCEL | ACCEPTED")
  private PocketMoneyRequest.Type type;
  
  @Schema(description = "요청한 날짜", example = "2022.02.02")
  private LocalDateTime createdAt;
  
  @Getter
  public static class Result{
    private List<GetPocketMoneyRequestResDto> list = new LinkedList<>();
    
    public Result(List<GetPocketMoneyRequestResDto> result){
      this.list = result;
    }
  }
}

@Getter
class User{
  @Schema(description = "유저 ID", example = "1")
  private Long id;
  @Schema(description = "유저 이름", example = "이제성")
  private String name;
}
