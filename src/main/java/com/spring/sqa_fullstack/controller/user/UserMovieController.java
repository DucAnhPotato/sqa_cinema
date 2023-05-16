package com.spring.sqa_fullstack.controller.user;

import com.spring.sqa_fullstack.model.Movie;
import com.spring.sqa_fullstack.model.Review;
import com.spring.sqa_fullstack.service.MovieService;
import com.spring.sqa_fullstack.service.ReviewService;
import com.spring.sqa_fullstack.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/user/movie")
public class UserMovieController {
    @Autowired
    private MovieService movieService;
    @Autowired
    private UserService userService;
    @Autowired
    private ReviewService reviewService;

    @RequestMapping()
    public String getAllMovies(Model model) {
        List<Movie> movies = movieService.getAllMovies();
        List<Movie> adventureMovies = movieService.getMoviesByCategory("adventure");
        List<Movie> horrorMovies = movieService.getMoviesByCategory("horror");
        model.addAttribute("movies", movies);
        model.addAttribute("adventureMovies", adventureMovies);
        model.addAttribute("horrorMovies", horrorMovies);
        return "user/userMovie";
    }

    @RequestMapping("/{id}")
    public String getMovieById(@PathVariable(name = "id") Long id,
                               Model model) {
        Movie movie = movieService.getMovieById(id);
        model.addAttribute("movie", movie);
        return "user/userMovieDetail";
    }

    @RequestMapping("/search")
    public String getMoviesByName(Model model ,
                                  @RequestParam(name = "name") String result) {
        List<Movie> movies =movieService.getMoviesByName(result);
        model.addAttribute("result", result);
        model.addAttribute("movies", movies);
        return "user/userMovieSearch";
    }

    @RequestMapping("/search/{category}")
    public String getMoviesByCategory(Model model ,
                                  @PathVariable(name = "category") String result) {
        List<Movie> movies =movieService.getMoviesByCategory(result);
        model.addAttribute("result", result);
        model.addAttribute("movies", movies);
        return "user/userMovieSearch";
    }

    @RequestMapping("/user/movie/{id}/review/save")
    public String sendReview(@RequestParam("inputText") String inputText,
                             @PathVariable(name = "id") Long movieId) {
        Review review = new Review();
        review.setUser(userService.getUserById(2L));
        review.setMovie(movieService.getMovieById(movieId));
        review.setDescription(inputText);
        reviewService.saveReview(review);
        return "redirect:/user/movie/{id}";
    }
}
