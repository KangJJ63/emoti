package com.mini.emoti.service;

import com.mini.emoti.model.dto.EmotionDto;

public interface EmotionService {
    // insert
    public void isertEmotion(EmotionDto dto);
    // select
    public EmotionDto findByEmotionId(Long emotionId);
    // update
    public void updateEmotion(EmotionDto dto);
    // delete
    public void deleteEmotion(Long emotionId);
}
