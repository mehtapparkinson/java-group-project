CREATE DATABASE MovieTrackerDB;
USE MovieTrackerDB;

CREATE TABLE Movies (
    movie_id BIGINT AUTO_INCREMENT PRIMARY KEY,  
    title VARCHAR(300) NOT NULL,                
    genre VARCHAR(100),
    added_at DATE

);


CREATE TABLE Reviews (
    review_id BIGINT AUTO_INCREMENT PRIMARY KEY, 
    movie_id BIGINT,                              
    review VARCHAR(1000),
    star INT CHECK (star BETWEEN 1 AND 5),
    FOREIGN KEY (movie_id) REFERENCES Movies(movie_id) ON DELETE CASCADE 
);


INSERT INTO Movies (title, genre, added_at)
VALUES 
('The Matrix', 'Sci-Fi', '2024-09-01'),
('The Dark Knight', 'Action', '2024-09-02'),
('Inception', 'Sci-Fi', '2024-09-03'),
('Interstellar', 'Sci-Fi', '2024-09-04'),
('Pulp Fiction', 'Crime', '2024-09-05'),
('The Shawshank Redemption', 'Drama', '2024-09-06'),
('Fight Club', 'Drama', '2024-09-07'),
('Forrest Gump', 'Drama', '2024-09-08'),
('The Lord of the Rings: The Fellowship of the Ring', 'Fantasy', '2024-09-09'),
('The Godfather', 'Crime', '2024-09-10');


SELECT * FROM movies;

INSERT INTO Reviews (movie_id, review, star)
VALUES 
(1, 'An amazing blend of action, philosophy, and sci-fi effects.', 5),
(2, 'A thrilling superhero film with a captivating villain performance.', 5),
(3, 'Mind-bending plot with incredible visual effects.', 4),
(4, 'A masterpiece of science fiction with stunning visuals and emotion.', 5),
(5, 'A nonlinear narrative that’s still gripping after all these years.', 4),
(6, 'A powerful tale of hope and friendship, masterfully told.', 5),
(7, 'An intense psychological drama with a twist you won’t see coming.', 4),
(8, 'Heartwarming, emotional, and filled with unforgettable moments.', 5),
(9, 'An epic adventure that redefined the fantasy genre.', 5),
(10, 'A gripping and intense mafia drama, brilliantly acted.', 5);

SELECT * FROM reviews;

SELECT 
    m.movie_id, 
    m.title, 
    r.review, 
    r.star 
FROM 
    Movies m
LEFT JOIN 
    Reviews r ON m.movie_id = r.movie_id;


