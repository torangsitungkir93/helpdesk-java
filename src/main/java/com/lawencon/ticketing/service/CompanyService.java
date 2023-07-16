package com.lawencon.ticketing.service;

import java.util.List;

import com.lawencon.ticketing.model.Company;
import com.lawencon.ticketing.model.File;

public interface CompanyService {
	List<Company> getAllCompany() ;
	Company insertCompany(String nameCompany,String companyAddress,String companyCode,File file,Long createdBy);
	Company updateCompany(Long companyId,String name,String address, File file,Long createdBy) ;
}
