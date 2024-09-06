package com.example.studyjwt.global.security.jwt;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Base64;

@Getter
@Configuration
//@ConfigurationProperties(prefix = "jwt")
public class JwtPropertise { // 환경을 세팅해주는 값을 필드로 구현

    @Value("${jwt.secret_key}") // 헤더와 페이로드를 이용해 signature 를 만들때(해싱할 때) 사용됨.
    private String secretKey;

    @Value("${jwt.access_exp}") // 엑세스토큰 만료시간
    private Long accessExp;

    @Value("${jwt.refresh_exp}") // 리프레시토큰 만료시간
    private Long refreshExp;

    @Value("${jwt.header}") // 헤더
    private String header;

    @Value("${jwt.prefix}") // 인증방식(이토큰으로 누구를 인증할 것인지. 주로 bearer 이다)
    private String prefix;

    @PostConstruct // 객체가 생성되고 나서 자동으로 초기화함
    private void encodeSecreKey() {
        this.secretKey = Base64.getEncoder().encodeToString(this.secretKey.getBytes());
    }
}
