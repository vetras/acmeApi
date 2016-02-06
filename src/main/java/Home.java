
import spark.Spark;

import dto.Company;

import java.util.List;
import org.eclipse.jetty.http.HttpStatus;
import service.CompanyService;
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

            CompanyService service = new CompanyService();

            List<Company> dtos = service.getAll();

            return dtos;
        }, json());

        Spark.get(BaseUrl + "/company/:id", (req, response) -> {
            response.type("application/json");

            String id = req.params(":id");

            CompanyService service = new CompanyService();

            Company dto = service.get(id);

            if (dto == null) {
                response.status(HttpStatus.NOT_FOUND_404);
            }

            return dto;

        }, json());

        Spark.post(BaseUrl + "/company", (req, response) -> {
            response.type("application/json");

            JsonUtil<Company> util = new JsonUtil(Company.class);

            Company company = util.fromJson(req.body());

            CompanyService service = new CompanyService();

            String id = service.create(company);

            if (id == null) {
                response.status(HttpStatus.UNPROCESSABLE_ENTITY_422);
                return 0;
            }

            return service.get(id);

        }, json());

    }

}
