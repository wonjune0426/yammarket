package com.example.yammarket.controller;

import com.example.yammarket.security.UserDetailsImpl;
import com.example.yammarket.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/comments/{postId}")
    public boolean commentsWrite(
            @PathVariable Long postId,
            @RequestBody String comment,
            @AuthenticationPrincipal UserDetailsImpl userDetails){
        return commentService.commentsWrite(postId,comment,userDetails);
    }

    @PutMapping("/comments/{commentId}")
    public boolean commentUpdate(@PathVariable Long commentId,
                                 @RequestBody String comment,
                                 @AuthenticationPrincipal UserDetailsImpl userDetails){
        return commentService.commentUpdate(commentId,comment,userDetails);
    }

    @DeleteMapping("/comments/{commentId}")
    public boolean commentDelete(@PathVariable Long commentId){
        return commentService.deleteComment(commentId);
    }

}
