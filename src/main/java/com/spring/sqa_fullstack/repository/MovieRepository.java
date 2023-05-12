package com.spring.sqa_fullstack.repository;

import com.spring.sqa_fullstack.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findMoviesByIdBetween(Long idStart, Long idEnd);

}
