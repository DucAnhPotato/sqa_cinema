package com.spring.sqa_fullstack.controller.admin;

import com.spring.sqa_fullstack.model.Movie;
import com.spring.sqa_fullstack.service.MovieService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/admin/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @RequestMapping()
    public String allMovies(Model model) {
        List<Movie> movies = movieService.getAllMovies();
        model.addAttribute("movies", movies);
        return "admin/movie/movies";
    }

    @RequestMapping("/{id}")
    public String getMovieById(@PathVariable(name = "id") Long id,
                               Model model) {
        Movie movie = movieService.getMovieById(id);
        model.addAttribute("movie", movie);
        return "admin/movie/detailMovie";
    }

    @RequestMapping("/add")
    public String addMovie(Model model) {
        Movie movie = new Movie();
        model.addAttribute("movie", movie);
        return "admin/movie/addMovie";
    }

    @RequestMapping("/update/{id}")
    public String updateMovie(@PathVariable(name = "id") Long id,
                              Model model) {
        Movie movie = movieService.getMovieById(id);
        model.addAttribute("movie", movie);
        return "admin/movie/updateMovie";
    }

    @RequestMapping("/save")
    public String saveUpdate(
            @RequestParam(name = "id", required = false) Long id,
            @Valid Movie movie,
            BindingResult result) {
        if (result.hasErrors()) {
            if (id == null) {
                return "admin/movie/addMovie";
            } else {
                return "admin/movie/updateMovie";
            }
        }
        movie.setId(id);
        movieService.saveMovie(movie);
        return "redirect:/admin/movies";
    }

    @RequestMapping("/delete/{id}")
    public String deleteMovie(@PathVariable(name = "id") Long id) {
        movieService.deleteMovieById(id);
        return "redirect:/admin/movies";
    }
}
