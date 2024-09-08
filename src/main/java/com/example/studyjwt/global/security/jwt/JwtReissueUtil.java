package com.example.studyjwt.global.security.jwt;

import com.example.studyjwt.global.exception.JwtExpiredException;
import com.example.studyjwt.global.security.TokenResponse;
import com.example.studyjwt.global.security.auth.AuthDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class JwtReissueUtil { // 토큰 재발급 관리자

    private final JwtProvider jwtProvider;
    private final JwtPropertise jwtPropertise;
    private final AuthDetailsService authDetailsService;

    public TokenResponse reissue(String refreshToken) {
        if(!isRefreshToken(refreshToken)) {
            throw new JwtException("not refresh token");
        }
        String accontId = getId(refreshToken);

        return TokenResponse.builder()
                .accessToken(jwtProvider.createAccessToken(accontId))
                .refreshToken(refreshToken)
                .build();
    }

    private String getId(String token) {return getClaims(token).getSubject();}

    private Claims getClaims(String token) {
        try {
            return Jwts
                    .parser()
                    .setSigningKey(jwtPropertise.getSecretKey())
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e){
            throw new JwtExpiredException();
        }
    }

    private boolean isRefreshToken(String token) { //refreshtoken 인지 확인
        return getClaims(token).get("type").equals("refresh");
    }

    public Authentication getAuthentication(String token) {
        Claims claims = getClaims(token);
        UserDetails userDetails = authDetailsService.loadUserByUsername(claims.getSubject());
        return new UsernamePasswordAuthenticationToken(userDetails,"",userDetails.getAuthorities());
    }
}
