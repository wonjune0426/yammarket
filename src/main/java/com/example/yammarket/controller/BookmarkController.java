package com.example.yammarket.controller;


import com.example.yammarket.model.Bookmarks;
import com.example.yammarket.model.Posts;
import com.example.yammarket.model.Users;
import com.example.yammarket.repository.BookmarkRepository;
import com.example.yammarket.repository.UserRepository;
import com.example.yammarket.security.TokenUser;
import com.example.yammarket.security.UserDetailsImpl;
import com.example.yammarket.service.BookmarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
@RestController
public class BookmarkController {

    private final BookmarkService bookmarkService;
    private final BookmarkRepository bookmarkRepository;
    private final UserRepository userRepository;

    @GetMapping("/bookmarks")
    public List<Long> getBookmarks(
//            @AuthenticationPrincipal UserDetailsImpl userDetails
            HttpServletRequest request
    ) throws Exception {
        TokenUser tokenUser=new TokenUser(userRepository);
        Users users=tokenUser.getUser(request);
        return bookmarkService.getBookmarks(users);
    }


    @PostMapping("/bookmarks/{postId}")
    public boolean registerBookmarks(@PathVariable Long postId,
//                           @AuthenticationPrincipal UserDetailsImpl userDetails
                                     HttpServletRequest request
    ) throws Exception {
        TokenUser tokenUser=new TokenUser(userRepository);
        Users users=tokenUser.getUser(request);
        return bookmarkService.registerBookmarks(postId, users);
    }

    @DeleteMapping("/bookmarks/{postId}")
    public boolean deleteBookmarks(@PathVariable Long postId,
//                                   @AuthenticationPrincipal UserDetailsImpl userDetails
                                   HttpServletRequest request
    ) throws Exception {
        TokenUser tokenUser=new TokenUser(userRepository);
        Users users=tokenUser.getUser(request);
        return bookmarkService.deleteBookmarks(postId,users);
    }
}
