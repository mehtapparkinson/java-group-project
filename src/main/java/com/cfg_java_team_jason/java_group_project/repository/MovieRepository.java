package com.cfg_java_team_jason.java_group_project.repository;

import com.cfg_java_team_jason.java_group_project.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface MovieRepository extends JpaRepository<Movie, Integer> {
    List<Movie> findByTitle(String title);
}
