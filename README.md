# FilmVault

## Overview
FilmVault is a web application designed for movie enthusiasts to explore, review, and discuss their favorite films. Built with Spring Boot, this project serves as a backend platform, providing APIs for front-end interactions, such as browsing movies, adding reviews, and managing user profiles.

## Features
- **Movie Browsing:** Users can browse through a collection of movies, view detailed information, and read reviews.
- **User Reviews:** Authenticated users can post reviews for movies, providing ratings and comments.
- **User Authentication:** Supports user registration, login, and profile management using JWT for secure access.
- **API Integration:** Integrates external movie databases to fetch and display comprehensive movie details.
- **Security:** Implements Spring Security for authentication and authorization, ensuring that user data and actions are protected.

## Technologies
- **Spring Boot:** For creating the RESTful backend services.
- **Spring Security & JWT:** For authentication and authorization.
- **Hibernate & Spring Data JPA:** For database integration and ORM.
- **MySQL:** As the database for storing user and application data.
- **External Movie API:** For fetching movie details (integration details would be specified here).

## Getting Started

### Prerequisites
- Java JDK 11 or later
- Maven 3.6 or later
- MySQL Server (version details)

### Setup and Installation
1. Clone the repository:
git clone https://github.com/k2esone/filmvault-spring.git
cd filmvault-spring
2. Configure your MySQL database settings in `src/main/resources/application.properties` to match your local or remote setup.

3. Build the project using Maven:
mvn clean install
4. Run the application:
mvn spring-boot:run
5. The application should now be running and accessible on `http://localhost:8080`.

## API Documentation
FilmVault utilizes external movie database and requires valid API to work properly.
In src/main/resources/ create application.properties with value media.api.key=

For your own API key, go to https://developer.themoviedb.org/docs and request one for free
## Contributing
We welcome contributions to FilmVault! If you have suggestions or bug reports, please open an issue or submit a pull request.

## Acknowledgments
- Thanks to CodeCool and Software Development Academy for providing the learning platform and project guidance.
- All contributors who have helped to enrich the FilmVault application.

