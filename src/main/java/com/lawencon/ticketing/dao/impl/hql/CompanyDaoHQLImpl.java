package com.lawencon.ticketing.dao.impl.hql;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.SessionFactory;

import com.lawencon.ticketing.config.EntityManagerConfig;
import com.lawencon.ticketing.dao.CompanyDao;
import com.lawencon.ticketing.model.Company;

public class CompanyDaoHQLImpl implements CompanyDao{

	private final EntityManager em;
	
	public CompanyDaoHQLImpl(SessionFactory sessionFactory) throws SQLException {
		this.em = EntityManagerConfig.get(sessionFactory);
	}
	
	@Override
	public Company getById(Long id) throws SQLException {
		final Company company = this.em.find(Company.class, id);
		return company;
	}

	@Override
	public List<Company> getAll() throws SQLException {
		final String sql = "SELECT c "
				+ "FROM Company c "
				+ "WHERE c.companyCode <> 'GHAJ2'";
		
		final List<Company> companies = this.em.createQuery(sql,Company.class).getResultList();
		return companies;
	}

	@Override
	public Company insert(Company company) throws SQLException {
		em.persist(company);
		return company;
	}

	@Override
	public Company update(Company company) throws SQLException {
		Company companyResult = this.em.merge(company);
		this.em.flush();
		return companyResult;
	}

}
