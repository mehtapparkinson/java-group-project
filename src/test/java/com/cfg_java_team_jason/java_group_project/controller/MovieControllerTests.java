package com.cfg_java_team_jason.java_group_project.controller;

import com.cfg_java_team_jason.java_group_project.model.Movie;
import com.cfg_java_team_jason.java_group_project.repository.MovieRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;


@WebMvcTest(MovieController.class)
public class MovieControllerTests {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private MovieRepository movieRepository;


    //TO DO

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

    @Test
    public void getAllMovies_ShouldReturnListOfMovies_WhenMoviesExist() throws Exception {
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

        Pageable pageable = PageRequest.of(0, 50);
        Page<Movie> moviePage = new PageImpl<>(List.of(movie1, movie2), pageable, 2);

        when(movieRepository.findAll(pageable)).thenReturn(moviePage);

        mockMvc.perform(get("/movies"))
                // 200 OK
                .andExpect(status().isOk())
                // Check if the response has the correct values
                .andExpect(jsonPath("$.content[0].title").value("Inception"))
                .andExpect(jsonPath("$.content[1].title").value("Titanic"))
                .andExpect(jsonPath("$.content[0].review").value("It could have been a dream, it could have been real, we will never know, but it was a great movie"))
                .andExpect(jsonPath("$.content[1].review").value("Rose could have saved Jack, there was enough space on the door"));
    }

    @Test
    public void getAllMovies_ShouldReturnNoContent_WhenNoMoviesExist() throws Exception {
        Pageable pageable = PageRequest.of(0, 50);
        Page<Movie> emptyPage = new PageImpl<>(List.of(), pageable, 0);

        when(movieRepository.findAll(pageable)).thenReturn(emptyPage);

        mockMvc.perform(get("/movies"))
                // 204 No Content
                .andExpect(status().isNoContent());
    }


    @Test
    public void getAllMovies_ShouldReturnInternalServerError_WhenExceptionIsThrown() throws Exception {
        Pageable pageable = PageRequest.of(0, 50);

        when(movieRepository.findAll(pageable)).thenThrow(new RuntimeException());

        mockMvc.perform(get("/movies"))
                // 500 Error
                .andExpect(status().isInternalServerError());
    }
}

