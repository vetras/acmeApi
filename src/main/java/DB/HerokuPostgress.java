package DB;

import com.heroku.sdk.jdbc.DatabaseUrl;

import java.net.URISyntaxException;

import java.sql.Connection;
import java.sql.SQLException;

public class HerokuPostgress implements IPersistance {

    @Override
    public Connection getConnection() throws URISyntaxException, SQLException {
        return DatabaseUrl.extract().getConnection();
    }
}
