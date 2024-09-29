package com.cfg_java_team_jason.java_group_project.controller;

import com.cfg_java_team_jason.java_group_project.model.Movie;
import com.cfg_java_team_jason.java_group_project.repository.MovieRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

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
    public void when_addedMovie() throws Exception {
        // PostMapping endpoint test
    }

    @Test
    @SneakyThrows
    public void getAllMovies_ShouldReturnListOfMovies_WhenMoviesExist() {
        // Create two movies
        Movie movie1 = new Movie();
        movie1.setMovie_id(1);
        movie1.setTitle("Inception");
        movie1.setReview("It could have been a dream, it could have been real, we will never know, but it was a great movie");
        movie1.setDate(LocalDate.of(2024, 7, 16));
        movie1.setStar(5);

        Movie movie2 = new Movie();
        movie2.setMovie_id(2);
        movie2.setTitle("Titanic");
        movie2.setReview("Rose could have saved Jack, there was enough space on the door");
        movie2.setDate(LocalDate.of(2023, 12, 19));
        movie2.setStar(4);

        // Mock the findAll method
        when(movieRepository.findAll()).thenReturn(List.of(movie1, movie2));

        // Get the response
        mockMvc.perform(get("/movies"))
                // 200 OK
                .andExpect(status().isOk())
                // Check if the response has the correct values
                .andExpect(jsonPath("$[0].title").value("Inception"))
                .andExpect(jsonPath("$[1].title").value("Titanic"))
                .andExpect(jsonPath("$[0].review").value("It could have been a dream, it could have been real, we will never know, but it was a great movie"))
                .andExpect(jsonPath("$[1].review").value("Rose could have saved Jack, there was enough space on the door"));
    }

    @Test
    @SneakyThrows
    public void getAllMovies_ShouldReturnNoContent_WhenNoMoviesExist() {
        when(movieRepository.findAll()).thenReturn(List.of());
        mockMvc.perform(get("/movies"))
                // 204 No Content
                .andExpect(status().isNoContent());
    }

    @Test
    @SneakyThrows
    public void getAllMovies_ShouldReturnInternalServerError_WhenExceptionIsThrown() {
        when(movieRepository.findAll()).thenThrow(new RuntimeException());
        mockMvc.perform(get("/movies"))
                // 500 Error
                .andExpect(status().isInternalServerError());
    }
}