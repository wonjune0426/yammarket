package com.example.yammarket.model;

import com.example.yammarket.dto.ImageFileDto;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    // @Column(name = "file_id")
  
    @Column(nullable = false)
    private String origFilename;

    @Column(nullable = false)
    private String fileName;

    @Column(nullable = false)
    private String filePath;

    @Column(nullable = false)
    private Long fileSize;

//    @ManyToOne
//    @JoinColumn(name = "post_id", nullable = false)
//    private Posts posts;

    @Builder
    public ImageFiles(Long id, String origFilename, String fileName, String filePath, Long fileSize){
        this.id = id;
        this.origFilename = origFilename;
        this.fileName = fileName;
        this.filePath = filePath;
        this.fileSize = fileSize;
    }

    public ImageFiles(ImageFileDto fileDto){
        this.origFilename = fileDto.getOrigFilename();
        this.fileName = fileDto.getFileName();
        this.filePath = fileDto.getFilePath();
        this.fileSize = fileDto.getFileSize();
        System.out.println("~~~~~");
    }
}

