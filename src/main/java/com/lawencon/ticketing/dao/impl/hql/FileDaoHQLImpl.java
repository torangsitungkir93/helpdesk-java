package com.lawencon.ticketing.dao.impl.hql;

import java.sql.SQLException;

import javax.persistence.EntityManager;

import org.hibernate.SessionFactory;

import com.lawencon.ticketing.config.EntityManagerConfig;
import com.lawencon.ticketing.dao.FileDao;
import com.lawencon.ticketing.model.File;

public class FileDaoHQLImpl implements FileDao {
	private final EntityManager em;

	public FileDaoHQLImpl(SessionFactory sessionFactory) throws SQLException {
		this.em = EntityManagerConfig.get(sessionFactory);
	}

	@Override
	public File getById(Long fileId) throws SQLException {
		final File user = this.em.find(File.class, fileId);
		return user;
	}

	@Override
	public File insert(File file) throws SQLException {
		em.persist(file);
		return file;
	}

	@Override
	public boolean deleteById(Long id) throws SQLException {
		final String sql = "DELETE FROM File WHERE id = :id";
		final int result = em.createQuery(sql)
				.setParameter("id", id)
				.executeUpdate();
		return result > 0;
	}

}
