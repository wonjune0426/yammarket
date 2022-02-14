package com.example.yammarket.dto;

import com.example.yammarket.model.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ImageFileDto {

    private String imageName;
    private String imagePath;
    private Long imageSize;

}
