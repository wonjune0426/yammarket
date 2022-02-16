package com.example.yammarket.dto;

import com.example.yammarket.model.Posts;
import com.example.yammarket.model.Users;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
public class BookmarkRequestDto {

    private Long id;
    private String userId;
    private Posts posts;
}
