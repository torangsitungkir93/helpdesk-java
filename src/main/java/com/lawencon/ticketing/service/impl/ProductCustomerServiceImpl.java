package com.lawencon.ticketing.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.lawencon.ticketing.dao.ProductCustomerDao;
import com.lawencon.ticketing.dao.ProductDao;
import com.lawencon.ticketing.dao.UserDao;
import com.lawencon.ticketing.dto.InsertResDto;
import com.lawencon.ticketing.dto.productcustomer.ProductCustReqDto;
import com.lawencon.ticketing.dto.productcustomer.ProductCustResDto;
import com.lawencon.ticketing.model.Product;
import com.lawencon.ticketing.model.ProductCustomer;
import com.lawencon.ticketing.model.User;
import com.lawencon.ticketing.service.ProductCustomerService;

@Service
public class ProductCustomerServiceImpl implements ProductCustomerService {
	private final static Long USER_ID = (long) 1;
	private ProductCustomerDao productCustomerDao;
	private UserDao userDao;
	private ProductDao productDao;

	private ProductCustomer productCustomer = null;
	private User user = null;
	private Product product = null;
	@PersistenceContext
	private EntityManager em;

	public ProductCustomerServiceImpl(ProductCustomerDao productCustomerDao,UserDao userDao,ProductDao productDao){
		this.productCustomerDao = productCustomerDao;
		this.userDao = userDao;
		this.productDao = productDao;
	}

	@Transactional
	@Override
	public InsertResDto insert(ProductCustReqDto dto) {
		InsertResDto response = null;
		ProductCustomer productResult = null;
		
		user = userDao.getById(dto.getCustomerId());
		product = productDao.getById(dto.getProductId());

		productCustomer = new ProductCustomer();
		productCustomer.setCustomer(user);
		productCustomer.setProduct(product);
		productCustomer.setCreatedBy(USER_ID);

		final LocalDateTime timeNow = LocalDateTime.now();
		productCustomer.setCreatedAt(timeNow);

		productResult=productCustomerDao.insert(productCustomer);
		response = new InsertResDto();
		response.setId(productResult.getId());
		response.setMessage("Berhasil Input User");
		
		return response;
	}

	@Override
	public List<ProductCustResDto> getAll(Long idCust) {
		
		List<ProductCustomer> product = null;
		if(idCust==null) {
			product=productCustomerDao.getAll();
		}else {
			product=productCustomerDao.getAllByIdCust(idCust);
		}
		
		final List<ProductCustResDto> productResDtos = new ArrayList<> ();
		product.forEach(u->{
		final ProductCustResDto productCustResDto = new ProductCustResDto () ;
		productCustResDto.setProductName(u.getProduct().getProductName()); 
		productResDtos.add(productCustResDto);
		});
		return productResDtos;
	}

}
