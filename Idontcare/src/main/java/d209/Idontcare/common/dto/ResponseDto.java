package d209.Idontcare.common.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ResponseDto{
    private int code;
    private Object data;


    public ResponseDto(int code, Object data){
        this.code = code;
        this.data = data;
    }

}