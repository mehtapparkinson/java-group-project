package com.cfg_java_team_jason.java_group_project.controller;


import com.cfg_java_team_jason.java_group_project.model.Movie;
import com.cfg_java_team_jason.java_group_project.repository.MovieRepository;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class MovieController {
    private static final Logger logger = LoggerFactory.getLogger(MovieController.class);

    @Autowired
    private MovieRepository movieRepository;

    @GetMapping("/movies")
    public ResponseEntity<List<Movie>> getAllMovies(){
        try {
            List<Movie> movies = movieRepository.findAll();
            if (movies.isEmpty()) {
                //204 No Content
                logger.warn("No movies found in the database.");
                return ResponseEntity.noContent().build();
            }
        //200 OK
        logger.info("Successfully retrieved {} movies from the database.", movies.size());
        return ResponseEntity.ok(movies);
        } catch (Exception e) {
            //500 Error
            logger.error("An error occurred while retrieving movies from the database.", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @DeleteMapping("/movies/{movieId}")
    public ResponseEntity<String> deleteMovie(@PathVariable int movieId) {
        try {
            if (movieRepository.existsById(movieId)) {
                movieRepository.deleteById(movieId);
                logger.info("Deleted movie: {}", movieId);
                return ResponseEntity.ok("Movie with ID " + movieId + " deleted successfully.");
            } else {
                logger.warn("Movie with id {} not found", movieId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Movie with ID " + movieId + " not found.");
            }
        } catch (Exception e) {
        //500 Error
        logger.error("An error occurred while deleting movies from the database.", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @PutMapping("/movies/{movieId}")
    public ResponseEntity <?> updateMovie(@PathVariable int movieId, @Valid @RequestBody Movie movie) {
       try{
           if (movieRepository.existsById(movieId)) {
               movie.setMovie_id(movieId);
               Movie updatedMovie = movieRepository.save(movie);
               logger.info("Updated movie with id: {}", movieId);
               return ResponseEntity.ok(updatedMovie);
           } else {
               logger.warn("Movie with id {} not found", movieId);
               return ResponseEntity.status(HttpStatus.NOT_FOUND)
                       .body("Movie with ID " + movieId + " not found.");
           }
       } catch (Exception e) {
           logger.error("An error occurred while updating a movie to the database.", e);
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
       }
    }

    @PostMapping("/movies")
    public ResponseEntity<Movie> addMovie(@Valid @RequestBody Movie movie) {
        Movie savedMovie = movieRepository.save(movie);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMovie);
    }

    //Handles validation errors of @Valid. Returns corresponding error messages to the client.
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }


    @GetMapping("/movies/search")
    public ResponseEntity<?> searchMoviesByTitle(@RequestParam String title) {
        logger.info("Searching movies with title: {}", title);

        try {
            List<Movie> movies = movieRepository.findByTitle(title);

            if (movies.isEmpty()) {
                logger.warn("No movies found with title: {}", title);
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No movies found with title: " + title);
            }

            return ResponseEntity.ok(movies);
        } catch (Exception e) {
            logger.error("Error occurred while searching for movies: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while searching for movies");
        }
    }




}