package com.example.yammarket.model;

import com.example.yammarket.dto.PostDto;
import com.example.yammarket.dto.PostRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.ArrayList;
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

    //@ManyToOne(cascade = CascadeType.REMOVE)
    @ManyToOne
    @JoinColumn(name = "user_id",nullable = true) // nullable = false
    private Users users;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "posts")
    private List<Bookmarks> bookmarks;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "posts")
    private List<Comments> comments;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "posts")
//    private List<ImageFiles> imageFiles;
    @Column(nullable = false)
    private Long fileId;

//    @OneToOne
//    @JoinColumn(name = "file_id")
//    private Posts posts;


    // 게시글 생성 시 이용할 생성자
    @Builder
    public Posts(PostRequestDto requestDto){
        this.title = requestDto.getTitle();
        this.desc = requestDto.getDesc();
        this.fileId = requestDto.getFileId();
    }

    public void update(PostRequestDto requestDto){
        this.title = requestDto.getTitle();
        this.desc = requestDto.getDesc();
        this.fileId = requestDto.getFileId();
    }

    public void update2(PostDto postDto){
        this.title = postDto.getTitle();
        this.desc = postDto.getDesc();
        this.fileId = postDto.getFileId();
    }

    @Builder
    public Posts(String user_id, String title, String desc, Long fileId){
        //this.users.getUserId() = user_id;
        this.title = title;
        this.desc = desc;
        this.fileId = fileId;
    }

//     public void addImageFiles(ImageFiles imageFiles) {
//         this.imageFiles.add(imageFiles);

//         // 게시글에 파일이 저장되어있지 않은 경우
//         if(imageFiles.getPosts() != this)
//             // 파일 저장
//             imageFiles.setPosts(this);
//     }
}
