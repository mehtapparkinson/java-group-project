#### java group project - Team Json

# Movie Tracker System :movie_camera: :film_strip:

## Project Overview :clapper:

The **Movie Tracker System** is a Spring Boot application designed to help users track their favorite movies.
It allows users to perform actions such as adding new movies, viewing their movie collection, and tracking details
about each movie (e.g., title, personal review of each film, personal rating and date added).

## Application Requirements :clapper:

- The system allows users to manage a collection of movies.
- Users can add, view, update, and delete movies from their collection.
- Each movie will have attributes such as:
    - Title
    - Personal review
    - Date added
    - Rating 
- Data is stored in a MySQL database, and the application provides REST endpoints to interact with this data.


## How We Will Achieve This :clapper:

1. **Spring Boot Application**: A Spring Boot was created application using the Spring Initializr to generate the necessary project structure.
2. **REST Endpoints**: We implemented RESTful endpoints to perform CRUD operations (Create, Read, Update, Delete) on movie data.
3. **Database Integration**: MySQL was used as the database for storing movie information. Spring Data JPA was used to manage the interactions between the application and the database.
4. **Unit Testing**:  JUnit was used for unit testing and Mockito to mock external dependencies.
5. **Exception Handling**: Implemented appropriate exception handling using custom exceptions and Spring's global exception handling features.
6. **Docker**: The application will be containerized using Docker. We'll create a Dockerfile and Docker Compose file for easier deployment and scalability.
7. **Logging**: We used SLF4J for logging throughout the application.


## Manual Test using Postman or Insomnia :clapper:

1. **Add a Movie**
- Input valid movie details (title, director, genre, etc.).
- Verify the movie is saved correctly in the database.

2. **View Movies**
- Retrieve a list of movies and check if the information is correct.

3. **Update a Movie**
- Edit movie details and ensure the changes are reflected in the database.

4. **Delete a Movie**
- Delete a movie and verify it's removed from the database.

## REST Endpoints :clapper:

We will implement the following REST endpoints:

1. **Add a Movie**
- `POST /movies`
- Request Body: JSON object with movie details.
- Response: Success message and the created movie object.

2. **Get All Movies**
- `GET /movies`
- Response: A list of all movies in the collection.

**More endpoints**

## Database Setup :clapper:

1. **MySQL**:  
   A MySQL database was created using MySQL Workbench. The database schema will include a `movies` table with columns
   corresponding to the movie attributes (title, director, etc.).

2. **JPA Entities**:  
   An entity class `Movie` was created that maps to the `movies` table in the database, and a repository interface
   `MovieRepository` to handle CRUD operations.

3. **MySQL Script**:  
   The MySQL script for creating and initializing the database is provided in the `SQL` directory of the project.

## Running Instructions :clapper:

1. **Prerequisites**:
- Java 21
- MySQL
- Docker (for containerization)

2. **Steps**:
- Clone the repository.
- Set up the database using the MySQL script provided.
- Run the application using `./mvnw spring-boot:run`.
- Access the application at `http://localhost:8080`.

## OpenAPI Specification :clapper:

The OpenAPI specification for the REST API can be accessed at:  
`[link]`

## Deployment Plan :clapper:

1. **Continuous Integration**:
- GitHub Actions will be used for continuous integration, with stages for building, testing, and deploying the application.

2. **Docker**:
- The application will be containerized using Docker. A Dockerfile and a `docker-compose.yaml` will be provided to run the application along with a MySQL container.

3. **Pipeline**:
- **Stage 1**: Build and test the application.
- **Stage 2**: Build Docker image.
- **Stage 3**: Deploy the application using Docker Compose.
