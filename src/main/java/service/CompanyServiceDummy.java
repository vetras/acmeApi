package service;

import dto.CompanyDto;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CompanyServiceDummy implements ICompany {

    private static List<CompanyDto> list = null;
    
    private static Integer lastId = 2;

    public CompanyServiceDummy() {
        init();
    }

    private void init() {
        if (list != null) {
            return;
        }
        
        list = new ArrayList();
                
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

    @Override
    public List<CompanyDto> getAll() {
        return list;
    }

    @Override
    public String create(CompanyDto company) {
        company.Id = getNextId();
        list.add(company);
        return get(company.Id).Id;
    }

    @Override
    public CompanyDto get(String companyId) {
        List<CompanyDto> data = list.stream().filter(p -> p.Id.equals(companyId)).collect(Collectors.toList());

        if (data.size() == 1) {
            return data.get(0);
        }
        return null;
    }

    private static String getNextId() {
        lastId++;
        return lastId.toString();
    }

}
