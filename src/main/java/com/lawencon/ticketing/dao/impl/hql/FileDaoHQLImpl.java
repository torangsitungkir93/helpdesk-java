package com.lawencon.ticketing.dao.impl.hql;

import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketing.dao.FileDao;
import com.lawencon.ticketing.model.File;


@Repository
@Profile("hql-query")
public class FileDaoHQLImpl implements FileDao {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public File getById(Long fileId){
		final File user = this.em.find(File.class, fileId);
		return user;
	}

	@Override
	public File insert(File file){
		em.persist(file);
		return file;
	}

	@Override
	public boolean deleteById(Long id){
		final String sql = "DELETE FROM File f WHERE f.id = :id";
		final Integer result = em.createNativeQuery(sql).setParameter("id", id).executeUpdate();
		return result > 0;
	}

}
