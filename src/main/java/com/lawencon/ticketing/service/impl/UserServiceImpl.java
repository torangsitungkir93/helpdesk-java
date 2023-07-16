package com.lawencon.ticketing.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import com.lawencon.ticketing.dao.CompanyDao;
import com.lawencon.ticketing.dao.FileDao;
import com.lawencon.ticketing.dao.ProfileDao;
import com.lawencon.ticketing.dao.RoleDao;
import com.lawencon.ticketing.dao.UserDao;
import com.lawencon.ticketing.dto.InsertResDto;
import com.lawencon.ticketing.dto.user.UserInsertReqDto;
import com.lawencon.ticketing.dto.user.UserResDto;
import com.lawencon.ticketing.dto.user.UserUpdatePhotoReqDto;
import com.lawencon.ticketing.dto.user.UserUpdatePhotoResDto;
import com.lawencon.ticketing.model.Company;
import com.lawencon.ticketing.model.File;
import com.lawencon.ticketing.model.Profile;
import com.lawencon.ticketing.model.Role;
import com.lawencon.ticketing.model.User;
import com.lawencon.ticketing.service.UserService;


@Service
public class UserServiceImpl implements UserService {
	
	private final static Long USER_ID =(long) 1;
	
	private final UserDao userDao;
	private final ProfileDao profileDao;
	private final FileDao fileDao;
	private final RoleDao roleDao;
	private final CompanyDao companyDao;
	@PersistenceContext
	private EntityManager em;

	public UserServiceImpl(UserDao userDao, ProfileDao profileDao, FileDao fileDao,RoleDao roleDao,
			CompanyDao companyDao,
			DataSource dataSource,
			SessionFactory factory){
		this.companyDao = companyDao;
		this.profileDao = profileDao;
		this.fileDao = fileDao;
		this.userDao = userDao;
		this.roleDao = roleDao;
	}

	@Override
	public List<UserResDto> getAll(String roleCode){
		List<User> user = null;
		if(roleCode==null) {
			user=userDao.getAll();
		}else {
			user=userDao.getByRoleCode(roleCode);
		}
		
		final List<UserResDto> userResDtos = new ArrayList<> ();
		user.forEach(u->{
		final UserResDto resultUserResDto = new UserResDto () ;
		resultUserResDto.setRoleCode(u.getRole().getCodeRole()); 
		resultUserResDto.setRoleName(u.getRole().getNameRole());
		resultUserResDto.setProfileName(u.getProfile().getFullName());
		userResDtos.add(resultUserResDto);
		});
		return userResDtos;
	}

	@Transactional
	@Override
	public InsertResDto insert(UserInsertReqDto data){
		User userResult = null;
		InsertResDto response = null;
		
			final LocalDateTime dateNow = LocalDateTime.now();
			final User user = new User();
			user.setUserEmail(data.getUserEmail());
			user.setPasswordUser(data.getUserPassword());
			
			final File file = new File();
			
			file.setExt(data.getExt());
			file.setFiles(data.getFile());
			file.setCreatedBy(USER_ID);
			final File fileResult = fileDao.insert(file);
			
			final Profile profile = new Profile();
			profile.setFullName(data.getUserName());
			profile.setPhone(data.getUserPhone());
			profile.setProfilePhoto(fileResult);
			profile.setCreatedBy(USER_ID);
			profile.setCreatedAt(dateNow);
			
			final Profile profileResult = profileDao.insert(profile);
			final Role roleResult = roleDao.getById(data.getRoleId());
			final Company company = companyDao.getById(data.getCompanyId());
			user.setRole(roleResult);
			user.setCompany(company);
			user.setProfile(profileResult);
			user.setCreatedBy(USER_ID);
			user.setCreatedAt(dateNow);
			
			userResult = userDao.insert(user);
			response = new InsertResDto();
			response.setId(userResult.getId());
			response.setMessage("Berhasil Input User");
			
		return response;
	}

	@Override
	public UserUpdatePhotoResDto changePhoto(UserUpdatePhotoReqDto dto){
		Profile profile = null;
		File file = null;
		try {
			this.em.getTransaction().begin();
			profile = profileDao.getById(dto.getProfileId());
			final Long fileId = profile.getProfilePhoto().getId();
			final LocalDateTime dateNow = LocalDateTime.now();
			file = new File(); 
			
			file.setFiles(dto.getFile());
			file.setExt(dto.getExt());
			file.setCreatedBy(dto.getProfileId());
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
		return null;
	}

	@Override
	public User login(String email, String userPassword){
		final User user = userDao.getByUserNameAndPassword(email, userPassword);
		return user;
	}

	@Override
	public User getUserById(Long id){
		final User user = userDao.getById(id);
		return user;
		
	}
}
