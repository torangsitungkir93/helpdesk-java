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
import com.lawencon.ticketing.dao.ProfileDao;
import com.lawencon.ticketing.dao.RoleDao;
import com.lawencon.ticketing.dao.UserDao;
import com.lawencon.ticketing.model.Company;
import com.lawencon.ticketing.model.File;
import com.lawencon.ticketing.model.Profile;
import com.lawencon.ticketing.model.Role;
import com.lawencon.ticketing.model.User;
import com.lawencon.ticketing.service.UserService;

public class UserServiceImpl implements UserService {
	private final UserDao userDao;
	private final ProfileDao profileDao;
	private final FileDao fileDao;
	private final RoleDao roleDao;
	private final CompanyDao companyDao;
	private final EntityManager em;

	public UserServiceImpl(UserDao userDao, ProfileDao profileDao, FileDao fileDao,RoleDao roleDao,
			CompanyDao companyDao,
			DataSource dataSource,
			SessionFactory factory) throws SQLException {
		this.companyDao = companyDao;
		this.em = EntityManagerConfig.get(factory);
		this.profileDao = profileDao;
		this.fileDao = fileDao;
		this.userDao = userDao;
		this.roleDao = roleDao;
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
			final LocalDateTime dateNow = LocalDateTime.now();
			file.setCreatedBy(user.getCreatedBy());
			file.setCreatedAt(dateNow);
			file.setIsActive(true);
			file.setVer(1);
			final File fileResult = fileDao.insert(file);
			profile.setProfilePhoto(fileResult);
			profile.setCreatedBy(user.getCreatedBy());
			profile.setCreatedAt(dateNow);
			profile.setIsActive(true);
			profile.setVer(1);
			final Profile profileResult = profileDao.insert(profile);
			final Role roleResult = roleDao.getById(user.getRole().getId());
			final Company company = companyDao.getById(user.getCompany().getId());
			user.setRole(roleResult);
			user.setCompany(company);
			user.setProfile(profileResult);
			user.setCreatedBy(user.getCreatedBy());
			user.setCreatedAt(dateNow);
			user.setIsActive(true);
			user.setVer(1);
			userResult = userDao.insert(user);
			this.em.getTransaction().commit();
		}catch(Exception e) {
			e.printStackTrace();
			this.em.getTransaction().rollback();
		}
		return userResult;
	}

	@Override
	public Profile changePhoto(Long userId,Long profileId, File file) throws SQLException {
		Profile profile = null;
		try {
			this.em.getTransaction().begin();
			profile = profileDao.getById(profileId);
			final Long fileId = profile.getProfilePhoto().getId();
			final LocalDateTime dateNow = LocalDateTime.now();
			file.setCreatedBy(userId);
			file.setCreatedAt(dateNow);
			file.setIsActive(true);
			file.setVer(1);
			final File fileResult = fileDao.insert(file);
			profile.setProfilePhoto(fileResult);
			profileDao.update(profile);
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
		return profile;
	}

	@Override
	public User login(String email, String userPassword) throws SQLException {
		final User user = userDao.getByUserNameAndPassword(email, userPassword);
		return user;
	}

	@Override
	public User getUserById(Long id) throws SQLException {
		final User user = userDao.getById(id);
		return user;
	}
}
