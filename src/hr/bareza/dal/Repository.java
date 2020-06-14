/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.bareza.dal;

import hr.bareza.models.Genre;
import hr.bareza.models.Movie;
import hr.bareza.models.Person;
import hr.bareza.models.User;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Bareza
 */
public interface Repository {
    
    //Init
    int InitAdmin() throws Exception;
    void ClearRepository()throws Exception;
    
    //Administrator
    /*int createAdministrator(Administrator data) throws Exception;
    void updateAdministrator(int id, Administrator data) throws Exception;
    void deleteAdministrator(int id) throws Exception;
    Optional<Administrator> selectAdministrator(int id) throws Exception;
    List<Administrator> selectAdministrators() throws Exception;*/
    
    //User
    int createUser(User data) throws Exception;
    void updateUser(int id, User data) throws Exception;
    int deleteUser(String username) throws Exception;
    Optional<User> selectUser(int id) throws Exception;
    Optional<User> SelectUserByUsername(String username) throws Exception;
    List<User> selectUsers() throws Exception;
    
    //Movie
    int createMovie(Movie data) throws Exception;
    void createMovies(List<Movie> movies) throws Exception;
    void updateMovie(int id, Movie data) throws Exception;
    void deleteMovie(int id) throws Exception;
    Optional<Movie> selectMovie(int id) throws Exception;
    List<Movie> selectMovies() throws Exception;
    
    
    //Person
    int createPerson(Person data) throws Exception;
    void createPeople(List<Person> people) throws Exception;
    void updatePerson(int id, Person data) throws Exception;
    int deletePerson(int id) throws Exception;
    int selectPerson(Person person) throws Exception;
    List<Person> selectPeople() throws Exception;

    //Genre
    int createGenre(int movieId, Genre genre) throws Exception;
    void createGenres(List<Genre> genres) throws Exception;
    void updateGenre(int id, Genre data) throws Exception;
    int deleteGenre(int id) throws Exception;
    int selectGenre(Genre genre) throws Exception;
    List<Genre> selectGenres() throws Exception;
    
    
    //Actor
    int createActor(int movieId, Person person) throws Exception;
    void addActorToMovie(int actorId, int movieId) throws Exception;
    void addActorsToMovie(List<Integer> actorIds, int movieId) throws Exception;
    void removeActorFromMovie(int actorId, int movieId) throws Exception;
    List<Movie> selectMoviesFromActor(int actorId) throws Exception;
    List<Person> selectActorsFromMovie(int movieId) throws Exception;
    
    //Director
    int createDirector(int movieId, Person person) throws Exception;
    void addDirectorToMovie(int directorId, int movieId) throws Exception;
    void addDirectorsToMovie(List<Integer> directorIds, int movieId) throws Exception;
    void removeDirectorFromMovie(int directorId, int movieId) throws Exception;
    List<Movie> selectMoviesFromDirector(int directorId) throws Exception;
    List<Person> selectDirectorsFromMovie(int movieId) throws Exception;
    
    //GenreMovie
    void addGenreToMovie(int movieId, Genre genre) throws Exception;
    void addGenresToMovie(List<Integer> genreIds, int movieId) throws Exception;
    void removeGenreFromMovie(int genreId, int movieId) throws Exception;
    List<Movie> selectMoviesFromGenre(int genreId) throws Exception;
    List<Genre> selectGenresFromMovie(int movieId) throws Exception;
}
