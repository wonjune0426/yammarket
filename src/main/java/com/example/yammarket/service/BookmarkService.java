package com.example.yammarket.service;

import com.example.yammarket.dto.BookmarkRequestDto;
import com.example.yammarket.model.Bookmarks;
import com.example.yammarket.model.Posts;
import com.example.yammarket.model.Users;
import com.example.yammarket.repository.BookmarkRepository;
import com.example.yammarket.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BookmarkService {

    private final BookmarkRepository bookmarkRepository;
    private final PostRepository postRepository;

    public boolean registerBookmarks(@PathVariable Long postId,
                                     @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Posts posts = postRepository.findById(postId);

        Users users = userDetails.getUser();
        Bookmarks bookmarks = new Bookmarks(users, posts);
        bookmarkRepository.save(bookmarks);
        return true;
    }

    public boolean deleteBookmarks(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {

        Optional<Bookmarks> bookmarks = bookmarkRepository.findById(id);
        Users bookmarkUserId = bookmarks.get().getUsers();
        Users userId = userDetails.getUser();

        boolean result;
        if (bookmarkUserId== userId) {
            bookmarkRepository.deleteById(id);
            result = true;
        } else {
            result = false;
        }

        return result;
    }
}
