package com.cfg_java_team_jason.java_group_project.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
@Entity
@Data
@Table(name ="movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int movie_id;
    @NotBlank(message = "Title is mandatory")
    @Size(max = 300, message = "Title cannot be longer than 300 characters")
    private String title;
    @Size(max = 1000, message = "Review cannot be longer than 1000 characters")
    private String review;
    @Column(name = "added_at")
    private LocalDate date = LocalDate.now();
    @Min(value = 1, message = "Star rating must be at least 1")
    @Max(value = 5, message = "Star rating must be less than 5")
    private int star;

}
