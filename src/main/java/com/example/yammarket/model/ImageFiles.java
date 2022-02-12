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
    private String imageName;

    @Column(nullable = false)
    private String imagePath;

    @Column(nullable = false)
    private String imageSize;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Posts posts;

}
