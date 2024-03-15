package com.mini.emoti.model.post.dao;

import java.util.List;
import java.time.LocalDateTime;

import com.mini.emoti.model.entity.PostEntity;

public interface PostDao {

    // 게시글 작성 
    public void writePost(PostEntity entity);
    
    // 게시글 삭제 
    public void deletePost(Long postId);

    // 게시글 수정 
    public void updatePost(PostEntity entity);

    // 전체 게시글 조회 
    public List<PostEntity> getAllPost();
    
    //특정 
    public PostEntity findById(Long postId);

    // 특정 유저의 특정 게시글 조회 
    // PostEntity findByCreatedDateAndUserUserId(LocalDateTime createDate, Long userId);


    
}