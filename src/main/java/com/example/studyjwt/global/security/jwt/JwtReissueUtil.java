package com.example.studyjwt.global.security.jwt;

import com.example.studyjwt.global.security.auth.AuthDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class JwtReissueUtil { // 토큰 재발급 관리자

    private final JwtProvider jwtProvider;
    private final JwtPropertise jwtPropertise;
    private final AuthDetailsService authDetailsService;
}
