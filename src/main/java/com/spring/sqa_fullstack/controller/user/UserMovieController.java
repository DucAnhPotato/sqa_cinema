package com.spring.sqa_fullstack.controller.user;

import com.spring.sqa_fullstack.model.Movie;
import com.spring.sqa_fullstack.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user/movie")
public class UserMovieController {
    @Autowired
    private MovieService movieService;

    @RequestMapping()
    public String getAllMovies(Model model) {
        List<Movie> movies = movieService.getAllMovies();
        model.addAttribute("movies", movies);
        return "user/userMovie";
    }

    @RequestMapping("/{id}")
    public String getMovieById(@PathVariable(name = "id") Long id,
                               Model model) {
        Movie movie = movieService.getMovieById(id);
        model.addAttribute("movie", movie);
        return "user/userMovieDetail";
    }
}
