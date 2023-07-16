package com.lawencon.ticketing.dao.impl.hql;

import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.lawencon.ticketing.dao.ProfileDao;
import com.lawencon.ticketing.model.Profile;


@Repository
@org.springframework.context.annotation.Profile("hql-query")
public class ProfileDaoHQLImpl implements ProfileDao{
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Profile getById(Long profileId){
		final Profile profile = this.em.find(Profile.class, profileId);
		return profile;
	}

	@Override
	public Profile insert(Profile profile){
		em.persist(profile);
		return profile;
	}

	@Override
	public Profile update(Profile profile){
		return profile;
	}
}
