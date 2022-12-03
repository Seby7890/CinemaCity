package com.spring.CinemaCity.controller;

import com.spring.CinemaCity.DTO.AddMovieDTO;
import com.spring.CinemaCity.model.Movie;
import com.spring.CinemaCity.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie")
public class MovieController {
    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping("/add")
    public Movie addMovie(@RequestBody AddMovieDTO addMovieDTO) {
        return movieService.addMovie(addMovieDTO);
    }
}