/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.bareza.dal.sql;

import hr.bareza.dal.Repository;
import hr.bareza.models.Genre;
import hr.bareza.models.Movie;
import hr.bareza.models.Person;
import hr.bareza.models.User;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;

/**
 *
 * @author Bareza
 */
public class SqlRepository implements Repository{

    private final DataSource dataSource = DataSourceSingleton.getInstance();
    
    //Administrator, User
    private static final String ID_USER= "IDUser";
    private static final String USERNAME= "Username";
    private static final String PASSWORD = "Password";
    private static final String ADMINISTRATOR = "Administrator";

    private static final String CREATE_ADMIN_ON_INITIALIZATION = "{ CALL CreateAdminOnInitialization (?) }";
    private static final String CLEAR_REPOSITORY = "{ CALL ClearDatabase }";
    
    private static final String CREATE_USER = "{ CALL CreateUser (?,?,?,?) }";
    private static final String UPDATE_USER = "{ CALL UpdateUser (?,?,?,?) }";
    private static final String DELETE_USER = "{ CALL DeleteUser (?,?) }";
    private static final String SELECT_USER = "{ CALL SelectUser (?) }";
    private static final String SELECT_USER_BY_USERNAME = "{ CALL SelectUserByUsername (?) }";
    private static final String SELECT_USERS = "{ CALL SelectUsers }";
    
    //Movie 
    private static final String ID_MOVIE = "IDMovie";
    private static final String TITLE= "Title";
    private static final String DATE_PUBLISHED = "DatePublished";
    private static final String DESCRIPTION = "Description";
    private static final String ORIGINAL_TITLE = "OriginalTitle";
    private static final String DURATION = "Duration";
    private static final String PICTURE_PATH = "PicturePath";
    private static final String LINK = "Link";
    
    private static final String CREATE_MOVIE = "{ CALL CreateMovie (?,?,?,?,?,?,?,?) }";
    private static final String UPDATE_MOVIE = "{ CALL UpdateMovie (?,?,?,?,?,?,?,?) }";
    private static final String DELETE_MOVIE = "{ CALL DeleteMovie (?) }";
    private static final String SELECT_MOVIE = "{ CALL SelectMovie (?) }";
    private static final String SELECT_MOVIES = "{ CALL SelectMovies }";
    
    //Person
    private static final String ID_PERSON = "IDPerson";
    private static final String FIRST_NAME = "FirstName";
    private static final String LAST_NAME = "LastName";
    
    private static final String CREATE_PERSON = "{ CALL CreatePerson (?,?,?) }";
    private static final String UPDATE_PERSON = "{ CALL UpdatePerson (?,?,?) }";
    private static final String DELETE_PERSON = "{ CALL DeletePerson (?,?) }";
    private static final String SELECT_PERSON = "{ CALL SelectPerson (?,?,?) }";
    private static final String SELECT_PEOPLE = "{ CALL SelectPeople }";
    
    //Genre
    private static final String ID_GENRE = "IDGenre";
    private static final String NAME = "Name";
    
    private static final String CREATE_GENRE = "{ CALL CreateGenre (?,?,?) }";
    private static final String UPDATE_GENRE = "{ CALL UpdateGenre (?,?) }";
    private static final String DELETE_GENRE = "{ CALL DeleteGenre (?,?) }";
    private static final String SELECT_GENRE = "{ CALL SelectGenre (?,?) }";
    private static final String SELECT_GENRES = "{ CALL SelectGenres }";
    
    //Actor
    private static final String MOVIE_ID = "MovieID";
    private static final String PERSON_ID = "PersonID";
    
    private static final String CREATE_ACTOR = "{ CALL CreateActor (?,?,?,?) }";
    private static final String ADD_ACTOR_TO_MOVIE = "{ CALL AddActorToMovie (?,?) }";
    private static final String REMOVE_ACTOR_FROM_MOVIE = "{ CALL RemoveActorFromMovie (?,?) }";
    private static final String GET_MOVIES_FROM_ACTOR = "{ CALL GetMoviesFromActor (?) }";
    private static final String SELECT_ACTORS_FROM_MOVIE = "{ CALL SelectActorsFromMovie (?) }";
    
