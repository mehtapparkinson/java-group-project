package com.cfg_java_team_jason.java_group_project.controller;


import com.cfg_java_team_jason.java_group_project.model.Movie;
import com.cfg_java_team_jason.java_group_project.repository.MovieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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

    @DeleteMapping("/movies/{movieId}")
    public ResponseEntity<String> deleteMovie(@PathVariable int movieId) {
        if (movieRepository.existsById(movieId)) {
            movieRepository.deleteById(movieId);
            logger.info("Deleted movie: {}", movieId);
            return ResponseEntity.ok("Movie with ID " + movieId + " deleted successfully.");
        } else {
            logger.error("Movie with id {} not found", movieId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Movie with ID " + movieId + " not found.");
        }
    }



}


