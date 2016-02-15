
import java.sql.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;

import spark.template.freemarker.FreeMarkerEngine;
import spark.ModelAndView;
import spark.Spark;

import com.heroku.sdk.jdbc.DatabaseUrl;
import dto.CompanyDto;
import java.util.List;
import org.eclipse.jetty.http.HttpStatus;
import service.CompanyServiceDummy;
import util.JsonUtil;
import static util.JsonUtil.json;

public class Main {

    private static final String BaseUrl = "/api/v1";

    public static void main(String[] args) {

        String port = System.getenv("PORT");
        
        if (port == null || port.isEmpty()) {
            port = "5000";
        }
        
        Spark.port(Integer.valueOf(port));
        Spark.staticFileLocation("/public");

        // TODO duplicated line to return json
        // TODO not easy to modify the return type of the API
        Spark.get(BaseUrl + "/health", (req, res) -> "It is Alive !");

        Spark.get(BaseUrl + "/company", (req, response) -> {
            response.type("application/json");

            CompanyServiceDummy service = new CompanyServiceDummy();

            List<CompanyDto> dtos = service.getAll();

            return dtos;
        }, json());

        Spark.get(BaseUrl + "/company/:id", (req, response) -> {
            response.type("application/json");

            String id = req.params(":id");

            CompanyServiceDummy service = new CompanyServiceDummy();

            CompanyDto dto = service.get(id);

            if (dto == null) {
                response.status(HttpStatus.NOT_FOUND_404);
            }

            return dto;

        }, json());

        Spark.post(BaseUrl + "/company", (req, response) -> {
            response.type("application/json");

            JsonUtil<CompanyDto> util = new JsonUtil(CompanyDto.class);

            CompanyDto company = util.fromJson(req.body());

            CompanyServiceDummy service = new CompanyServiceDummy();

            String id = service.create(company);

            if (id == null) {
                response.status(HttpStatus.UNPROCESSABLE_ENTITY_422);
                return 0;
            }

            return service.get(id);

        }, json());

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

}
