package com.cfg_java_team_jason.java_group_project.controller;


import com.cfg_java_team_jason.java_group_project.model.Movie;
import com.cfg_java_team_jason.java_group_project.repository.MovieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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


    @DeleteMapping("/movies/{movieId}")
    public ResponseEntity<String> deleteMovie(@PathVariable int movieId) {
        try {
            if (movieRepository.existsById(movieId)) {
                movieRepository.deleteById(movieId);
                logger.info("Deleted movie: {}", movieId);
                return ResponseEntity.ok("Movie with ID " + movieId + " deleted successfully.");
            } else {
                logger.error("Movie with id {} not found", movieId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Movie with ID " + movieId + " not found.");
            }
        } catch (Exception e) {
        //500 Error
        logger.error("An error occurred while deleting movies from the database.", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
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
    public ResponseEntity<?> addMovie(@RequestBody Movie movie) {
        try {
            // Validate
            if (movie.getTitle() == null || movie.getTitle().isEmpty() || movie.getReview() == null || movie.getReview().isEmpty()) {
                logger.error("Invalid movie data: Title or Review is missing");
                return ResponseEntity.badRequest().body("Please provide valid movie data");
            } else if (movie.getStar() < 1 || movie.getStar() > 5) {
                logger.error("Invalid star rating: {}", movie.getStar());
                return ResponseEntity.badRequest().body("Please provide a star rating between 1 and 5");
            }
            // Save
            Movie savedMovie = movieRepository.save(movie);
            logger.info("Added movie: {}", movie.getTitle());
            return ResponseEntity.ok(savedMovie);
        //Catch
        } catch (Exception e) {
            logger.error("Unexpected error occurred: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred while adding the movie");
        }
    }







}