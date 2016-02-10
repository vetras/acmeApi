
import spark.Spark;

import dto.CompanyDto;
import java.sql.Connection;

import java.util.List;
import org.eclipse.jetty.http.HttpStatus;
import persistence.HerokuPostgress;
import service.CompanyServiceDummy;
import util.JsonUtil;
import static util.JsonUtil.json;

public class Home {

    private static String BaseUrl = "/api/v1";

    // TODO think about including DI for services
    // TODO should include unit test for services
    // TODO make the heroku persistence code
    public static void main(String[] args) {

        // TODO move to metod init apark
        // Spark.port(Integer.valueOf(System.getenv("PORT")));
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

    }

}
