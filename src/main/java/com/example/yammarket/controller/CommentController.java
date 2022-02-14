package com.example.yammarket.controller;

import com.example.yammarket.model.Comments;
import com.example.yammarket.security.UserDetailsImpl;
import com.example.yammarket.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;


    //댓글작성
    @PostMapping("/comments/{postId}")
    public boolean commentsWrite(
            @PathVariable Long postId,
            @RequestBody String comment,
            @AuthenticationPrincipal UserDetailsImpl userDetails){
        return commentService.commentsWrite(postId,comment,userDetails);
    }


    //댓글 수정
    @PutMapping("/comments/{commentId}")
    public boolean commentUpdate(@PathVariable Long commentId,
                                 @RequestBody String comment,
                                 @AuthenticationPrincipal UserDetailsImpl userDetails){
        return commentService.commentUpdate(commentId,comment,userDetails);
    }


    //댓글 List조회
    @GetMapping("/comments/{postId}")
    public List<Comments> getComments(@PathVariable Long postId){
        return commentService.getComments(postId);
    }


    //댓글 삭제
    @DeleteMapping("/comments/{commentId}")
    public boolean commentDelete(@PathVariable Long commentId){
        return commentService.deleteComment(commentId);
    }

}
