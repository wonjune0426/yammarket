package com.example.yammarket.controller;

import com.example.yammarket.security.UserDetailsImpl;
import com.example.yammarket.service.CommentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentsService commentsService;

    @PostMapping("/comments/{postId}")
    public boolean commentsWrite(
            @PathVariable Long postId,
            @RequestBody String comment,
            @AuthenticationPrincipal UserDetailsImpl userDetails){
        return commentsService.commentsWrite(postId,comment,userDetails);
    }

    @PutMapping("/comments/{commentId}")
    public boolean commentUpdate(@PathVariable Long commentId,
                                 @RequestBody String comment,
                                 @AuthenticationPrincipal UserDetailsImpl userDetails){
        return commentsService.commentUpdate(commentId,comment,userDetails);
    }

    @DeleteMapping("/comments/{commentId}")
    public boolean commentDelete(@PathVariable Long commentId){
        return commentsService.deleteComment(commentId);
    }

}
