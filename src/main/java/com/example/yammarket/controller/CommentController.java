package com.example.yammarket.controller;

import com.example.yammarket.dto.CommentRequestDto;
import com.example.yammarket.model.Comments;
import com.example.yammarket.model.Users;
import com.example.yammarket.repository.UserRepository;
import com.example.yammarket.security.TokenUser;
import com.example.yammarket.security.UserDetailsImpl;
import com.example.yammarket.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import java.util.List;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final UserRepository userRepository;

    //댓글작성
    @PostMapping("/comments/{postId}")
    public boolean commentsWrite(
            @PathVariable Long postId,
            @RequestBody CommentRequestDto commentRequestDto,
//            @AuthenticationPrincipal UserDetailsImpl userDetails
            HttpServletRequest request) throws Exception {
        TokenUser tokenUser=new TokenUser(userRepository);
        Users users=tokenUser.getUser(request);
        return commentService.commentsWrite(postId,commentRequestDto,users);
    }


    //댓글 수정
    @PutMapping("/comments/{commentId}")
    public boolean commentUpdate(@PathVariable Long commentId,
                                 @RequestBody CommentRequestDto commentRequestDto,
//                                 @AuthenticationPrincipal UserDetailsImpl userDetails
                                 HttpServletRequest request
    ) throws Exception {
        TokenUser tokenUser=new TokenUser(userRepository);
        Users users=tokenUser.getUser(request);
        return commentService.commentUpdate(commentId,commentRequestDto,users);
    }


    //댓글 List조회
    @GetMapping("/comments/{postId}")
    public List<Comments> getComments(@PathVariable Long postId){
        return commentService.getComments(postId);
    }


    //댓글 삭제
    @DeleteMapping("/comments/{commentId}")
    public boolean commentDelete(@PathVariable Long commentId,
//                                 @AuthenticationPrincipal UserDetailsImpl userDetails
                                 HttpServletRequest request
    ) throws Exception {
        TokenUser tokenUser=new TokenUser(userRepository);
        Users users=tokenUser.getUser(request);
        return commentService.deleteComment(commentId,users);
    }

}
