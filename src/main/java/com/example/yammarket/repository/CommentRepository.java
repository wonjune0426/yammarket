package com.example.yammarket.repository;

import com.example.yammarket.model.Comments;
import com.example.yammarket.model.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comments,Long> {

    List<Comments> findCommentsByPostId(Long postId);

    void deleteByPostId(Long postId);
}
