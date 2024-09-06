package com.example.studyjwt.domain.auth.domain;


import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@RedisHash("refreshToken")
public class RefashToken {
    @Id
    private String accountId;

    private String refreshToken;

    @TimeToLive //지정한 시간(초) 뒤에 자동으로 해시가 사라짐.
    private Long expiration;

    public RefashToken updateExiration(Long expiration){
        this.expiration = expiration;
        return this;
    }
}
