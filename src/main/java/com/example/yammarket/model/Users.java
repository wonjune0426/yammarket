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
    private String user_id;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String password;

    public Users(String user_id, String nickname, String password) {
        this.user_id=user_id;
        this.nickname=nickname;
        this.password=password;
    }

    public Users(SignupRequestDto requestDto) {
        this.user_id=requestDto.getUser_id();
        this.nickname=requestDto.getNickname();
        this.password=requestDto.getPassword();
    }

    //@OneToMany(fetch = FetchType.LAZY, mappedBy = "users")
    //private List<Posts> posts;


}
