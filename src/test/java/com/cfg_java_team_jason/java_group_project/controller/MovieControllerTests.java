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

import static org.mockito.Mockito.*;
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
    @SneakyThrows
    public void deleteMovie_ShouldDeleteMovie_when_MovieExists() throws Exception {
        //arrange the movie to exist, mock delete, check if it deleted

        when(movieRepository.existsById(1)).thenReturn(true);

        mockMvc.perform(delete("/movies/1"))
                .andExpect(status().isOk());

    }

    @Test
    @SneakyThrows
    public void deleteMovie_ShouldReturn404_when_MovieDoesNotExist() throws Exception {

        when(movieRepository.existsById(1)).thenReturn(false);

        mockMvc.perform(delete("/movies/1"))
                .andExpect(status().isNotFound());

    }

    @Test
    @SneakyThrows
    public void deleteMovie_ShouldReturn500_when_ExceptionThrown() throws Exception {

        when(movieRepository.existsById(1)).thenThrow(new RuntimeException());

        mockMvc.perform(delete("/movies/1"))
                .andExpect(status().isInternalServerError());

    }

    @Test
    @SneakyThrows
    public void deleteMovie_ShouldReturn400_when_MovieIdIsNotAnInteger() throws Exception {

        mockMvc.perform(delete("/movies/abc"))
                .andExpect(status().isBadRequest());

    }
}





