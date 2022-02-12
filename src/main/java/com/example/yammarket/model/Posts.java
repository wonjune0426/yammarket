package com.example.yammarket.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor // 기본생성자(인자 없는)를 만듭니다.
@Getter
@Entity
public class Posts extends TimeStamped {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String desc;

//    @Column(nullable = false)
//    private String image_url;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "posts")
    private List<Bookmarks> bookmarks;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "posts")
    private List<Comments> comments;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "posts")
//    private List<ImageFiles> imageFiles;

}
