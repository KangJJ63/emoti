package com.mini.emoti.controller.user;

import org.hibernate.annotations.AttributeAccessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.mini.emoti.config.constant.ExceptionConstant;
import com.mini.emoti.config.error.EmotionException;
import com.mini.emoti.model.user.dto.UserDto;
import com.mini.emoti.service.user.UserService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
// @RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;
    
    /*
     * 누구나 접근 가능
     */

     @GetMapping("/index")
     public String index(Authentication authentication, Model model) {
        if(authentication != null){
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            model.addAttribute("username", userDetails.getUsername()); 
        }

        return "index";
     }

     @GetMapping("/loginPage")
     public String loginPage(@RequestParam(value = "errorMessage", required = false)String errorMessage, Model model){
        model.addAttribute("errorMessage", errorMessage);
        model.addAttribute("openLogin", true);
        return "index";

     }


     @GetMapping("/joinPage")
     public String joinPage(Model model){
        model.addAttribute("openJoinModal", true); // 회원가입 모달을 열기 위한 플래그
        return "index"; // index.html을 반환
    }
     
    // 회원가입 
    // localhost:8080/api/v1/user/join
     @PostMapping("/join")
     public ResponseEntity<String> join(@ModelAttribute UserDto dto){
        log.info("[UserController][join]" + dto.toString());
        try {
            userService.joinUser(dto);
            return ResponseEntity.ok("가입 성공");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("가입 실패");

     }

     /*
      * 로그인한 경우만 
      */

      @GetMapping("user/index")
      public String user(Authentication authentication, Model model){

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        model.addAttribute("username", userDetails.getUsername());
        return "/index";
      }



    // @GetMapping("/user/index")
    
    // 회원가입
    // localhost:8080/api/v1/user/join
    // @PostMapping("/join")
    // public ResponseEntity<String> joinUser(@Valid @RequestBody UserDto dto) throws Exception {
        
    //     if(dto.getNickname() == null){
    //         throw new EmotionException(ExceptionConstant.USER_NOT_FOUND);
    //     }

    //     userService.joinUser(dto);
    //     return ResponseEntity.ok("가입 성공");
    // }



    // 유저 조회
    // localhost:8080/api/v1/user/{userName}
    @GetMapping("/user/{userName}")
    public ResponseEntity<String> selectUser(@PathVariable("userName") String userName) throws Exception {
        
        return ResponseEntity.ok(userService.findByUserName(userName).toString());
    }

    // 삭제
    // localhost:8080/api/v1/user/{userName}
    @DeleteMapping("/user/{userName}")
    public ResponseEntity<String> deleteUser(@PathVariable("userName") String userName) throws Exception {

        userService.deleteUser(userName);
        return ResponseEntity.ok("삭제 성공");
    }

    // 프로필 수정 
    // localhost:8080/api/v1/user/join
    @PostMapping("/user/update")
    public ResponseEntity<String> updateUser(@Valid @RequestBody UserDto dto) throws Exception {
        
        if(dto.getNickname() == null){
            throw new EmotionException(ExceptionConstant.INVALID_REQUEST);
        }

        userService.updateUser(dto);

        return ResponseEntity.ok("업데이트 성공");
    }
}
