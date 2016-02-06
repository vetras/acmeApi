import java.sql.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;

import java.net.URI;
import java.net.URISyntaxException;

import spark.template.freemarker.FreeMarkerEngine;
import spark.ModelAndView;
import spark.Spark;

import com.heroku.sdk.jdbc.DatabaseUrl;
import dto.Company;
import java.util.List;
import service.CompanyService;
import static util.JsonUtil.json;

public class Home {
    private static String BaseUrl = "/api/v1";

  public static void main(String[] args) {

     // TODO move to metod init apark
    //Spark.port(Integer.valueOf(System.getenv("PORT")));
    

    
    Spark.get(BaseUrl + "/health", (req, res) -> "It is Alive !");

    
    Spark.get(BaseUrl + "/company", (req, res) -> {
            res.type("application/json");
            
            CompanyService service = new CompanyService();
            
            List<Company> dtos = service.getAll();

           return dtos;
        }, json());


  }

}
