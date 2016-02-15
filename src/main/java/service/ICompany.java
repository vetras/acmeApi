package service;

import dto.CompanyDto;
import java.util.List;

public interface ICompany {

    String create(CompanyDto company);

    CompanyDto get(String companyId);

    List<CompanyDto> getAll();
    
}