    //Director
    private static final String CREATE_DIRECTOR = "{ CALL CreateDirector (?,?,?,?) }";
    private static final String ADD_DIRECTOR_TO_MOVIE = "{ CALL AddDirectorToMovie (?,?) }";
    private static final String REMOVE_DIRECTOR_FROM_MOVIE = "{ CALL RemoveDirectorFromMovie (?,?) }";
    private static final String GET_MOVIES_FROM_DIRECTOR = "{ CALL GetMoviesFromDirector (?) }";
    private static final String SELECT_DIRECTORS_FROM_MOVIE = "{ CALL SelectDirectorsFromMovie (?) }";
    
    //GenreMovie
    private static final String GENRE_ID = "GenreID";
    
    private static final String ADD_GENRE_TO_MOVIE = "{ CALL AddGenreToMovie (?,?) }";
    private static final String REMOVE_GENRE_FROM_MOVIE = "{ CALL RemoveGenreFromMovie (?,?) }";
    private static final String GET_MOVIES_FROM_GENRE = "{ CALL GetMoviesFromGenre (?) }";
    private static final String GET_GENRES_FROM_MOVIE = "{ CALL GetGenresFromMovie (?) }";
    
        @Override
    public int InitAdmin() throws Exception {
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_ADMIN_ON_INITIALIZATION)) {
            
            stmt.registerOutParameter(1, Types.INTEGER);
            
            stmt.executeUpdate();
            return stmt.getInt(1);
        }
    }
    
    @Override
    public void ClearRepository() throws Exception {
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CLEAR_REPOSITORY)) {
            
            stmt.executeUpdate();
        }
    }
    
    //User
    @Override
    public int createUser(User data) throws Exception {
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_USER)) {
            
            stmt.setString(1, data.getUsername());
            stmt.setInt(2, data.getPassword());
            stmt.setBoolean(3, data.isAdministrator());
            stmt.registerOutParameter(4, Types.INTEGER);
            
            stmt.executeUpdate();
            return stmt.getInt(4);
        }
    }

    @Override
    public void updateUser(int id, User data) throws Exception {
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(UPDATE_USER)) {
            
            stmt.setInt(1, id);
            stmt.setString(2, data.getUsername());
            stmt.setInt(3, data.getPassword());
            stmt.setBoolean(4, data.isAdministrator());
            
            stmt.executeUpdate();
        }
    }

    @Override
    public int deleteUser(String username) throws Exception {
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(DELETE_USER)) {
            
            stmt.setString(1, username);
            stmt.registerOutParameter(2, Types.INTEGER);
            
            stmt.executeUpdate();
            return stmt.getInt(2);
        }
    }

    @Override
    public Optional<User> selectUser(int id) throws Exception {
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_USER)) {
            
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new User(
                            rs.getInt(ID_USER),
                            rs.getString(USERNAME),
                            rs.getInt(PASSWORD),
                            rs.getBoolean(ADMINISTRATOR)));
                }
            } 
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> SelectUserByUsername(String username) throws Exception {
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_USER_BY_USERNAME)) {
            
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new User(
                            rs.getInt(ID_USER),
                            rs.getString(USERNAME),
                            rs.getInt(PASSWORD),
                            rs.getBoolean(ADMINISTRATOR)));
                }
            } 
        }
        return Optional.empty();
    }
    
    @Override
    public List<User> selectUsers() throws Exception {
        List<User> users = new ArrayList<>();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_USERS);
                ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {                
                users.add(new User(
                        rs.getInt(ID_USER),
                        rs.getString(USERNAME),
                        rs.getInt(PASSWORD),
                        rs.getBoolean(ADMINISTRATOR)));
            }
        }
        return users;
    }

    //Movie
    @Override
    public int createMovie(Movie data) throws Exception {
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_MOVIE)) {
            
            stmt.setString(1, data.getTitle());
            stmt.setString(2, data.getPublishedDate().format(Movie.DATE_FORMATTER));
            stmt.setString(3, data.getDescription());
            stmt.setString(4, data.getOriginalTitle());
            stmt.setInt(5, data.getDuration());
            stmt.setString(6, data.getPicturePath());
            stmt.setString(7, data.getLink());
            stmt.registerOutParameter(8, Types.INTEGER);
            
            stmt.executeUpdate();
            return stmt.getInt(8);
        }
    }

    @Override
    public void createMovies(List<Movie> movies) throws Exception {
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_MOVIE)) {

            for (Movie data : movies) {
                stmt.setString(1, data.getTitle());
                stmt.setString(2, data.getPublishedDate().format(Movie.DATE_FORMATTER));
                stmt.setString(3, data.getDescription());
                stmt.setString(4, data.getOriginalTitle());
                stmt.setInt(5, data.getDuration());
                stmt.setString(6, data.getPicturePath());
                stmt.setString(7, data.getLink());
                stmt.registerOutParameter(8, Types.INTEGER);
                
                stmt.executeUpdate();
            }
        }
    }
    
    @Override
    public void updateMovie(int id, Movie data) throws Exception {
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(UPDATE_MOVIE)) {
            
            stmt.setInt(1, id);
            stmt.setString(2, data.getTitle());
            stmt.setString(3, data.getPublishedDate().format(Movie.DATE_FORMATTER));
            stmt.setString(4, data.getDescription());
            stmt.setString(5, data.getOriginalTitle());
            stmt.setInt(6, data.getDuration());
            stmt.setString(7, data.getPicturePath());
            stmt.setString(8, data.getLink());
            
            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteMovie(int id) throws Exception {
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(DELETE_MOVIE)) {
            
            stmt.setInt(1, id);
            
            stmt.executeUpdate();
        }
    }

    @Override
    public Optional<Movie> selectMovie(int id) throws Exception {
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_MOVIE)) {
            
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new Movie(
                            rs.getInt(ID_MOVIE),
                            rs.getString(TITLE),
                            LocalDateTime.parse(rs.getString(DATE_PUBLISHED), Movie.DATE_FORMATTER),
                            rs.getString(DESCRIPTION),
                            rs.getString(ORIGINAL_TITLE),
                            rs.getInt(DURATION),
                            rs.getString(PICTURE_PATH),
                            rs.getString(LINK)));
                }
            } 
        }
        return Optional.empty();
    }

    @Override
    public List<Movie> selectMovies() throws Exception {
        List<Movie> movies = new ArrayList<>();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_MOVIES);
                ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {                
                movies.add(new Movie(
                        rs.getInt(ID_MOVIE),
                        rs.getString(TITLE),
                        LocalDateTime.parse(rs.getString(DATE_PUBLISHED), Movie.DATE_FORMATTER),
                        rs.getString(DESCRIPTION),
                        rs.getString(ORIGINAL_TITLE),
                        rs.getInt(DURATION),
                        rs.getString(PICTURE_PATH),
                        rs.getString(LINK)));
            }
        }
        return movies;
    }

    //Person
    @Override
    public int createPerson(Person data) throws Exception {
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_PERSON)) {
            
            stmt.setString(1, data.getFirstName());
            stmt.setString(2, data.getLastName());
            stmt.registerOutParameter(3, Types.INTEGER);

            stmt.executeUpdate();
            return stmt.getInt(3);
        }
    }

    @Override
    public void createPeople(List<Person> people) throws Exception {
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_PERSON)) {

            for (Person data : people) {
                stmt.setString(1, data.getFirstName());
                stmt.setString(2, data.getLastName());
                stmt.registerOutParameter(3, Types.INTEGER);
                
                stmt.executeUpdate();
            }
        }
    }
    
    @Override
    public void updatePerson(int id, Person data) throws Exception {
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(UPDATE_PERSON)) {
            
            stmt.setInt(1, id);
            stmt.setString(2, data.getFirstName());
            stmt.setString(3, data.getLastName());

            stmt.executeUpdate();
        }
    }

    @Override
    public int deletePerson(int id) throws Exception {
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(DELETE_PERSON)) {
            
            stmt.setInt(1, id);
            stmt.registerOutParameter(2, Types.INTEGER);
            
            stmt.executeUpdate();
            return stmt.getInt(2);
        } 
    }

    @Override
    public int selectPerson(Person person) throws Exception {
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_PERSON)) {
           
            stmt.setString(1, person.getFirstName());
            stmt.setString(2, person.getLastName());
            stmt.registerOutParameter(3, Types.INTEGER);
            
            stmt.executeUpdate();
            return stmt.getInt(3);
        }
    }

    @Override
    public List<Person> selectPeople() throws Exception {
        List<Person> people = new ArrayList<>();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_PEOPLE);
                ResultSet rs = stmt.executeQuery()) {
          
            while (rs.next()) {
                people.add(new Person(
                                rs.getInt(ID_PERSON),
                                rs.getString(FIRST_NAME),
                                rs.getString(LAST_NAME)));            }
        } 
        return people;
    }

    //Genre
    @Override
    public int createGenre(int movieId, Genre genre) throws Exception {
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_GENRE)) {
            
            stmt.setInt(1, movieId);
            stmt.setString(2, genre.getName());
            stmt.registerOutParameter(3, Types.INTEGER);

            stmt.executeUpdate();
            return stmt.getInt(3);
        }
    }

    @Override
    public void createGenres(List<Genre> genres) throws Exception {
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_GENRE)) {

            for (Genre data : genres) {
                stmt.setString(1, data.getName());
                stmt.registerOutParameter(2, Types.INTEGER);

                stmt.executeUpdate();
            }
        }
    }
    
    @Override
    public void updateGenre(int id, Genre data) throws Exception {
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(UPDATE_GENRE)) {
            
            stmt.setInt(1, id);
            stmt.setString(2, data.getName());

            stmt.executeUpdate();
        }
    }

    @Override
    public int deleteGenre(int id) throws Exception {
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(DELETE_GENRE)) {
            
            stmt.setInt(1, id);
            stmt.registerOutParameter(2, Types.INTEGER);
            
            stmt.executeUpdate();
            return stmt.getInt(2);
        } 
    }

    @Override
    public int selectGenre(Genre genre) throws Exception {
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_GENRE)) {
           
            stmt.setString(1, genre.getName());
            stmt.registerOutParameter(2, Types.INTEGER);
            
            stmt.executeUpdate();
            return stmt.getInt(2);
        }
    }

    @Override
    public List<Genre> selectGenres() throws Exception {
        List<Genre> genres = new ArrayList<>();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_GENRES);
                ResultSet rs = stmt.executeQuery()) {
          
            while (rs.next()) {
                genres.add(new Genre(
                                rs.getInt(ID_GENRE),
                                rs.getString(NAME)));
            }
        } 
        return genres;
    }

    //Actor
    @Override
    public int createActor(int movieId, Person person) throws Exception {
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_ACTOR)) {
            
            stmt.setInt(1, movieId);
            stmt.setString(2, person.getFirstName());
            stmt.setString(3, person.getLastName());
            stmt.registerOutParameter(4, Types.INTEGER);

            stmt.executeUpdate();
            return stmt.getInt(4);
        }
    }
    
    @Override
    public void addActorToMovie(int actorId, int movieId) throws Exception {
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(ADD_ACTOR_TO_MOVIE)) {
            
            stmt.setInt(1, actorId);
            stmt.setInt(2, movieId);
            
            stmt.executeUpdate();
        } 
    }

    @Override
    public void addActorsToMovie(List<Integer> actorIds, int movieId) throws Exception {
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(ADD_ACTOR_TO_MOVIE)) {

            for (Integer actorId : actorIds) {
                stmt.setInt(1, actorId);
                stmt.setInt(2, movieId);
            
                stmt.executeUpdate();
            }
        }
    }
    
    @Override
    public void removeActorFromMovie(int actorId, int movieId) throws Exception {
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(REMOVE_ACTOR_FROM_MOVIE)) {
            
            stmt.setInt(1, actorId);
            stmt.setInt(2, movieId);
            
            stmt.executeUpdate();
        } 
    }

    @Override
    public List<Movie> selectMoviesFromActor(int actorId) throws Exception {
        List<Movie> movies = new ArrayList<>();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(GET_MOVIES_FROM_ACTOR)) {
            
            stmt.setInt(1, actorId);
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {                
                movies.add(new Movie(
                        rs.getInt(ID_MOVIE),
                        rs.getString(TITLE),
                        LocalDateTime.parse(rs.getString(DATE_PUBLISHED), Movie.DATE_FORMATTER),
                        rs.getString(DESCRIPTION),
                        rs.getString(ORIGINAL_TITLE),
                        rs.getInt(DURATION),
                        rs.getString(PICTURE_PATH),
                        rs.getString(LINK)));
            }
        }
        return movies;
    }

    @Override
    public List<Person> selectActorsFromMovie(int movieId) throws Exception {
        List<Person> people = new ArrayList<>();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_ACTORS_FROM_MOVIE)) {
            
            stmt.setInt(1, movieId);
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {                
                people.add(new Person(
                        rs.getInt(ID_PERSON),
                        rs.getString(FIRST_NAME),
                        rs.getString(LAST_NAME)));
            }
        }
        return people;
    }

    //Director
    @Override
    public int createDirector(int movieId, Person person) throws Exception {
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_DIRECTOR)) {
            
            stmt.setInt(1, movieId);
            stmt.setString(2, person.getFirstName());
            stmt.setString(3, person.getLastName());
            stmt.registerOutParameter(4, Types.INTEGER);

            stmt.executeUpdate();
            return stmt.getInt(4);
        }
    }
    
    @Override
    public void addDirectorToMovie(int directorId, int movieId) throws Exception {
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(ADD_DIRECTOR_TO_MOVIE)) {
            
            stmt.setInt(1, directorId);
            stmt.setInt(2, movieId);
            
            stmt.executeUpdate();
        } 
    }

    @Override
    public void addDirectorsToMovie(List<Integer> directorIds, int movieId) throws Exception {
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(ADD_DIRECTOR_TO_MOVIE)) {

            for (Integer directorId : directorIds) {
                stmt.setInt(1, directorId);
                stmt.setInt(2, movieId);
            
                stmt.executeUpdate();
            }
        }
    }
    
    @Override
    public void removeDirectorFromMovie(int directorId, int movieId) throws Exception {
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(REMOVE_DIRECTOR_FROM_MOVIE)) {
            
            stmt.setInt(1, directorId);
            stmt.setInt(2, movieId);
            
            stmt.executeUpdate();
        } 
    }

    @Override
    public List<Movie> selectMoviesFromDirector(int directorId) throws Exception {
        List<Movie> movies = new ArrayList<>();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(GET_MOVIES_FROM_DIRECTOR)) {
            
            stmt.setInt(1, directorId);
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {                
                movies.add(new Movie(
                        rs.getInt(ID_MOVIE),
                        rs.getString(TITLE),
                        LocalDateTime.parse(rs.getString(DATE_PUBLISHED), Movie.DATE_FORMATTER),
                        rs.getString(DESCRIPTION),
                        rs.getString(ORIGINAL_TITLE),
                        rs.getInt(DURATION),
                        rs.getString(PICTURE_PATH),
                        rs.getString(LINK)));
            }
        }
        return movies;
    }

    @Override
    public List<Person> selectDirectorsFromMovie(int movieId) throws Exception {
        List<Person> people = new ArrayList<>();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_DIRECTORS_FROM_MOVIE)) {
            
            stmt.setInt(1, movieId);
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {                
                people.add(new Person(
                        rs.getInt(ID_PERSON),
                        rs.getString(FIRST_NAME),
                        rs.getString(LAST_NAME)));
            }
        }
        return people;
    }

    //GenreMovie
    @Override
    public void addGenreToMovie(int movieId, Genre genre) throws Exception {
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(ADD_GENRE_TO_MOVIE)) {
            
            stmt.setInt(1, genre.getId());
            stmt.setInt(2, movieId);
            
            stmt.executeUpdate();
        } 
    }

    @Override
    public void addGenresToMovie(List<Integer> genreIds, int movieId) throws Exception {
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(ADD_GENRE_TO_MOVIE)) {

            for (Integer genreId : genreIds) {
                stmt.setInt(1, genreId);
                stmt.setInt(2, movieId);
            
                stmt.executeUpdate();
            }
        }
    }
    
    @Override
    public void removeGenreFromMovie(int genreId, int movieId) throws Exception {
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(REMOVE_GENRE_FROM_MOVIE)) {
            
            stmt.setInt(1, genreId);
            stmt.setInt(2, movieId);
            
            stmt.executeUpdate();
        } 
    }

    @Override
    public List<Movie> selectMoviesFromGenre(int genreId) throws Exception {
        List<Movie> movies = new ArrayList<>();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(GET_MOVIES_FROM_GENRE)) {
            
            stmt.setInt(1, genreId);
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {                
                movies.add(new Movie(
                        rs.getInt(ID_MOVIE),
                        rs.getString(TITLE),
                        LocalDateTime.parse(rs.getString(DATE_PUBLISHED), Movie.DATE_FORMATTER),
                        rs.getString(DESCRIPTION),
                        rs.getString(ORIGINAL_TITLE),
                        rs.getInt(DURATION),
                        rs.getString(PICTURE_PATH),
                        rs.getString(LINK)));
            }
        }
        return movies;
    }

    @Override
    public List<Genre> selectGenresFromMovie(int movieId) throws Exception {
        List<Genre> genres = new ArrayList<>();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(GET_GENRES_FROM_MOVIE)) {
            
            stmt.setInt(1, movieId);
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {                
                genres.add(new Genre(
                        rs.getInt(ID_GENRE),
                        rs.getString(NAME)));
            }
        }
        return genres;
    }

}
