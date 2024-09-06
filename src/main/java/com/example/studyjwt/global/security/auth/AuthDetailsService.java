package com.example.studyjwt.global.security.auth;

import com.example.studyjwt.domain.user.domain.User;
import com.example.studyjwt.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String accountId) throws UsernameNotFoundException {
        User user = userRepository.findByAccountId(accountId)
                .orElseThrow(()-> new UsernameNotFoundException(""));
        AuthDetails authDetails = new AuthDetails(user.getAccontId(),user);
        return authDetails;
    }
}
