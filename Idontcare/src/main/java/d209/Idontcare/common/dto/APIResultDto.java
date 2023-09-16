package d209.Idontcare.common.dto;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class APIResultDto<Header, Body> {
  HttpStatus status;
  Header header;
  Body body;
}
