package service;

import dto.CompanyDto;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CompanyServiceDummy {

    // TODO this is dummy
    private static final List<CompanyDto> list = new ArrayList();

    public CompanyServiceDummy() {
        CompanyDto a = new CompanyDto();
        CompanyDto b = new CompanyDto();
        a.Id = "1";
        b.Id = "2";
        a.Name = "barbosa";
        b.Name = "vidigal";
        a.Adress = "aveiro";
        b.Adress = "viseu";
        list.add(a);
        list.add(b);
    }

    public List<CompanyDto> getAll() {
        return list;
    }

    public String create(CompanyDto company) {
        company.Id = getNextId();
        list.add(company);
        return get(company.Id).Id;
    }

    public CompanyDto get(String companyId) {
        List<CompanyDto> data = list.stream().filter(p -> p.Id.equals(companyId)).collect(Collectors.toList());

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
