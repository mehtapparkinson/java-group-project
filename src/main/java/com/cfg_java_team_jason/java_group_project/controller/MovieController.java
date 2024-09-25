package com.cfg_java_team_jason.java_group_project.controller;


import com.cfg_java_team_jason.java_group_project.model.Movie;
import com.cfg_java_team_jason.java_group_project.repository.MovieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
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

    @PostMapping("/movies")
    public Movie addMovie(@RequestBody Movie movie) {
        //validation for title, review, star
        if (movie.getTitle().isEmpty() || movie.getReview().isEmpty()) {
            logger.error("Please provide valid movie data");
            throw new IllegalArgumentException("Please provide valid movie data");
        } else if (movie.getStar() < 1 || movie.getStar() > 5) {
            logger.error("Please provide a star rating between 1 and 5");
            throw new IllegalArgumentException("Please provide a star rating between 1 and 5");
        } else {
            logger.info("Added movie: {}", movie.getTitle());
            return movieRepository.save(movie);
        }
    }







}