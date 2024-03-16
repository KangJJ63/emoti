package com.mini.emoti.controller.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mini.emoti.model.dto.EmotionDto;
import com.mini.emoti.service.EmotionService;


@RestController
@RequestMapping("/api/v1/emotion")
public class EmotionRestController {

    @Autowired
    private EmotionService emotionService;

    // localhost:8080/api/v1/emotion/insert
    @PostMapping("/insert")
    public String isertEmotion(@RequestBody EmotionDto dto){
        emotionService.isertEmotion(dto);
        return "저장 성공";
    }

    // localhost:8080/api/v1/emotion/find/{emotionId}
    @GetMapping("/find/{emotionId}")
    public String findByEmotionId(@PathVariable Long emotionId){
        EmotionDto dto = emotionService.findByEmotionId(emotionId);
        return dto.toString();
    }

    // localhost:8080/api/v1/emotion/delete/{emotionId}
    @GetMapping("/delete/{emotionId}")
    public String deleteEmotion(@PathVariable Long emotionId){
        emotionService.deleteEmotion(emotionId);
        return "삭제 성공";
    }

    // localhost:8080/api/v1/emotion/update
    @PostMapping("/update")
    public String updateEmotion(@RequestBody EmotionDto dto){
        emotionService.updateEmotion(dto);
        return "수정 성공";
    }
}
