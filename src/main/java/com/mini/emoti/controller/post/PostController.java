package com.mini.emoti.controller.post;

import java.util.List;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mini.emoti.model.entity.PostEntity;
import com.mini.emoti.model.post.dto.PostDto;
import com.mini.emoti.service.post.PostService;

import jakarta.validation.Valid;

@RestController
public class PostController {

    @Autowired
    private PostService postService;

      // 게시글 작성 
    @PostMapping("/user/post/write")
    public void writePost(@Valid @RequestBody PostDto dto){
        postService.writePost(dto);
    }
    
    // 게시글 삭제 
    @DeleteMapping("/user/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable("postId") Long postId){
        postService.deletePost(postId);
        return ResponseEntity.ok("삭제 성공");
    }

    // 게시글 수정 
    @PostMapping("/user/update/{postId}")
    public ResponseEntity<String> updatePost(@Valid @RequestBody PostDto dto,
                                            @PathVariable("postId") Long postId ){
        postService.updatePost(dto);
        return ResponseEntity.ok("수정 성공");


    }

    // 전체 게시글 조회 
    @GetMapping("/user/post")
    public List<PostDto> getAllPost(){
        return postService.getAllPost();
    }

    
}
