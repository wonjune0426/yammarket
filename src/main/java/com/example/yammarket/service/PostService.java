package com.example.yammarket.service;

import com.example.yammarket.dto.PostDto;

import com.example.yammarket.dto.PostRequestDto;
import com.example.yammarket.model.ImageFiles;
import com.example.yammarket.model.Posts;
import com.example.yammarket.model.Users;
import com.example.yammarket.repository.*;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final BookmarkRepository bookmarkRepository;
    private final CommentRepository commentRepository;
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

    // 로그인한 사용자와 게시물을 작성한 사용자가 같은지 확인
    public Boolean equalUserId(String userId){
        Optional<Users> user = userRepository.findByUserId(userId);
        if(user.isPresent())
            return true;

        Users users = userRepository.findByUserId(userId).orElseThrow(
                ()-> new IllegalArgumentException("유저 아이디가 다릅니다.")
        );

        return false;
    }


    @Transactional
    public Boolean createPostInfo(PostRequestDto requestDto){

        Posts posts = new Posts(requestDto);

        //List<ImageFiles> imageFilesList = imageFileHandler.parseFileInfo(imageFiles);

        // 파일이 존재할 때에만 처리
//        if(!imageFilesList.isEmpty()){
//            for(ImageFiles imageFiles_list : imageFilesList)
//                // 파일을 DB에 저장
//                posts.addImageFiles(imageFileRepository.save(imageFiles_list));
//        }

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
            commentRepository.deleteByPostId(postId);
            bookmarkRepository.deleteByPostId(postId);
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Transactional
    public Long savePost(PostDto postDto) {
        Posts posts = new Posts(postDto);
        return postRepository.save(posts).getId();
        //return postRepository.save(postDto.toEntity()).getId();
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
