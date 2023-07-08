package com.lawencon.ticketing.dao.impl.hql;

import java.sql.SQLException;

import javax.persistence.EntityManager;

import org.hibernate.SessionFactory;

import com.lawencon.ticketing.config.EntityManagerConfig;
import com.lawencon.ticketing.dao.ProfileDao;
import com.lawencon.ticketing.model.Profile;

public class ProfileDaoHQLImpl implements ProfileDao{
	private final EntityManager em;
	
	public ProfileDaoHQLImpl(SessionFactory sessionFactory) throws SQLException {
		this.em = EntityManagerConfig.get(sessionFactory);
	}
	
	@Override
	public Profile getById(Long profileId) throws SQLException {
		final Profile profile = this.em.find(Profile.class, profileId);
		return profile;
	}

	@Override
	public Profile insert(Profile profile) throws SQLException {
		em.persist(profile);
		return profile;
	}

	@Override
	public Profile update(Profile profile) throws SQLException {
		return profile;
	}
}
