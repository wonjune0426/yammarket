package com.example.yammarket.controller;


import com.example.yammarket.model.Test;
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
        return "aa";
    }

    @GetMapping("/test")
    public Test test() {
        Test testjson = new Test();
        testjson.setMsg("it worked");
        return testjson;
    }

}
