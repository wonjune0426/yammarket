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

@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
@RestController
public class BookmarkController {

    private final BookmarkService bookmarkService;
    private final BookmarkRepository bookmarkRepository;

    @GetMapping("/bookmarks")
    public List<Long> getBookmarks(@AuthenticationPrincipal UserDetailsImpl userDetails)  {
        return bookmarkService.getBookmarks(userDetails);
    }


    @PostMapping("/bookmarks/{postId}")
    public boolean registerBookmarks(@PathVariable Long postId,
                           @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return bookmarkService.registerBookmarks(postId, userDetails);
    }

    @DeleteMapping("/bookmarks/{bookmarkId}")
    public boolean deleteBookmarks(@PathVariable Long bookmarkId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return bookmarkService.deleteBookmarks(bookmarkId, userDetails);
    }
}
