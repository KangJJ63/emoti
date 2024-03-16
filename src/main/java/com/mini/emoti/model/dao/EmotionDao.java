package com.mini.emoti.model.dao;

import com.mini.emoti.model.entity.EmotionEntity;

public interface EmotionDao {
    // select
    public EmotionEntity findByEmotionId(Long emotionId);

    // insert
    public void isertEmotion(EmotionEntity entity);

    // update
    public void updateEmotion(EmotionEntity entity);

    // delete
    public void deleteEmotion(Long emotionId);
    
}
