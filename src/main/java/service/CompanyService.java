package service;

import dto.Company;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CompanyService {

    // TODO this is dummy
    private static final List<Company> list = new ArrayList();

    public CompanyService() {
        Company a = new Company();
        Company b = new Company();
        a.Id = "1";
        b.Id = "2";
        a.Name = "barbosa";
        b.Name = "vidigal";
        a.Adress = "aveiro";
        b.Adress = "viseu";
        list.add(a);
        list.add(b);
    }

    public List<Company> getAll() {
        return list;
    }

    public String create(Company company) {
        company.Id = getNextId();
        list.add(company);
        return get(company.Id).Id;
    }

    public Company get(String companyId) {
        List<Company> data = list.stream().filter(p -> p.Id.equals(companyId)).collect(Collectors.toList());

        if (data.size() == 1) {
            return data.get(0);
        }
        return null;
    }

    // TODO not done
    private String getNextId() {
        return "5";
    }

}
