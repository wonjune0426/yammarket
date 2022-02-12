package com.example.yammarket.controller;

import com.example.yammarket.dto.PostRequestDto;
import com.example.yammarket.model.Posts;
import com.example.yammarket.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;

    // 처음에는 그냥 메인페이지만 보여주는게 맞고
    // 메인페이지("/")로 가서 post의 list를 호출을 해주는게 맞다
    @GetMapping("/views/postList")
    public List<Posts> mainIndex(){
        return postService.getpostList();
    }

    // 일단 반환하는데 Boolean 형인데
    // user정보가 필요하면  @AuthenticationPrincipal UserDetailsImpl userDetails 를 사용하면 된다.
    @PostMapping("/posts/write")
    public Boolean createPost(@RequestBody PostRequestDto requestDto){
        return postService.createPostInfo(requestDto);
        //LocalDateTime cr = post.getCreatedAt();
        //LocalDateTime md = post.getModifiedAt();
    }

    //  @AuthenticationPrincipal UserDetailsImpl userDetails 넣을까 말까
    // 댓글은 댓글조회 url을 만들어서 불러주는게 맞는듯
    @GetMapping("/views/posts/{postId}")
    public Posts viewPost(@PathVariable Long postId){
        return postService.viewPostInfo(postId);
    }

    // Long 으로 반환해서 뭐하려고
    @PatchMapping("/posts/{postId}")
    public Boolean updatePost(@PathVariable Long postId, @RequestBody PostRequestDto requestDto){
        return postService.updatePost(postId, requestDto);
    }

    @DeleteMapping("/posts/{postId}")
    public Boolean deletePost(@PathVariable Long postId){
        return postService.deletePostService(postId);
    }

}
