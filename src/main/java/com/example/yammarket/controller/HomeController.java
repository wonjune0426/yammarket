package com.example.yammarket.controller;


import com.example.yammarket.security.UserDetailsImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import com.example.yammarket.model.Posts;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class HomeController {

    @GetMapping("/")
    public String ss(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return "ss";
    }

    /*@GetMapping("/")
    public String home(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        try{
            model.addAttribute("username", userDetails.getUsername());
        } catch (NullPointerException exception){
            System.out.println("username 값이 없습니다.");
            //exception.printStackTrace();
        }
        return "index";
    }*/
}
