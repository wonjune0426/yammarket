package com.example.yammarket.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
@Table(name = "file")
public class ImageFiles extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Posts posts;

    @Column(nullable = false)
    private String imageName;  // 파일 원본명

    @Column(nullable = false)
    private String imagePath;  // 파일 저장 경로

    @Column(nullable = false)
    private Long imageSize;



    @Builder
    public ImageFiles(String imageName, String imagePath, Long imageSize){
        this.imageName = imageName;
        this.imagePath = imagePath;
        this.imageSize = imageSize;
    }

    // post 정보 저장
    public void setPosts(Posts posts){
        this.posts = posts;

        // 게시글에 현재 파일이 존재하지 않는다면
        if(!posts.getImageFiles().contains(this))
            // 파일 추가
            posts.getImageFiles().add(this);
    }
}