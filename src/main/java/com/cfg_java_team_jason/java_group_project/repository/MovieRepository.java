package com.cfg_java_team_jason.java_group_project.repository;

import com.cfg_java_team_jason.java_group_project.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
    List<Movie> findByTitle (String title);
}
