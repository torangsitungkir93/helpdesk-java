package com.lawencon.ticketing.dao.impl;

import java.sql.SQLException;

import javax.persistence.EntityManager;

import org.hibernate.SessionFactory;

import com.lawencon.ticketing.config.EntityManagerConfig;
import com.lawencon.ticketing.dao.ProfileDao;
import com.lawencon.ticketing.model.Profile;

public class ProfileDaoImpl implements ProfileDao{
	private final EntityManager em;
	
	public ProfileDaoImpl(SessionFactory sessionFactory) throws SQLException {
		this.em = EntityManagerConfig.get(sessionFactory);
	}
	
	@Override
	public Profile getById(Long profileId) throws SQLException {
		final Profile profile = this.em.find(Profile.class, profileId);
		return profile;
	}

	@Override
	public Profile insert(Profile profile) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Profile update(Profile profile) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}