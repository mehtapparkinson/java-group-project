CREATE DATABASE MovieTrackerDB;
USE MovieTrackerDB;

CREATE TABLE Movies (
    movie_id BIGINT AUTO_INCREMENT PRIMARY KEY,  
    title VARCHAR(300) NOT NULL,                
	review VARCHAR(1000),
    added_at DATE,
	star INT CHECK (star BETWEEN 1 AND 5)
);



INSERT INTO Movies (title, added_at, review, star)
VALUES 
('The Matrix', '2024-09-01', 'An amazing blend of action, philosophy, and sci-fi effects.', 5),
('The Dark Knight', '2024-09-02', 'A thrilling superhero film with a captivating villain performance.', 5),
('Inception', '2024-09-03', 'Mind-bending plot with incredible visual effects.', 4),
('Interstellar','2024-09-04', 'A masterpiece of science fiction with stunning visuals and emotion.', 5),
('Pulp Fiction','2024-09-05', 'A nonlinear narrative that’s still gripping after all these years.', 4),
('The Shawshank Redemption', '2024-09-06', 'A powerful tale of hope and friendship, masterfully told.', 5),
('Fight Club', '2024-09-07', 'An intense psychological drama with a twist you won’t see coming.', 4),
('Forrest Gump', '2024-09-08', 'Heartwarming, emotional, and filled with unforgettable moments.', 5),
('The Lord of the Rings: The Fellowship of the Ring', '2024-09-09', 'An epic adventure that redefined the fantasy genre.', 5),
('The Godfather', '2024-09-10', 'A gripping and intense mafia drama, brilliantly acted.', 5);


SELECT * FROM movies;
