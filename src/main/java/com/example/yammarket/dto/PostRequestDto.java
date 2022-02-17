package com.example.yammarket.dto;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Getter
public class PostRequestDto {
    private String userId;
    private String title;
    private String postDesc;
    private Long fileId;
}
