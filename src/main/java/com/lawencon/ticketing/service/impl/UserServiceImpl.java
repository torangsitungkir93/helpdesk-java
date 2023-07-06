package com.lawencon.ticketing.service.impl;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;

import com.lawencon.ticketing.config.EntityManagerConfig;
import com.lawencon.ticketing.dao.FileDao;
import com.lawencon.ticketing.dao.ProfileDao;
import com.lawencon.ticketing.dao.UserDao;
import com.lawencon.ticketing.model.File;
import com.lawencon.ticketing.model.Profile;
import com.lawencon.ticketing.model.User;
import com.lawencon.ticketing.service.UserService;

public class UserServiceImpl implements UserService {
	private final UserDao userDao;
	private final ProfileDao profileDao;
	private final FileDao fileDao;
	private final EntityManager em;

	public UserServiceImpl(UserDao userDao, ProfileDao profileDao, FileDao fileDao,DataSource dataSource,
			SessionFactory factory) throws SQLException {
		this.em = EntityManagerConfig.get(factory);
		this.profileDao = profileDao;
		this.fileDao = fileDao;
		this.userDao = userDao;
	}

	@Override
	public List<User> getAll() throws SQLException {
		final List<User> users = userDao.getAll();
		return users;
	}

	@Override
	public List<User> getByRoleCode(String roleCode) throws SQLException {
		final List<User> users = userDao.getByRoleCode(roleCode);
		return users;
	}

	@Override
	public User insert(User user, Profile profile, File file) throws SQLException {
		User userResult = null;
		try {
			this.em.getTransaction().begin();
			if(profile.getProfilePhoto() != null) {
				final File fileResult = fileDao.insert(file);
				profile.setProfilePhoto(fileResult);
			}
			
			final Profile profileResult = profileDao.insert(profile);
			user.setProfile(profileResult);
			userResult = userDao.insert(user);
			this.em.getTransaction().commit();
		}catch (Exception e){
			e.printStackTrace();
			this.em.getTransaction().rollback();
		}
		return userResult;
	}

	@Override
	public Profile changePhoto(Long profileId, File file) throws SQLException {
		Profile profileResult = null;
		try {
			this.em.getTransaction().begin();
			final Profile profile = profileDao.getById(profileId);
			final File fileResult = fileDao.insert(file);
			profile.setProfilePhoto(fileResult);
			profileResult = profileDao.update(profile);
			fileDao.deleteById(profile.getProfilePhoto().getId());
			this.em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				this.em.getTransaction().rollback();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return profileResult;
	}

	@Override
	public User login(String email, String userPassword) throws SQLException {
		final User user = userDao.getByUserNameAndPassword(email, userPassword);
		return user;
	}

	@Override
	public User update(User user, Profile profile) throws SQLException {
		User userResult = null;
		try {
			this.em.getTransaction().begin();
			final Profile profileResult = profileDao.update(profile);
			user.setProfile(profileResult);
			this.em.getTransaction().commit();
		}catch (Exception e){
			e.printStackTrace();
			this.em.getTransaction().rollback();
		}
		return userResult;
	}
}
