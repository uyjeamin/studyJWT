package com.example.studyjwt.global.security.jwt;

import com.example.studyjwt.domain.auth.domain.RefreshToken;
import com.example.studyjwt.domain.auth.domain.repository.RefreshTokenRepostory;
import com.example.studyjwt.global.security.TokenResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.security.Key;
import java.security.KeyStore;
import java.util.Base64;
import java.util.Date;


@Component
@RequiredArgsConstructor
public class JwtProvider { // token 공급자

    private final JwtPropertise jwtPropertise;
    private final RefreshTokenRepostory refreshTokenRepostory;

    public TokenResponse createToken(String accountId) {
        return TokenResponse.builder()
                .accessToken(createAccessToken(accountId))
                .refreshToken(createRefreshToken(accountId))
                .build();
    }

    public String createAccessToken(String accountId) {
        Claims claims = Jwts.claims().setSubject(accountId);
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime()+ jwtPropertise.getAccessExp() * 1000))
                .signWith(SignatureAlgorithm.HS256, jwtPropertise.getSecretKey())
                .compact();
    }

    private String createRefreshToken(String accountId) {
        Date now = new Date();

        String refreshtoken = Jwts.builder()
                .setSubject(accountId)
                .claim("type","refresh")
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + jwtPropertise.getRefreshExp() * 1000)) // Date 는 시간을 밀리초 단위로 받음(1000 을 곱해준 이유)
                .signWith(SignatureAlgorithm.HS256, jwtPropertise.getSecretKey())
                .compact();

        refreshTokenRepostory.save(
                RefreshToken.builder()
                        .accountId(accountId)
                        .refreshToken(refreshtoken)
                        .expiration(jwtPropertise.getRefreshExp())
                        .build());

        return refreshtoken;
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(jwtPropertise.getHeader());

        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith(jwtPropertise.getPrefix())
        && bearerToken.length() >jwtPropertise.getPrefix().length() + 1) {
            return bearerToken.substring(7);//bearer 접두사가 있으면 그 뒤에 부분의 jwt 를
        }
        return null;
    }

    // 파싱
    private Claims getBody(String token) {
        try {
            return Jwts.parser().setSigningKey(jwtPropertise.getSecretKey()).parseClaimsJws(token).getBody();
        } catch(ExpiredJwtException e){
            throw new RuntimeException();
        }
    }

    public void validateToken(String token) {
        Claims body = getBody(token);
        if(body.getExpiration().before(new Date())) {
            throw new RuntimeException();
        }
    }

}
