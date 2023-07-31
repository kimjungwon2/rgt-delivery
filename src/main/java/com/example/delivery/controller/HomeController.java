package com.example.delivery.controller;

import com.example.delivery.config.auth.LoginUser;
import com.example.delivery.config.auth.dto.SessionUser;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
@Slf4j
public class HomeController {

    private final HttpSession httpSession;

    @GetMapping("/")
    public String home(Model model, @LoginUser SessionUser user){

        if(user!= null){
            model.addAttribute("userName",user.getName());
        }

        log.info("{}",user);

        return "index";
    }

    @GetMapping("/move")
    public String move(){
        return "hello";
    }

    @GetMapping("/move/user")
    public String onlyUser(){
        return "user";
    }
}
