package com.example.yammarket.controller;

import com.example.yammarket.dto.SignupRequestDto;
import com.example.yammarket.model.Users;
import com.example.yammarket.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;


    // 회원 로그인 페이지
    @PostMapping("/user/login")
    public String loginPage(HttpServletResponse response) throws IOException  {
        String result = userService.login(response);
        return result;
    }

    @PostMapping("/user/logout")
    public String logoutPage (HttpServletResponse response) throws IOException {
        String result = userService.logoutPage(response);
        return result;
    }


    // 회원 가입 요청 처리
    @PostMapping("/user/signup")
    public Boolean registerUser(@RequestBody SignupRequestDto requestDto) {
        Boolean result = userService.registerUser(requestDto);
        return result;

    }

}

