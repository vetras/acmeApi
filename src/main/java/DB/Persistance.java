package DB;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;

interface IPersistance {
    Connection getConnection()  throws URISyntaxException, SQLException ;
}
