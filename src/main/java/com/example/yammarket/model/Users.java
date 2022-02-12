package com.example.yammarket.model;

import com.example.yammarket.dto.SignupRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class Users {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String password;

    public Users(String userId, String nickname, String password) {
        this.userId=userId;
        this.nickname=nickname;
        this.password=password;
    }

    public Users(SignupRequestDto requestDto) {
        this.userId=requestDto.getUserId();
        this.nickname=requestDto.getNickname();
        this.password=requestDto.getPassword();
    }


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "users")
    private List<Posts> posts;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "users")
    private List<Comments> comments;

    @OneToMany(fetch =FetchType.LAZY,mappedBy = "users")
    private List<Bookmarks> bookmarks;

}
