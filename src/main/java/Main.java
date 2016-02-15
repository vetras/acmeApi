
import java.sql.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;

import spark.template.freemarker.FreeMarkerEngine;
import spark.ModelAndView;
import spark.Spark;

import com.heroku.sdk.jdbc.DatabaseUrl;
import resource.Company;
import resource.Healthz;

public class Main {

    private static final String BaseUrl = "/api/v1";

    // TODO duplicated line to return json
    // TODO not easy to modify the return type of the API
    public static void main(String[] args) {

        initSpark();

        new Healthz(BaseUrl).RegisterActions();
        new Company(BaseUrl).RegisterActions();

        // =====================================================================
        // =====================================================================
        Spark.get("/", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("message", "Hello World!");
            return new ModelAndView(attributes, "index.ftl");
        }, new FreeMarkerEngine());

        Spark.get("/db", (req, res) -> {
            Connection connection = null;
            Map<String, Object> attributes = new HashMap<>();
            try {
                connection = DatabaseUrl.extract().getConnection();

                Statement stmt = connection.createStatement();
                stmt.executeUpdate("CREATE TABLE IF NOT EXISTS ticks (tick timestamp)");
                stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
                ResultSet rs = stmt.executeQuery("SELECT tick FROM ticks");

                ArrayList<String> output = new ArrayList<String>();
                while (rs.next()) {
                    output.add("Read from DB: " + rs.getTimestamp("tick"));
                }

                attributes.put("results", output);
                return new ModelAndView(attributes, "db.ftl");
            } catch (Exception e) {
                attributes.put("message", "There was an error: " + e);
                return new ModelAndView(attributes, "error.ftl");
            } finally {
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                    }
                }
            }
        }, new FreeMarkerEngine());

    }

    private static void initSpark() throws NumberFormatException {
        String port = System.getenv("PORT");

        if (port == null || port.isEmpty()) {
            port = "5000";
        }

        Spark.port(Integer.valueOf(port));

        Spark.staticFileLocation("/public");
    }

}
