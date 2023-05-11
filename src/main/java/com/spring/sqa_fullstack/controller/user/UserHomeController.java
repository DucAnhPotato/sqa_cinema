package com.spring.sqa_fullstack.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/home")
public class UserHomeController {
    @RequestMapping()
    public String home() {
        return "user/userHome";
    }
}
