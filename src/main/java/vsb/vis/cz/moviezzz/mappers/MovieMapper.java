package vsb.vis.cz.moviezzz.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vsb.vis.cz.moviezzz.database.JDBCconnection;
import vsb.vis.cz.moviezzz.models.Movie;
import vsb.vis.cz.moviezzz.models.columns.MovieColumns;

import java.sql.*;
import java.util.List;

@Service
public class MovieMapper {

    private Connection connection;

    private final String SELECT_QUERY_ID = "SELECT * FROM Movies WHERE id = ?";

    @Autowired
    public MovieMapper(Connection connection) {
        this.connection = connection;
    }

    public void insert(Movie movie) {

    }

    public void update(Movie movie) {

    }

    public Movie findById(Long id) {
        try (PreparedStatement statement = connection.prepareStatement(SELECT_QUERY_ID)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                resultSet.next();
                String name = resultSet.getString(MovieColumns.NAME);
                String description = resultSet.getString(MovieColumns.DESCRIPTION);
                Integer yearOfRelease = resultSet.getInt(MovieColumns.YEAR_OF_RELEASE);
                Boolean forAdults = resultSet.getBoolean(MovieColumns.FOR_ADULTS);
                Double price = resultSet.getDouble(MovieColumns.PRICE);
                return new Movie(id, name, description, yearOfRelease, forAdults, price);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Movie> findAll() {

        return null;
    }


}
