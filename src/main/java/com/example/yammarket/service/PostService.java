package com.example.yammarket.service;

import com.example.yammarket.dto.PostDto;
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
    //private final ImageFileRepository imageFileRepository;
    //private final ImageFileHandler imageFileHandler;

    @Transactional
    public List<Posts> getpostList(){
        return postRepository.findAllByOrderByModifiedAtDesc();
    }

    @Transactional
    public Posts getPost(Long id){
        Posts posts = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("~~~post의 id가 존재하지 않습니다")
        );

        return posts;
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
    public Boolean updatePost2(Long id, PostDto postDto){
        Posts posts = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        try {
            posts.update2(postDto);
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Transactional
    public Boolean createPostInfo(PostRequestDto requestDto){
        Posts posts = new Posts(requestDto);

        try {
            postRepository.save(posts);
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

    @Transactional
    public Long savePost(PostDto postDto) {
        return postRepository.save(postDto.toEntity()).getId();
    }

    /*@Transactional
    public PostDto getPost(Long id){
        // 뒤에 .get() 안씀..
        Posts posts = postRepository.findById(id).get(); // null 처리 해줘야할 듯
        *//*Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("~~~post의 id가 존재하지 않습니다. 아마도")
        );*//*

        PostDto postDto = PostDto.builder()
                .userId(posts.getUsers().getUserId())
                .title(posts.getTitle())
                .desc(posts.getDesc())
                .fileId(posts.getFileId())
                .build();
        return postDto;
    }*/
}
