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

//    @ManyToOne
//    @JoinColumn(name = "user_id",nullable = false)
//    private Users users;

    @Column(nullable = false)
    private Long postId;

    @Column(nullable = false)
    private String userId;

    public Bookmarks(String userId, Long postId) {
        this.userId=userId;
        this.postId=postId;
    }



}
