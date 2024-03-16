package com.mini.emoti.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mini.emoti.model.dao.EmotionDao;
import com.mini.emoti.model.dto.EmotionDto;
import com.mini.emoti.model.entity.EmotionEntity;
import com.mini.emoti.service.EmotionService;



@Service
public class EmotionServiceImpl implements EmotionService{
    
    @Autowired
    private EmotionDao emotionDao;

    // delete
    @Override
    public void deleteEmotion(Long emotionId) {
        EmotionEntity entity = emotionDao.findByEmotionId(emotionId);
        emotionDao.deleteEmotion(entity.getEmotionId());
    }

    // select
    @Override
    public EmotionDto findByEmotionId(Long emotionId) {
        EmotionEntity entity = emotionDao.findByEmotionId(emotionId);
        EmotionDto dto = new EmotionDto();
        dto.setEmotionId(emotionId);
        // dto.setUser(entity.getUser());
        // dto.setEmotionType(entity.getEmotionType());
        return dto;
    }

    // insert
    @Override
    public void isertEmotion(EmotionDto dto) {
    //     EmotionEntity entity = new EmotionEntity();
    //     entity.setEmotionId(dto.getEmotionId());
    //     entity.setUser(dto.getUser());
    //     entity.setEmotionType(dto.getEmotionType());
    //     emotionDao.isertEmotion(entity);
    }

    // update
    @Override
    public void updateEmotion(EmotionDto dto) {
        EmotionEntity entity = emotionDao.findByEmotionId(dto.getEmotionId());
        entity.setEmotionId(dto.getEmotionId());
        // entity.setUser(dto.getUser());
        // entity.setEmotionType(dto.getEmotionType());
        // emotionDao.updateEmotion(entity);
    }

    
}

