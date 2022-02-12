package com.example.yammarket.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Optional;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Bookmarks {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)

    private Users users;

    @ManyToOne
    @JoinColumn(name = "post_id",nullable = false)
    private Posts posts;

    public Bookmarks(Users users, Posts posts) {
        this.users=users;
        this.posts=posts;
    }



}
