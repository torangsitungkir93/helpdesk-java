package com.lawencon.ticketing.dao;

import java.sql.SQLException;
import com.lawencon.ticketing.model.Profile;

public interface ProfileDao {
	Profile getById(Long id) ;
	Profile insert(Profile profile) ;
	Profile update(Profile profile) ;
}
