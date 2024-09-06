package com.example.studyjwt.domain.auth.dto;


import jakarta.validation.constraints.NotBlank;

public class SigninRequest {
    @NotBlank(message = "accountId 는 NULL 이거나 공백,뛰어쓰기를 허용하지 않습니다.")
    private String accounId;
    @NotBlank(message = "password 는 NULL 이거나 공백,뛰어쓰기를 허용하지 않습니다.")
    private String password;
    @NotBlank(message = "device_token 는 NULL 이거나 공백,뛰어쓰기를 허용하지 않습니다.")
    private String deviceToken;

}
