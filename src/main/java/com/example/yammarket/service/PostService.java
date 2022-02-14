package com.example.yammarket.service;

import com.example.yammarket.Handler.ImageFileHandler;
import com.example.yammarket.dto.PostRequestDto;
import com.example.yammarket.model.ImageFiles;
import com.example.yammarket.model.Posts;
import com.example.yammarket.repository.ImageFileRepository;
import com.example.yammarket.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final ImageFileRepository imageFileRepository;
    private final ImageFileHandler imageFileHandler;

    @Transactional
    public List<Posts> getpostList(){
        return postRepository.findAllByOrderByModifiedAtDesc();
    }

    @Transactional
    public Posts viewPostInfo(Long postId){
        Posts posts = postRepository.findById(postId).orElseThrow(
                () -> new NullPointerException("id가 존재하지 않습니다.")
        );

        return posts;
    }

    @Transactional
    public Boolean updatePost(Long id, PostRequestDto requestDto){
        Posts posts = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        try {
            posts.update(requestDto);
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Transactional
    public Boolean createPostInfo(PostRequestDto requestDto, List<MultipartFile> imageFiles) throws Exception {
        Posts posts = new Posts(requestDto);

        List<ImageFiles> imageFilesList = imageFileHandler.parseFileInfo(imageFiles);

        // 파일이 존재할 때에만 처리
        if(!imageFilesList.isEmpty()){
            for(ImageFiles imageFiles_list : imageFilesList)
                // 파일을 DB에 저장
                posts.addImageFiles(imageFileRepository.save(imageFiles_list));
        }

        try {
            System.out.println("~~~ 1");
            postRepository.save(posts);
            System.out.println("~~~ 2");
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Transactional
    public Boolean deletePostService(Long postId){
        try{
            postRepository.deleteById(postId);
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
