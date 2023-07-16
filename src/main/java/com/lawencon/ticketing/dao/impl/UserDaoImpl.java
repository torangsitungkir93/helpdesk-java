package com.lawencon.ticketing.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.lawencon.ticketing.dao.UserDao;
import com.lawencon.ticketing.model.Profile;
import com.lawencon.ticketing.model.Role;
import com.lawencon.ticketing.model.User;


@Repository
@org.springframework.context.annotation.Profile("native")
public class UserDaoImpl implements UserDao {
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAll() {
		final String sql = "SELECT " + "* " + "FROM t_users";
		final List<User> users = this.em.createNativeQuery(sql, User.class).getResultList();
		return users;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getByRoleCode(String roleCode) {
		final String sql = "SELECT " 
				+ "	* " 
				+ "FROM " 
				+ "	t_users u " 
				+ "INNER JOIN t_role r ON "
				+ "	r.id = u.role_id " 
				+ "INNER JOIN t_profile tp ON " 
				+ "	tp.id = u.profile_id "
				+ "WHERE r.code_role = :roleCode";
		final List<User> users = this.em.createNativeQuery(sql,User.class).setParameter("roleCode", roleCode).getResultList();
		return users;
	}

	@Override
	public User getByUserNameAndPassword(String email, String passwordUser) {
		final String sql = "SELECT u.id as user_id,tp.full_name,r.code_role "
				+ "FROM t_users u "
				+ "INNER JOIN t_role r ON r.id = u.role_id INNER JOIN t_profile tp ON u.profile_id= tp.id "
				+ "WHERE u.email = :email AND u.password_user =:password";
		
		final Object userObj = this.em.createNativeQuery(sql)
				.setParameter("email", email)
				.setParameter("password", passwordUser)
				.getSingleResult();

		final Object[] userArr = (Object[]) userObj;

		User user = null;

		if (userArr.length > 0) {
			user = new User();
			user.setId(Long.valueOf(userArr[0].toString()));

			final Profile profile = new Profile();
			profile.setFullName(userArr[1].toString());
			user.setProfile(profile);

			final Role role = new Role();
			role.setCodeRole(userArr[2].toString());
			user.setRole(role);
		}
		return user;
	}

	@Override
	public User insert(User user) {
		em.persist(user);
		return user;
	}

	@Override
	public User getById(Long userId) {
		final User user = this.em.find(User.class, userId);
		return user;
	}

	@Override
	public Profile update(Profile profile) {
		Profile profileResult = this.em.merge(profile);
		this.em.flush();
		return profileResult;
	}
}
