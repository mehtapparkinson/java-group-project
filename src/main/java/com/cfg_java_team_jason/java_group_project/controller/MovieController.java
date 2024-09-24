package com.cfg_java_team_jason.java_group_project.controller;


import com.cfg_java_team_jason.java_group_project.model.Movie;
import com.cfg_java_team_jason.java_group_project.repository.MovieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class MovieController {
    private static final Logger logger = LoggerFactory.getLogger(MovieController.class);

    @Autowired
    private MovieRepository movieRepository;

    @GetMapping("/movies")
    public List<Movie> getAllMovies(){
        return movieRepository.findAll();

    }

    @DeleteMapping ("/movies/{movieId}")
    public void deleteMovie(@PathVariable int movieId) {
        if (movieRepository.existsById(movieId)) {
            movieRepository.deleteById(movieId);
            logger.info("Deleted movie: {}", movieId);
        } else {
            logger.error("Movie with id {} not found", movieId);
        }
    }

    @PutMapping("/movies/update/{movieId}")
    public void updateMovie(@PathVariable int movieId, @RequestBody Movie movie) {
        if (movieRepository.existsById(movieId)) {
            movie.setMovie_id(movieId);
            movieRepository.save(movie);
            logger.info("Updated movie with id: {}", movieId);
        } else {
            logger.error("Movie with id {} not found", movieId);
        }
    }


}