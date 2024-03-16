package com.mini.emoti.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mini.emoti.model.entity.EmotionEntity;


public interface EmotionRepository extends JpaRepository<EmotionEntity, Long>{

    public EmotionEntity findByEmotionId(Long emotionId);

    
    
}
