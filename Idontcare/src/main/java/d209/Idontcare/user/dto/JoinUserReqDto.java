package d209.Idontcare.user.dto;


import d209.Idontcare.user.entity.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter @Setter @ToString
public class JoinUserReqDto {

    @Schema(description = "유저 ID", example = "123")
    @NotNull
    private Long userId;

    @Schema(description = "유저 타입", example = "PARENT | CHILD", allowableValues = {"PARENT", "CHILD"})
    @NotNull
    private Role role;
    
    @Schema(description = "이름", example = "신지훈")
    @NotBlank
    private String name;

    @Schema(description = "유저 닉네임", example = "nickname")
    @NotBlank
    private String nickName;
    
    @Schema(description = "생년 월일", example = "19980101")
    @NotBlank
    private String birth;
    
    @Schema(description = "휴대폰 번호", example = "01012345678")
    @NotBlank
    private String phoneNumber;

    @Schema(description = "유저 이메일", example = "test@naver.com")
    @Email
    private String email;
    
    @Schema(description = "가상 계좌 비밀번호", example = "123456")
    private Integer password;

}
