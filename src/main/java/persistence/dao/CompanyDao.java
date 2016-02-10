package persistence.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import persistence.entity.CompanyEntity;


public class CompanyDao {

	private final EntityManager em;

        public CompanyDao(EntityManager em) {
		this.em = em;
	}

        public CompanyEntity findById(int Id) {
		return em.find(CompanyEntity.class, Id);
	}

        public List<CompanyEntity> readAll() {
		return readAll(0, 0);
	}

	/**
	 * Query the companies and gets the results between first result and
	 * (firstresult+maxResults)
	 * 
	 * @param maxResults
	 *            max number of results to return
	 * @param firstResult
	 *            index of first result to show
	 * @return the list of companies starting in firstResult, the max number of companies
	 *         returned is maxResults
	 */
	public List<CompanyEntity> readAll(int maxResults, int firstResult) {
		TypedQuery<CompanyEntity> query = em.createNamedQuery("findAll", CompanyEntity.class);
		if (maxResults > 0) {
			query.setMaxResults(maxResults);
			query.setFirstResult(firstResult);
		}
		List<CompanyEntity> companies = query.getResultList();
		return companies;
	}

	public void create(CompanyEntity data) {
		em.persist(data);
	}

	public void delete(int Id) {
		CompanyEntity data = em.find(CompanyEntity.class, Id);
		em.remove(data);
	}

	public void update(CompanyEntity newData) {
		em.merge(newData);
	}
	
}
