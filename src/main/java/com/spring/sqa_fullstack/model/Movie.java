package com.spring.sqa_fullstack.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id", nullable = false)
    private Long id;
    @Column(name = "movie_name")
    private String name;
    @Column(name = "movie_image")
    private String imageURL;
    @Column(name = "movie_category")
    private String category;
    @Column(name = "movie_actor")
    private String actor;
    @Column(name = "movie_director")
    private String director;
    @Column(name = "movie_rating")
    private int rating;
    @Column(name = "movie_description", length = 1000)
    private String description;
    @Column(name = "movie_length")
    private int length;
    @Column(name = "movie_trailer")
    private String trailerURL;
}
