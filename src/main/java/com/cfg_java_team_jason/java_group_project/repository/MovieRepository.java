package com.cfg_java_team_jason.java_group_project.repository;

import com.cfg_java_team_jason.java_group_project.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MovieRepository extends JpaRepository<Movie, Integer> {
}
