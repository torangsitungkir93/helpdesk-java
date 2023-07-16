package com.lawencon.ticketing.dao.impl.hql;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.lawencon.ticketing.dao.UserDao;
import com.lawencon.ticketing.model.Profile;
import com.lawencon.ticketing.model.Role;
import com.lawencon.ticketing.model.User;


@Repository
@org.springframework.context.annotation.Profile("hql-query")
public class UserDaoHQLImpl implements UserDao {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<User> getAll(){
		final String sql = "SELECT u FROM User AS u";
		final List<User> users = this.em.createQuery(sql, User.class).getResultList();
		return users;
	}

	@Override
	public List<User> getByRoleCode(String roleCode){
		final List<User> users = new ArrayList<>();
		final String sql = "SELECT " 
				+ "u.id, u.role.codeRole,u.profile.fullName " 
				+ "FROM " 
				+ "User u " 
				+ "WHERE u.role.codeRole = :codeRole ";
		
		final List<?> userObjs = this.em.createQuery(sql)
				.setParameter("codeRole", roleCode)
				.getResultList();
			
			if(userObjs.size() > 0) {
				for(Object userObj : userObjs) {
					final Object[] userArr = (Object[]) userObj;
					final User user = new User();
					user.setId(Long.valueOf(userArr[0].toString()));
								
					final Role role = new Role();
					role.setCodeRole(userArr[1].toString());
					user.setRole(role);
					
					final Profile profile = new Profile();
					profile.setFullName(userArr[2].toString());
					user.setProfile(profile);
					users.add(user);
				}
			}
		return users;
	}					

	@Override
	public User getByUserNameAndPassword(String email, String passwordUser){
		final String sql = "SELECT u.id,u.profile.fullName,u.role.codeRole "
				+ "FROM User u "
				+ "WHERE u.userEmail = :email AND u.passwordUser =:password";
		
		try {
			final Object userObj = this.em.createQuery(sql)
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

		}catch(Exception e){
				e.printStackTrace();
				return null;
			}
	}
	

	@Override
	public User insert(User user){
		em.persist(user);
		return user;
	}

	@Override
	public User getById(Long userId){
		final User user = this.em.find(User.class, userId);
		return user;
	}


	@Override
	public Profile update(Profile profile){
		Profile profileResult = this.em.merge(profile);
		this.em.flush();
		return profileResult;
	}
}
