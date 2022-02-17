package com.example.yammarket.repository;

import com.example.yammarket.model.Bookmarks;
import com.example.yammarket.model.Users;
import com.example.yammarket.security.UserDetailsImpl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Book;
import java.util.List;
import java.util.Optional;

public interface BookmarkRepository extends JpaRepository<Bookmarks, Long> {
    List<String> findAllByUserId(String userId);
    List<Bookmarks> findByPostId(Long postId);

    void deleteByPostId(Long postId);

    List<Bookmarks> findAllByPostId(Long postid);
    //List<Bookmarks> findAllByOrderByCreatedAtDesc();

}
