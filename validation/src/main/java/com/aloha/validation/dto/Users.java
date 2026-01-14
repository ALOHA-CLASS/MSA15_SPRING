package com.aloha.validation.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;

@Data
public class Users {

    private Integer no;

    @Size(min = 4, max = 64, message = "아이디는 4자 이상 64자 이하로 입력하세요.")
    private String id;

    @NotBlank(message = "아이디는 필수입니다.")
    @Pattern(
      regexp = "^[A-Za-z0-9]{6,20}$",
      message = "아이디는 영문과 숫자만 가능하며 6~20자 이내로 작성해야합니다."
    )
    private String username;

    @NotBlank(message = "비밀번호는 필수입니다.")
    @Pattern(
      regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
      message = "비밀번호는 8자 이상이며, 영문, 숫자, 특수문자를 포함해야합니다."
    )
    private String password;

    @NotBlank(message = "이름은 필수입니다.")
    @Size(min = 2, max = 10, message = "이름은 2~10글자 이내로 작성해야합니다.")
    private String name;

    @Email(message = "이메일 형식이 아닙니다.")
    @NotBlank(message = "이메일은 필수입니다.")
    private String email;

    private Date createdAt;
    private Date updatedAt;

}
