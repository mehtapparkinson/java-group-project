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

    //update movie when movie exists
    // return not found when movie doesnt exist
    //return 500 when exception is thrown

    // PutMapping endpoint test

    @Test
    public void updateMovie_ShouldUpdateMovie_when_MovieExists() throws Exception {

        //mock data
        Movie updatedMovie = new Movie();
        updatedMovie.setTitle("Updated Title");
        updatedMovie.setReview("Updated Review");
        updatedMovie.setStar(5);

        when(movieRepository.existsById(1)).thenReturn(true);
        when(movieRepository.save(any(Movie.class))).thenReturn(updatedMovie);

        String updatedMovieJson = "{ \"title\": \"Updated Title\", \"review\": \"Updated Review\", \"star\": 5 }";

        mockMvc.perform(put("/movies/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)  // Ensure content type is JSON
                .content(updatedMovieJson))
                .andExpect(status().isOk())
                .andExpect(content().json( "{ \"title\": \"Updated Title\", \"review\": \"Updated Review\", \"star\": 5 }"));

        verify(movieRepository, times(1)).save(any(Movie.class));

    }
    @Test
    public void updateMovie_ShouldReturn404_when_MovieDoesNotExists() throws Exception {

        when(movieRepository.existsById(1)).thenReturn(false);
        String movieJson = "{ \"title\": \"Test Title\", \"review\": \"Test Review\", \"star\": 3 }";

        mockMvc.perform(put("/movies/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(movieJson))
                .andExpect(status().isNotFound());
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
    @SneakyThrows
    public void getAllMovies_ShouldReturnListOfMovies_WhenMoviesExist() throws Exception {
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
    public void getAllMovies_ShouldReturnNoContent_WhenNoMoviesExist() throws Exception {
        when(movieRepository.findAll()).thenReturn(List.of());
        mockMvc.perform(get("/movies"))
                // 204 No Content
                .andExpect(status().isNoContent());
    }

    @Test
    @SneakyThrows
    public void getAllMovies_ShouldReturnInternalServerError_WhenExceptionIsThrown() throws Exception {
        when(movieRepository.findAll()).thenThrow(new RuntimeException());
        mockMvc.perform(get("/movies"))
                // 500 Error
                .andExpect(status().isInternalServerError());
    }
}

