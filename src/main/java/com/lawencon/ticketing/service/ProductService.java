package com.lawencon.ticketing.service;

import java.util.List;

import com.lawencon.ticketing.model.Product;

public interface ProductService {
	List<Product> getAll() throws Exception;
	Product insertProduct(String productCode,String productName,Long createdById) ;
	Product updateProduct(Long idProduct,String productName,Long createdBy);
}
