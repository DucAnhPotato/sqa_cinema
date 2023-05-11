package com.spring.sqa_fullstack.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/home")
public class AdminHomeController {
    @RequestMapping()
    public String home() {
        return "admin/adminHome";
    }
}
