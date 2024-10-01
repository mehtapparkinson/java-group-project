package com.cfg_java_team_jason.java_group_project.controller;

import com.cfg_java_team_jason.java_group_project.model.Movie;
import com.cfg_java_team_jason.java_group_project.repository.MovieRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;







@WebMvcTest(MovieController.class)
public class MovieControllerTests {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private MovieRepository movieRepository;


    //TO DO
    @Test
    public void when_getAllMovies() throws Exception {
        // GetMapping endpoint test
    }
    @Test
    public void when_updateMovie() throws Exception {
        // PutMapping endpoint test
    }
    @Test
    public void when_deleteMovie() throws Exception {
        // DeleteMapping endpoint test
    }

    @Test
    public void when_addedMovie_ValidMovie() throws Exception {
        Movie movie = new Movie();
        movie.setTitle("Inception");
        movie.setReview("Great movie!");
        movie.setStar(5);

        when(movieRepository.save(any(Movie.class))).thenReturn(movie);

        mockMvc.perform(post("/movies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Inception\", \"review\":\"Great movie!\", \"star\":5}"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"title\":\"Inception\", \"review\":\"Great movie!\", \"star\":5}"));

        verify(movieRepository, times(1)).save(any(Movie.class));
    }

    @Test
    public void when_addedMovie_InvalidTitle() throws Exception {
        mockMvc.perform(post("/movies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"\", \"review\":\"Good movie!\", \"star\":4}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Please provide valid movie data"));

        verify(movieRepository, times(0)).save(any(Movie.class));
    }

    @Test
    public void when_addedMovie_InvalidStarRating() throws Exception {
        mockMvc.perform(post("/movies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"The Dark Knight\", \"review\":\"Best superhero movie!\", \"star\":6}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Please provide a star rating between 1 and 5"));

        verify(movieRepository, times(0)).save(any(Movie.class));
    }

    @Test
    public void when_addedMovie_UnexpectedError() throws Exception {
        Movie movie = new Movie();
        movie.setTitle("Interstellar");
        movie.setReview("Amazing visuals");
        movie.setStar(5);

        when(movieRepository.save(any(Movie.class))).thenThrow(new RuntimeException("Database error"));

        mockMvc.perform(post("/movies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Interstellar\", \"review\":\"Amazing visuals\", \"star\":5}"))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("An unexpected error occurred while adding the movie"));

        verify(movieRepository, times(1)).save(any(Movie.class));
    }

    // Other tests for getAllMovies, updateMovie, and deleteMovie will go here
}


