package service;

import dto.CompanyDto;
import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import persistence.HerokuPostgress;
import persistence.IPersistence;
import persistence.dao.CompanyDao;
import persistence.entity.CompanyEntity;

public class CompanyService {

    private final CompanyDao dao;

    private final IPersistence persistence;

    public CompanyService() {
        persistence = new HerokuPostgress();
        EntityManager em = persistence.createEntityManager();
        dao = new CompanyDao(em);
    }

    public List<CompanyDto> getAll() {
        List<CompanyEntity> entities = dao.readAll();

        ModelMapper mapper = new ModelMapper();
        Type targetListType = new TypeToken<List<CompanyDto>>() { }.getType();
        List<CompanyDto> dtos = mapper.map(entities, targetListType);

        return dtos;
    }

    public CompanyDto get(String companyId) {

        return null;
    }

}
