package com.example.yammarket.model;

import com.example.yammarket.dto.SignupRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Bookmarks {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userId")
    private Users users;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Posts posts;

    public Bookmarks(Users users, Posts posts) {
        this.users=users;
        this.posts=posts;
    }



}
