package com.example.yammarket.service;

import com.example.yammarket.dto.ImageFileDto;
import com.example.yammarket.model.ImageFiles;
import com.example.yammarket.repository.ImageFileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ImageFileService {

    private final ImageFileRepository fileRepository;

    @Transactional
    public Long saveFile(ImageFileDto fileDto){
        ImageFiles imageFiles = new ImageFiles(fileDto);
        return fileRepository.save(imageFiles).getId();
        //return fileRepository.save(fileDto.toEntity()).getId();
    }

    @Transactional
    public ImageFileDto getFile(Long id){
        ImageFiles files = fileRepository.findById(id).get(); // null 처리 해줘야할 듯

        ImageFileDto fileDto = ImageFileDto.builder()
                .origFilename(files.getOrigFilename())
                .fileName(files.getFileName())
                .filePath(files.getFilePath())
                .fileSize(files.getFileSize())
                .build();
        return fileDto;
    }

    @Transactional
    public Boolean deleteFile(Long fileId){
        try {
            fileRepository.deleteById(fileId);
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /*@Transactional
    public String uploadImage(MultipartFile file) throws IllegalAccessException{
        String fileName = createFileName(file.getOriginalFilename());
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(file.getSize());
        objectMetadata.setContentType(file.getContentType());
        try (InputStream inputStream = file.getInputStream()) {
            s3Service.uploadFile(inputStream, objectMetadata, fileName);
        } catch(IOException e) {
            throw new IllegalAccessException(String.format("파일 변환 중에러가 발생하였습니다.(%s)", file.getOriginalFilename()));
        }
        return s3Service.getFileUrl(fileName);
    }

    //기존 확장자명을 유지한 채, 유니크한 파일의 이름을 생성하는 로직
    private String createFileName(String originalFileName) throws IllegalAccessException {
        return UUID.randomUUID().toString().concat(getFileExtension(originalFileName));
    }*/
}
