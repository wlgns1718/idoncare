package d209.Idontcare.account.dto.res;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DailyHistoryRes {
  
  @Schema(description = "날짜", example = "10.01 | 5월")
  private String day;
  
  @Schema(description = "수입", example = "10000")
  private Long earn;
  
  @Schema(description = "지출", example = "5000")
  private Long expend;
  
  public DailyHistoryRes(LocalDateTime day, Long earn, Long expend){
    StringBuilder sb = new StringBuilder();
    String stringDay = sb.append(day.getMonthValue())
        .append(".")
        .append((day.getDayOfMonth() < 10 ? "0" : "") +day.getDayOfMonth())
        .toString();
    
    this.day = stringDay;
    this.earn = earn;
    this.expend = expend;
  }
}
