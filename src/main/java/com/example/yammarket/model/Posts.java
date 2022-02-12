package com.example.yammarket.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor // 기본생성자(인자 없는)를 만듭니다.
@Getter
@Entity
@Setter
public class Posts extends TimeStamped {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String desc;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private Users users;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "posts")
    private List<Bookmarks> bookmarks;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "posts")
    private List<Comments> comments;

}
