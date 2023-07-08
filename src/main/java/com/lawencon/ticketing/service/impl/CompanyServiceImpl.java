package com.lawencon.ticketing.service.impl;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;

import com.lawencon.ticketing.config.EntityManagerConfig;
import com.lawencon.ticketing.dao.CompanyDao;
import com.lawencon.ticketing.dao.FileDao;
import com.lawencon.ticketing.model.Company;
import com.lawencon.ticketing.model.File;
import com.lawencon.ticketing.service.CompanyService;

public class CompanyServiceImpl implements CompanyService{
	
	private CompanyDao companyDao;
	private FileDao fileDao;
	private final EntityManager em;

	public CompanyServiceImpl(CompanyDao companyDao,FileDao fileDao,DataSource dataSource,SessionFactory factory)throws SQLException {
		this.companyDao = companyDao;
		this.fileDao = fileDao;
		this.em = EntityManagerConfig.get(factory);
	}

	@Override
	public List<Company> getAllCompany() throws SQLException {
		final List<Company> companies = companyDao.getAll();
		return companies;
	}

	@Override
	public Company insertCompany(String nameCompany, String companyAddress, String companyCode, File file,Long createdBy) {
		Company company = new Company();
		try {
			this.em.getTransaction().begin();
			final LocalDateTime dateNow = LocalDateTime.now();
			file.setCreatedBy(createdBy);
			file.setCreatedAt(dateNow);
			file.setIsActive(true);
			file.setVer(1);
			final File fileResult = fileDao.insert(file);
			company.setPhotoId(fileResult);
			company.setCompanyCode(companyCode);
			company.setNameCompany(nameCompany);
			company.setCompanyAddress(companyAddress);
			company.setCreatedBy(createdBy);

			final LocalDateTime timeNow = LocalDateTime.now();
			company.setCreatedAt(timeNow);
			company.setIsActive(true);
			company.setVer(0);

			companyDao.insert(company);
			this.em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				this.em.getTransaction().rollback();
			}catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return company;
	}

	@Override
	public Company updateCompany(Long companyId, String name, String address, File file,Long createdBy) throws SQLException {
		Company company = null;
		try {
			this.em.getTransaction().begin();
			company = companyDao.getById(companyId);
			company.setNameCompany(name);
			company.setCompanyAddress(address);
			final Long fileId = company.getPhotoId().getId();
			final LocalDateTime dateNow = LocalDateTime.now();
			file.setCreatedBy(createdBy);
			file.setCreatedAt(dateNow);
			file.setIsActive(true);
			file.setVer(1);
			
			final File fileResult = fileDao.insert(file);
			company.setPhotoId(fileResult);
			companyDao.update(company);
			fileDao.deleteById(fileId);
			this.em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				this.em.getTransaction().rollback();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return company;
	}
	
	

}
