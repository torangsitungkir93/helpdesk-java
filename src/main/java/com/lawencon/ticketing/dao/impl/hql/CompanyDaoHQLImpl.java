package com.lawencon.ticketing.dao.impl.hql;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketing.dao.CompanyDao;
import com.lawencon.ticketing.model.Company;


@Repository
@Profile("hql-query")
public class CompanyDaoHQLImpl implements CompanyDao{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Company getById(Long id){
		final Company company = this.em.find(Company.class, id);
		return company;
	}

	@Override
	public List<Company> getAll(){
		final String sql = "SELECT c "
				+ "FROM Company c "
				+ "WHERE c.companyCode <> 'GHAJ2'";
		
		final List<Company> companies = this.em.createQuery(sql,Company.class).getResultList();
		return companies;
	}

	@Override
	public Company insert(Company company){
		em.persist(company);
		return company;
	}

	@Override
	public Company update(Company company){
		Company companyResult = this.em.merge(company);
		this.em.flush();
		return companyResult;
	}

}
