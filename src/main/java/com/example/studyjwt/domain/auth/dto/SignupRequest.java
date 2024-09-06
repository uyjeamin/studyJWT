package com.example.studyjwt.domain.auth.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class SignupRequest {

    @NotBlank(message = "account_id는 NULL, 공백, 뛰어쓰기를 허용하지 않습니다.")
    @Size(min = 5, max = 25 ,message = "account_id 는 5글자 이상, 25글자 이하여야 합니다.")
    private String accountId;

    @NotBlank(message = "password는 Null, 공백, 띄어쓰기를 허용하지 않습니다.")
    @Pattern(regexp = "(?=.*[a-z])(?=.*[0-9])(?=.*[!#$%&'()*+,./:;<=>?@＼^_`{|}~])[a-zA-Z0-9!#$%&'()*+,./:;" +
            "<=>?@＼^_`{|}~]{8,60}$",
            message = "password는 소문자, 숫자, 특수문자가 포함되어있어야 합니다.")
    private String password;

    @NotBlank(message = "email 은 공백,뛰어쓰기를 허용하지 않습니다.")
    private String email;



}
