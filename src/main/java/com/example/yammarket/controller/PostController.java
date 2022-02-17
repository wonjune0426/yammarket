package com.example.yammarket.controller;

import com.example.yammarket.dto.ImageFileDto;
import com.example.yammarket.dto.PostDto;
import com.example.yammarket.dto.PostRequestDto;
import com.example.yammarket.model.ImageFiles;
import com.example.yammarket.model.Posts;
import com.example.yammarket.model.Token;
import com.example.yammarket.model.Users;
import com.example.yammarket.repository.ImageFileRepository;
import com.example.yammarket.repository.UserRepository;
import com.example.yammarket.security.TokenUser;
import com.example.yammarket.security.UserDetailsImpl;
import com.example.yammarket.service.ImageFileService;
import com.example.yammarket.service.PostService;
import com.example.yammarket.util.MD5Generator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import java.nio.file.Path;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.nio.file.Paths;
import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class PostController {

    private final PostService postService;
    private final ImageFileService fileService;
    private final UserRepository userRepository;

    // 처음에는 그냥 메인페이지만 보여주는게 맞고
    // 메인페이지("/")로 가서 post의 list를 호출을 해주는게 맞다
    @GetMapping("/views/postList")
    public List<Posts> mainIndex(){
        return postService.getpostList();
    }

    // 일단 반환하는데 Boolean 형인데
    // user정보가 필요하면  @AuthenticationPrincipal UserDetailsImpl userDetails 를 사용하면 된다.

//    @PostMapping("/posts/write0")
//    public Boolean createPost(@RequestBody PostRequestDto requestDto){
//        return postService.createPostInfo(requestDto);
//    }

    // 게시글 생성
    @PostMapping("/posts/write")    // "file"은 프론트의 input name="file" 인듯
    public Boolean createPost(@RequestPart(value = "file")MultipartFile files,
                              @RequestPart(value = "post") PostDto postDto,
//                              @AuthenticationPrincipal UserDetailsImpl userDetails
                              HttpServletRequest request
                              ) {
        //public Boolean createPost(@RequestParam("file")MultipartFile files, @RequestBody PostDto postDto){
        try {
            System.out.println("~~~ 1");
            String origFilename = files.getOriginalFilename();
            // 이미지를 파일로 저장하기 위한 name을 만든다.
            String filename = new MD5Generator(origFilename).toString();
            /* 실행되는 위치의 'files' 폴더에 파일이 저장됩니다. */
            String savePath = "files";
//            String savePath = System.getProperty("user.dir") + "\\files";
            /* 파일이 저장되는 폴더가 없으면 폴더를 생성합니다. */
            if (!new File(savePath).exists()) {
                try {
                    new File(savePath).mkdir();
                } catch (Exception e) {
                    e.getStackTrace();
                }
            }
//            String filePath = savePath + "\\" + filename; files.transferTo(new File(filePath));
            Path filePath = Paths.get("/home/ubuntu/", savePath, "/" , filename);
            files.transferTo(new File(String.valueOf(filePath)));

            ImageFileDto fileDto = new ImageFileDto();
            fileDto.setOrigFilename(origFilename);
            fileDto.setFileName(filename);
            fileDto.setFilePath(String.valueOf(filePath));
            fileDto.setFileSize(files.getSize()); // 내가 추가함

            // 다른 예제들은 fileUrl? 을 dto로 저장하기도 하던데..
            Long fileId = fileService.saveFile(fileDto); // 이미지 파일을 저장한다.
            postDto.setFileId(fileId);  // 저장한 이미지 파일의 아이디를 postDto에 담는다

            // 일단 이름을 임의로 박음
            //postDto.setUserId("iamuser");
            TokenUser tokenUser=new TokenUser(userRepository);
            Users users=tokenUser.getUser(request);
            postDto.setUserId(users.getUserId());
            postDto.setFilePath(String.valueOf(filePath));
            postService.savePost(postDto);  // postDto를 저장한다.
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        //return "redirect:/";
        return true;
    }

    //  @AuthenticationPrincipal UserDetailsImpl userDetails 넣을까 말까
    // 댓글은 댓글조회 url을 만들어서 불러주는게 맞는듯
    @GetMapping("/posts/{postId}")
    public Posts viewPost(@PathVariable Long postId){
        return postService.viewPostInfo(postId);
    }

    // 게시글 수정
    // Post에 fileId도 있으니까 그거 이용하자
    @PatchMapping("/posts/{postId}")
    public Boolean updatePost(@PathVariable Long postId,
                              @RequestPart(value = "file") MultipartFile files,
                              @RequestPart(value = "post") PostDto postDto,
//                              @AuthenticationPrincipal UserDetailsImpl userDetails
                              HttpServletRequest request
    ) throws Exception {

        // 게시글의 userId랑 같은지를 체크해야한다.
        Posts posts = postService.getPost(postId);
        String postUserId = posts.getUserId();
        // 유저 아이디가 다르면 false 반환
        TokenUser tokenUser=new TokenUser(userRepository);
        Users users=tokenUser.getUser(request);
        if( !postUserId.equals(users.getUserId()) ){
            return false;
        }

        Long fileId;
        try {
            // 일단 사진 필수라고 하자
            if( !files.isEmpty() ){
                fileId = posts.getFileId();
                fileService.deleteFile(fileId);
            }

            String origFilename = files.getOriginalFilename();
            // 이미지를 파일로 저장하기 위한 name을 만든다.
            String filename = new MD5Generator(origFilename).toString();
            /* 실행되는 위치의 'files' 폴더에 파일이 저장됩니다. */
            String savePath = "files";
            /* 파일이 저장되는 폴더가 없으면 폴더를 생성합니다. */
            if (!new File(savePath).exists()) {
                try{
                    new File(savePath).mkdir();
                }
                catch(Exception e){
                    e.getStackTrace();
                }
            }
            Path filePath = Paths.get("/home/ubuntu/", savePath, "/" , filename);
            files.transferTo(new File(String.valueOf(filePath)));

            ImageFileDto fileDto = new ImageFileDto();
            fileDto.setOrigFilename(origFilename);
            fileDto.setFileName(filename);
            fileDto.setFilePath(String.valueOf(filePath));
            fileDto.setFileSize(files.getSize()); // 내가 추가함

            // 다른 예제들은 fileUrl? 을 dto로 저장하기도 하던데..
            Long newfileId = fileService.saveFile(fileDto); // 이미지 파일을 저장한다.
            postDto.setFileId(newfileId);  // 저장한 이미지 파일의 아이디를 postDto의 fileId에 담는다
            postDto.setFilePath(String.valueOf(filePath));
            // 게시글 수정
            postService.updatePost2(postId, postDto);

            //postService.savePost(postDto);  // postDto를 저장한다.
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
        //return "redirect:/";
        return true;
    }

    // 게시글 삭제
    @DeleteMapping("/posts/{postId}")
    public Boolean deletePost(@PathVariable Long postId,
//                              @AuthenticationPrincipal UserDetailsImpl userDetails
                              HttpServletRequest request
    ) throws Exception {

        // 게시글의 userId랑 같은지를 체크해야한다.
        Posts posts = postService.getPost(postId);
        String postUserId = posts.getUserId();

        // 유저 아이디가 다르면 false 반환
        TokenUser tokenUser=new TokenUser(userRepository);
        Users users=tokenUser.getUser(request);
        if( !postUserId.equals(users.getUserId()) ){
            return false;
        }

        Long fileId;
        try{
            //Posts posts = postService.getPost(postId); - 위에서 사용함
            fileId = posts.getFileId();
            // db에 저장된 파일을 삭제한다.
            fileService.deleteFile(fileId);
            postService.deletePostService(postId);
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }


    // Long 으로 반환해서 뭐하려고
//    @PatchMapping("/posts/{postId}")
//    public Boolean updatePost(@PathVariable Long postId, @RequestBody PostRequestDto requestDto){
//        return postService.updatePost(postId, requestDto);
//    }

//    @DeleteMapping("/posts/{postId}")
//    public Boolean deletePost(@PathVariable Long postId){
//        return postService.deletePostService(postId);
//    }

}
