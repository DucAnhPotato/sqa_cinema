package com.spring.sqa_fullstack.repository;

import com.spring.sqa_fullstack.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findMoviesByIdBetween(Long idStart, Long idEnd);
    @Query("select m from Movie m " +
            "where m.category like %:movie_category% " +
            "order by m.id " +
            "limit 4")
    List<Movie> findMoviesByCategoryContaining(@Param("movie_category") String category);

    List<Movie> findAllByNameContaining(String name);

}
