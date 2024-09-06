package com.example.studyjwt.domain.auth.domain.repository;

import com.example.studyjwt.domain.auth.domain.RefreshToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepostory extends CrudRepository<RefreshToken, String> {
}
