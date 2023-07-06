package com.lawencon.ticketing.view;

import java.util.List;

import com.lawencon.ticketing.util.ScannerUtil;
import com.lawencon.ticketing.constant.RoleConst;
import com.lawencon.ticketing.model.Product;
import com.lawencon.ticketing.model.User;
import com.lawencon.ticketing.service.PicCustomerService;
import com.lawencon.ticketing.service.ProductCustomerService;
import com.lawencon.ticketing.service.ProductService;
import com.lawencon.ticketing.service.UserService;

public class SuperAdminView {
	private final ProductService productService;
	private final UserService userService;
	private final ProductCustomerService productCustomerService;
	private final PicCustomerService picCustomerService;
	
	private Long currentIdUser = (long) 0;
	private String currentNameUser; 
	
	public SuperAdminView(ProductService productService, UserService userService,
			ProductCustomerService productCustomerService, PicCustomerService picCustomerService) {
		this.productService = productService;
		this.userService = userService;
		this.productCustomerService = productCustomerService;
		this.picCustomerService = picCustomerService;
	}

	public void show() throws Exception {
		System.out.println("-----------------------------------");
		System.out.println("--- Halooo "+currentNameUser);
		System.out.println("-----------------------------------");
		System.out.println("Menu untuk Super Admin");
		System.out.println("1. Product Customer");
		System.out.println("2. PIC Customer");
		System.out.println("3. Tambah Master Data");
		System.out.println("4. Keluar");
		System.out.println("===================================");
		
		final int option = (int) ScannerUtil.getScannerNumber("Pilih Menu: ", 4);
		if (option == 1) {
			showProductCust();
		} else if (option == 2) {
			showPicCustomer();
		} else if (option == 3) {
			System.out.println("Terimakasihhh .... ");
		}else if (option == 4) {
			System.out.println("Terimakasihhh .... ");
		} else {
			System.out.println("Pilihan tidak ada,input ulang");
		}
	}
	
	private void showProductCust() throws Exception {

		System.out.println("Menu Product Customer");
		System.out.println("1. Tampilkan Semua Product");
		System.out.println("2. Assign Product Customer");
		System.out.println("3. Keluar");
		System.out.println("===================================");
		String roleCode="";
		
		final int option = (int) ScannerUtil.getScannerNumber("Pilih Menu: ", 3);
		if (option == 1) {
			final List<Product> products = productService.getAll();
			int counter = 1;
			System.out.println("+===============================+");
			System.out.println("+==   Tampilan Semua Product  ==+");
			System.out.println("+===============================+");
			products.forEach(p -> {
				System.out.println(counter+". "+p.getProductName());
			});
		} else if (option == 2) {
			final List<Product> products = productService.getAll();
			System.out.println("=================================");
			System.out.println("===   Tampilan Semua Product  ===");
			System.out.println("=================================");
			int counter = 0;
			for (Product p : products) {
			    System.out.println(counter+1+ ". " + p.getProductName());
			    ++counter;
			}
			final int productChoice = (int) ScannerUtil.getScannerNumber("Pilih Product : ", counter);
			
			roleCode = RoleConst.CUST.getRoleCode();
			final List<User> customers = userService.getByRoleCode(roleCode);
			System.out.println("=================================");
			System.out.println("======  Tampilan Customer  ======");
			System.out.println("=================================");
			int counterC=0;
			for (User u : customers) {
			    System.out.println(counterC+1+ ". " + u.getProfile().getFullName());
			    ++counterC;
			}
			final int customerChoice = (int) ScannerUtil.getScannerNumber("Pilih Customer : ", counterC);
			
			productCustomerService.insert(getIdCustomer(customerChoice),getIdProduct(productChoice), currentIdUser);
		} else if (option == 3) {
			System.out.println("Terimakasihhh .... ");
		} else {
			System.out.println("Pilihan tidak ada,input ulang");
			show();
		}
	}
	
	private void showPicCustomer() throws Exception {
		System.out.println("Halooo "+currentNameUser);
		System.out.println("-- Menu Product Customer");
		System.out.println("1. Tampilkan Semua Customer yg belum memiliki PIC");
		System.out.println("2. Assign PIC Customer");
		System.out.println("3. Keluar");
		System.out.println("===================================");
		String roleCode="";
		
		final int option = (int) ScannerUtil.getScannerNumber("Pilih Menu: ", 3);
		
		if (option == 1) {
			System.out.println("belum ada");
		} else if (option == 2) {
			roleCode = RoleConst.PIC.getRoleCode();
			final List<User> users = userService.getByRoleCode(roleCode);
			System.out.println("==================================");
			System.out.println("===== Tampilan Semua PIC =====");
			System.out.println("==================================");
			int counterPic = 0;
			for (User u : users) {
			    System.out.println(counterPic+1+ ". " + u.getProfile().getFullName());
			    ++counterPic;
			}
			final int picChoice = (int) ScannerUtil.getScannerNumber("Pilih Product : ", counterPic);
			
			roleCode = RoleConst.CUST.getRoleCode();
			final List<User> customers = userService.getByRoleCode(roleCode);
			System.out.println("=================================");
			System.out.println("======  Tampilan Customer  ======");
			System.out.println("=================================");
			int counterC=0;
			for (User u : customers) {
			    System.out.println(counterC+1+ ". " + u.getProfile().getFullName());
			    ++counterC;
			}
			final int customerChoice = (int) ScannerUtil.getScannerNumber("Pilih Customer : ", counterC);
			
			picCustomerService.insert(getIdPic(picChoice),getIdCustomer(customerChoice),currentIdUser);
		} else if (option == 3) {
			System.out.println("Terimakasihhh .... ");
		} else {
			System.out.println("Pilihan tidak ada,input ulang");
			show();
		}
	}
	
	private Long getIdProduct(int index) throws Exception {
		final List<Product> listProduct = productService.getAll();
		final Long id = listProduct.get(index-1).getId();
		return id;
	}

	private Long getIdCustomer(int index) throws Exception {
		final List<User> listCustomer = userService.getByRoleCode(RoleConst.CUST.getRoleCode());
		final Long id = listCustomer.get(index-1).getId();
		return id;
	}
	
	private Long getIdPic(int index) throws Exception {
		final List<User> listPic = userService.getByRoleCode(RoleConst.PIC.getRoleCode());
		final Long id = listPic.get(index-1).getId();
		return id;
	}
	
	public void setIdCurrentUser(Long id) {
		this.currentIdUser = id;
	}
	
	public void setNameUser(String user) {
		this.currentNameUser = user;
	}
}
