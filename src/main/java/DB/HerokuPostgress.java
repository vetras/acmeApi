package DB;

import com.heroku.sdk.jdbc.DatabaseUrl;
import dto.CompanyId;

import java.net.URISyntaxException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HerokuPostgress implements IPersistance {

    @Override
    public Connection getConnection() throws URISyntaxException, SQLException {
        return DatabaseUrl.extract().getConnection();
    }
    
//    
//    @Override
//    public Connection ReadCompanyById(CompanyId id) {   
//    
//    Connection connection = getConnection();
//    
//      Map<String, Object> attributes = new HashMap<>();
//      
//      try {
//        Statement stmt = connection.createStatement();
//        
//        // TODO there is no pagination
//        ResultSet rs = stmt.executeQuery("SELECT * FROM companies");
//
//        ArrayList<String> output = new ArrayList<String>();
//        while (rs.next()) {
//          output.add( "Read from DB: " + rs.getTimestamp("tick"));
//        }
//
//        attributes.put("results", output);
//        return new ModelAndView(attributes, "db.ftl");
//      } catch (Exception e) {
//        attributes.put("message", "There was an error: " + e);
//        return new ModelAndView(attributes, "error.ftl");
//      } finally {
//        if (connection != null) try{connection.close();} catch(SQLException e){}
//      }
//    }
//    
//    
//    @Override
//    public Connection Save(Company dto) {   
//    
//    Connection connection = getConnection();
//    
//      Map<String, Object> attributes = new HashMap<>();
//      
//      try {
//        Statement stmt = connection.createStatement();
//        
//        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS ticks (tick timestamp)");
//        stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
//        ResultSet rs = stmt.executeQuery("SELECT tick FROM ticks");
//
//        ArrayList<String> output = new ArrayList<String>();
//        while (rs.next()) {
//          output.add( "Read from DB: " + rs.getTimestamp("tick"));
//        }
//
//        attributes.put("results", output);
//        return new ModelAndView(attributes, "db.ftl");
//      } catch (Exception e) {
//        attributes.put("message", "There was an error: " + e);
//        return new ModelAndView(attributes, "error.ftl");
//      } finally {
//        if (connection != null) try{connection.close();} catch(SQLException e){}
//      }
//    }
}
