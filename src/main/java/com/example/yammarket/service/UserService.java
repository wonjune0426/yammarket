package com.example.yammarket.service;
import com.example.yammarket.dto.CheckIdRequestDto;
import com.example.yammarket.dto.SignupRequestDto;
import com.example.yammarket.model.Users;
import com.example.yammarket.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private final UserRepository userRepository;

    public Optional<Users> findByUserId(String id) { return userRepository.findByUserId(id); }

    public boolean registerUser(SignupRequestDto requestDto) {
// 회원 ID 중복 확인
        String userId = requestDto.getUserId();
        Optional<Users> found = userRepository.findByUserId(userId);

        if (found.isPresent()) {
            return false; //throw new IllegalArgumentException("중복된 사용자 ID 가 존재합니다.");
        }

// 패스워드 암호화
        String nickname = requestDto.getNickname();
        String password_tmp = requestDto.getPassword();
        String password = passwordEncoder.encode(password_tmp);

        Users user = new Users(userId, nickname, password);
        userRepository.save(user);
        return true;

    }

    public static boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || AnonymousAuthenticationToken.class.
                isAssignableFrom(authentication.getClass())) {
            return false;
        }
        return authentication.isAuthenticated();
    }

    public Boolean checkId(CheckIdRequestDto requestDto) {
        if(userRepository.findByUserId(requestDto.getUserId()).isPresent()){
            return true;
        }
        return false;
    }

}