package com.example.yammarket.controller;


import com.example.yammarket.model.Bookmarks;
import com.example.yammarket.model.Posts;
import com.example.yammarket.repository.BookmarkRepository;
import com.example.yammarket.security.UserDetailsImpl;
import com.example.yammarket.service.BookmarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class BookmarkController {

    private final BookmarkService bookmarkService;
    private final BookmarkRepository bookmarkRepository;

    @GetMapping("/bookmarks")
    public List<Posts> getBookmarks(@AuthenticationPrincipal UserDetailsImpl userDetails)  {
        return bookmarkService.getBookmarks(userDetails);
    }


    @PostMapping("/bookmarks/{postId}")
    public boolean registerBookmarks(@PathVariable Long postId,
                           @AuthenticationPrincipal UserDetailsImpl userDetails) {
        bookmarkService.registerBookmarks(postId, userDetails);
        return true;
    }

    @DeleteMapping("/bookmarks/{postId}")
    public boolean deleteBookmarks(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        bookmarkService.deleteBookmarks(postId, userDetails);
        return true;
    }
}
