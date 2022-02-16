package com.example.yammarket.controller;

import com.example.yammarket.dto.CommentRequestDto;
import com.example.yammarket.model.Comments;
import com.example.yammarket.security.UserDetailsImpl;
import com.example.yammarket.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;


    //댓글작성
    @PostMapping("/comments/{postId}")
    public boolean commentsWrite(
            @PathVariable Long postId,
            @RequestBody CommentRequestDto commentRequestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails){
        return commentService.commentsWrite(postId,commentRequestDto,userDetails);
    }


    //댓글 수정
    @PutMapping("/comments/{commentId}")
    public boolean commentUpdate(@PathVariable Long commentId,
                                 @RequestBody CommentRequestDto commentRequestDto,
                                 @AuthenticationPrincipal UserDetailsImpl userDetails){
        return commentService.commentUpdate(commentId,commentRequestDto,userDetails);
    }


    //댓글 List조회
    @GetMapping("/comments/{postId}")
    public List<Comments> getComments(@PathVariable Long postId){
        return commentService.getComments(postId);
    }


    //댓글 삭제
    @DeleteMapping("/comments/{commentId}")
    public boolean commentDelete(@PathVariable Long commentId,
                                 @AuthenticationPrincipal UserDetailsImpl userDetails){
        return commentService.deleteComment(commentId,userDetails);
    }

}
