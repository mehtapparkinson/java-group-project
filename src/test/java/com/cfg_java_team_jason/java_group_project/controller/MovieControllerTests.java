package com.cfg_java_team_jason.java_group_project.controller;

import com.cfg_java_team_jason.java_group_project.repository.MovieRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

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
}

