package com.spring.sqa_fullstack.controller.user;


import com.spring.sqa_fullstack.model.Movie;
import com.spring.sqa_fullstack.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user/home")
public class UserHomeController {
    @Autowired
    private MovieService movieService;

    @RequestMapping()
    public String home(Model model) {
        List<Movie> popMovies = movieService.get4Movies(1L, 5L);
        List<Movie> newMovies = movieService.get4Movies(4L, 7L);
        model.addAttribute("popMovies", popMovies);
        model.addAttribute("newMovies", newMovies);
        return "user/userHome";
    }
}
