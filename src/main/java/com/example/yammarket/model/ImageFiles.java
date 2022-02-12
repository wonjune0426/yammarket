package com.example.yammarket.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class ImageFiles {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String image_name;

    @Column(nullable = false)
    private String image_path;

    @Column(nullable = false)
    private String image_size;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Posts posts;

    // 테스트용 커밋
}
