package resource;

import dto.CompanyDto;
import java.util.List;
import org.eclipse.jetty.http.HttpStatus;
import service.CompanyServiceDummy;
import service.ICompany;
import spark.Spark;
import util.JsonUtil;
import static util.JsonUtil.json;

public class Company extends Resource {

    public Company(String BaseUrl) {
        super(BaseUrl);
    }

    @Override
    public Resource RegisterActions() {

        Spark.get(BaseUrl + "/company", (req, response) -> {
            response.type("application/json");

            ICompany service = new CompanyServiceDummy();

            List<CompanyDto> dtos = service.getAll();

            return dtos;
        }, json());

        Spark.get(BaseUrl + "/company/:id", (req, response) -> {
            response.type("application/json");

            String id = req.params(":id");

            ICompany service = new CompanyServiceDummy();

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

            ICompany service = new CompanyServiceDummy();

            String id = service.create(company);

            if (id == null) {
                response.status(HttpStatus.UNPROCESSABLE_ENTITY_422);
                return 0;
            }

            return service.get(id);

        }, json());

        return this;
    }
}
