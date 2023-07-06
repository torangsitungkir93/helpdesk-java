package com.lawencon.ticketing.dao;

import java.sql.SQLException;
import com.lawencon.ticketing.model.Profile;

public interface ProfileDao {
	Profile getById(Long id) throws SQLException;
	Profile insert(Profile profile) throws SQLException;
	Profile update(Profile profile) throws SQLException;
}
