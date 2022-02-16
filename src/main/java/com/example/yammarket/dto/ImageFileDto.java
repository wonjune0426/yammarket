package com.example.yammarket.dto;


import com.example.yammarket.model.ImageFiles;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.transaction.Transactional;

@Getter
@Setter
@Transactional
@NoArgsConstructor
public class ImageFileDto {
    //private Long id;
    private String origFilename;
    private String fileName;
    private String filePath;
    private Long fileSize;

    public ImageFiles toEntity(){
        ImageFiles build = ImageFiles.builder()
                .origFilename(origFilename)
                .fileName(fileName)
                .filePath(filePath)
                .fileSize(fileSize)
                .build();
        return build;
    }

    @Builder
    public ImageFileDto(String origFilename, String fileName, String filePath, Long fileSize){
        //this.id = id;
        this.origFilename = origFilename;
        this.fileName = fileName;
        this.filePath = filePath;
        this.fileSize = fileSize;
    }

}
