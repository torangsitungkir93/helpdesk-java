package com.lawencon.ticketing.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;

import com.lawencon.ticketing.config.EntityManagerConfig;
import com.lawencon.ticketing.dao.FileDao;
import com.lawencon.ticketing.model.File;

public class FileDaoImpl implements FileDao {
	private final EntityManager em;

	public FileDaoImpl(SessionFactory sessionFactory) throws SQLException {
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
		final String sql = "DELETE FROM t_file WHERE id = :id";
		final Integer result = em.createNativeQuery(sql).setParameter("id", id).executeUpdate();
		return result > 0;
	}

}
