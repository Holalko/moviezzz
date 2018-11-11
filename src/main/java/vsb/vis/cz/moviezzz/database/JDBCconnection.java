package vsb.vis.cz.moviezzz.database;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

@Configuration
public class JDBCconnection {

    @Value("${password}")
    private String password;

    @Value("${username}")
    private String username;

    @Value("${url}")
    private String url;


    /**
     * Conenction to database
     * Singleton
     *
     * @return
     * @throws SQLException
     */
    @Bean
    public Connection connection() {
        Properties props = new Properties();
        props.setProperty("user", username);
        props.setProperty("password", password);
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(url, props);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }


}
