package com.mini.emoti.service.post;

import java.util.List;
import java.time.LocalDateTime;

import com.mini.emoti.model.entity.PostEntity;
import com.mini.emoti.model.post.dto.PostDto;

public interface PostService {

    // 게시글 작성 
    public void writePost(PostDto dto);
    
    // 게시글 삭제 
    public void deletePost(Long postId);

    // 게시글 수정 
    public void updatePost(PostDto dto);

    // 전체 게시글 조회 
    public List<PostDto> getAllPost();

    // 게시글 조회
    public PostDto findById(Long postId);


    // 특정 유저의 특정 게시글 조회 
    // public PostEntity findByCreatedDateAndUserUserId(LocalDateTime createDate, Long userId);


    
    
}
