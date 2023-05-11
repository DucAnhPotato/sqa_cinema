package com.spring.sqa_fullstack.service;

import com.spring.sqa_fullstack.model.Movie;
import com.spring.sqa_fullstack.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> getAllMovies() {
        return movieRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    public void saveMovie(Movie movie) {
        movieRepository.save(movie);
    }

    public Movie getMovieById(Long id) {
        return movieRepository.findById(id).orElse(null);
    }

    public void deleteMovieById(Long id) {
        movieRepository.deleteById(id);
    }
}
