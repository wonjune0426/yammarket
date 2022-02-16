package com.example.yammarket.service;

import com.example.yammarket.model.Comments;
import com.example.yammarket.model.Posts;
import com.example.yammarket.model.Users;
import com.example.yammarket.repository.CommentRepository;
import com.example.yammarket.repository.PostRepository;
import com.example.yammarket.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postsRepository;

    @Transactional
    public boolean commentsWrite(Long postId, String comment, UserDetailsImpl userDetails) {
        Posts posts=postsRepository.findById(postId).orElseThrow(
                ()-> new NullPointerException("댓글을 작성할 게시글이 없습니다."));

        Users users=userDetails.getUsers();
        Comments comments=new Comments(comment,users,posts);
        commentRepository.save(comments);
        return true;
    }

    @Transactional
    public boolean commentUpdate(Long commentId, String comment, UserDetailsImpl userDetails) {
        Comments comments= commentRepository.findById(commentId).orElseThrow(
                ()->new NullPointerException("수정할 댓글이 없습니다."));
        System.out.println("~~~ 댓글 수정 comments.getUsers().getUserId() : "+comments.getUsers().getUserId());
        System.out.println("~~~ 댓글 수정 comments.getUsers() : "+comments.getUsers());
        if(comments.getUsers().equals(userDetails.getUsers())) {
            comments.setComment(comment);
            return true;
        }else{
            return false;
        }
    }

    @Transactional
    public boolean deleteComment(Long commentId,UserDetailsImpl userDetails) {
        Comments comments= commentRepository.findById(commentId).orElseThrow(
                ()->new NullPointerException("삭제할 댓글이 없습니다."));
        System.out.println("~~~ 댓글 삭제 comments.getUsers().getUserId() : "+comments.getUsers().getUserId());
        System.out.println("~~~ 댓글 삭제 comments.getUsers() : "+comments.getUsers());
        
        if(comments.getUsers().equals(userDetails.getUsers())){
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

        return commentRepository.findAllByPosts(posts);
    }
}
