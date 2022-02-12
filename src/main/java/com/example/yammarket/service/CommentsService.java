package com.example.yammarket.service;

import com.example.yammarket.model.Comments;
import com.example.yammarket.model.Posts;
import com.example.yammarket.model.Users;
import com.example.yammarket.repository.CommentsRepository;
import com.example.yammarket.repository.PostsRepository;
import com.example.yammarket.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentsService {

    private final CommentsRepository commentsRepository;
    private final PostsRepository postsRepository;

    public boolean commentsWrite(Long postId, String comment, UserDetailsImpl userDetails) {
        Posts posts=postsRepository.findById(postId).orElseThrow(
                ()-> new NullPointerException("댓글을 작성할 게시글이 없습니다."));

        Users users=userDetails.getUsers();

        Comments comments=new Comments(comment,users,posts);

        commentsRepository.save(comments);

        return true;
    }

    public boolean commentUpdate(Long commentId, String comment, UserDetailsImpl userDetails) {
        Comments comments=commentsRepository.findById(commentId).orElseThrow(
                ()->new NullPointerException("수정할 댓글이 없습니다."));
//        Users users=userDetails.getUsers();
        comments.setComment(comment);
        commentsRepository.save(comments);
        return true;
    }

    public boolean deleteComment(Long commentId) {
        Comments comments=commentsRepository.findById(commentId).orElseThrow(
                ()->new NullPointerException("삭제할 댓글이 없습니다."));
        commentsRepository.deleteById(comments.getId());
        return true;
    }
}
