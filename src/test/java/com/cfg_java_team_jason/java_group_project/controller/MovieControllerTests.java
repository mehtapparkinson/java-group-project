package com.cfg_java_team_jason.java_group_project.controller;

import com.cfg_java_team_jason.java_group_project.repository.MovieRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

    // delete tests section
    @Test
    public void when_deleteMovie_then_returnSuccess() throws Exception {
        int movieId = 1;
        doNothing().when(movieRepository).deleteById(movieId);

        mockMvc.perform(delete("/movies/{movieId}", movieId))
                .andExpect(status().isOk());

        verify(movieRepository).deleteById(movieId);
    }

    //TODO: mock behaviour when movie doesn't exist, invalid movie id like string


    @Test
    public void when_addedMovie() throws Exception {
        // PostMapping endpoint test
    }
}

