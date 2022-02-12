package com.example.yammarket.service;
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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

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


    public String loginPage (HttpServletResponse response) throws IOException {
        if (!isAuthenticated()) {
//            response.setContentType("text/html; charset=UTF-8");
//            PrintWriter out = response.getWriter();
//            out.println("<script>alert('로그인이 필요한 기능입니다');</script>");
//            out.flush();
            return "login";
        }else{
            return "redirect:/";
        }

    }

    public String login(HttpServletResponse response) throws IOException  {
        if (isAuthenticated()) {
//            response.setContentType("text/html; charset=UTF-8");
//            PrintWriter out = response.getWriter();
//            out.println("<script>alert('이미 로그인 상태입니다.'); history.go(-1);</script>");
//            out.flush();
            return "redirect:/";
        }else{
            return "login";
        }

    }

    public String logoutPage (HttpServletResponse response) throws IOException {
        if (!isAuthenticated()) {
//            response.setContentType("text/html; charset=UTF-8");
//            PrintWriter out = response.getWriter();
//            out.println("<script>alert('이미 로그아웃 상태입니다.'); history.go(-1);</script>");
//            out.flush();
            return "login";
        }else{
            return "redirect:/user/logout";
        }
    }

    public String accessDenied(@RequestParam(value = "exception",required = false) String exception,
                               Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Users user = (Users)authentication.getPrincipal();
        model.addAttribute("userId",user.getUserId());
        model.addAttribute("exception",exception);
        return "denied";
    }


}