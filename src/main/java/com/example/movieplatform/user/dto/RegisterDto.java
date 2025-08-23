package com.example.movieplatform.user.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class RegisterDto {

    @NotBlank(message = "이메일은 필수입니다.")
    @Email(message = "유효한 이메일을 입력해주세요.")
    @Size(max = 50, message = "이메일은 50자 이하여야 합니다.")
    private String email;

    @NotBlank(message = "비밀번호는 필수입니다.")
    @Size(min = 4, max = 20, message = "비밀번호는 4~20자여야 합니다.")
    private String password;

    @NotBlank(message = "이름은 필수입니다.")
    @Size(max = 20, message = "이름은 20자 이하여야 합니다.")
    private String name;

    @NotBlank(message = "전화번호는 필수입니다.")
    @Size(max = 13, message = "전화번호는 13자 이하여야 합니다.")
    private String phoneNumber;

    @NotNull(message = "생년월일은 필수입니다.")
    @Past(message = "생년월일은 과거 날짜여야 합니다.")
    private LocalDate birthDate;
}
