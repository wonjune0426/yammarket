package com.example.yammarket.service;

import com.example.yammarket.dto.CommentRequestDto;
import com.example.yammarket.model.Comments;
import com.example.yammarket.model.Posts;
import com.example.yammarket.model.Users;
import com.example.yammarket.repository.CommentRepository;
import com.example.yammarket.repository.PostRepository;
import com.example.yammarket.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postsRepository;

    @Transactional
    public boolean commentsWrite(Long postId, CommentRequestDto commentRequestDto, UserDetailsImpl userDetails) {
        Posts posts=postsRepository.findById(postId).orElseThrow(
                ()-> new NullPointerException("댓글을 작성할 게시글이 없습니다."));

        Users users=userDetails.getUsers();
        Comments comments=new Comments(commentRequestDto.getComment(),postId,users.getUserId());
        commentRepository.save(comments);
        return true;
    }

    @Transactional
    public boolean commentUpdate(Long commentId, CommentRequestDto commentRequestDto, UserDetailsImpl userDetails) {
        Comments comments= commentRepository.findById(commentId).orElseThrow(
                ()->new NullPointerException("수정할 댓글이 없습니다."));
        System.out.println(comments.getUserId());
        System.out.println();
        System.out.println(userDetails.getUsers().getUserId());
        if(comments.getUserId().equals(userDetails.getUsers().getUserId())) {
            comments.setComment(commentRequestDto.getComment());
            return true;
        }else{
            return false;
        }
    }

    @Transactional
    public boolean deleteComment(Long commentId,UserDetailsImpl userDetails) {
        Comments comments= commentRepository.findById(commentId).orElseThrow(
                ()->new NullPointerException("삭제할 댓글이 없습니다."));
        if(comments.getUserId().equals(userDetails.getUsers().getUserId())){
            commentRepository.deleteById(comments.getId());
            return true;
        }else{
            return false;
        }
    }

    @Transactional
    public List<Comments> getComments(Long postId) {
        Posts posts=postsRepository.findById(postId).orElseThrow(
                ()->new NullPointerException("댓글을 작성할 게시글이 없습니다.")
        );

        return commentRepository.findCommentsByPostId(postId);
    }

    @Transactional
    public void deleteComment2(Long id){
        commentRepository.deleteById(id);
    }

//    public Comments getPostIdByComment(Long id){
//        Comments comments = commentRepository.findById(id).orElseThrow(
//                () -> new IllegalArgumentException("~~~comment의 id가 존재하지 않습니다")
//        );
//
//        return comments;
//    }

}
