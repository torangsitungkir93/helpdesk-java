package com.lawencon.ticketing.config;

import javax.persistence.EntityManager;

import org.hibernate.SessionFactory;

public class EntityManagerConfig {
	
	private static final ThreadLocal<EntityManager>
		ENTITY_MANAGER = new ThreadLocal<>();
	
	public static EntityManager get(SessionFactory factory) {
		final EntityManager em = ENTITY_MANAGER.get();
		if(em!=null) {
			return em;
		}
		final EntityManager emNew = factory.createEntityManager();
		ENTITY_MANAGER.set(emNew);
		return emNew;
	}
}
