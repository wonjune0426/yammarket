package com.example.yammarket.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
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


}
