package com.lawencon.ticketing.view;

import java.sql.SQLException;
import java.util.List;

import com.lawencon.ticketing.constant.RoleConst;
import com.lawencon.ticketing.model.Company;
import com.lawencon.ticketing.model.File;
import com.lawencon.ticketing.model.Product;
import com.lawencon.ticketing.model.Profile;
import com.lawencon.ticketing.model.Role;
import com.lawencon.ticketing.model.User;
import com.lawencon.ticketing.service.CompanyService;
import com.lawencon.ticketing.service.PicCustomerService;
import com.lawencon.ticketing.service.ProductCustomerService;
import com.lawencon.ticketing.service.ProductService;
import com.lawencon.ticketing.service.RoleService;
import com.lawencon.ticketing.service.UserService;
import com.lawencon.ticketing.util.ScannerUtil;

public class SuperAdminView {
	private final ProductService productService;
	private final UserService userService;
	private final ProductCustomerService productCustomerService;
	private final PicCustomerService picCustomerService;
	private final CompanyService companyService;
	private final RoleService roleService;

	private Long currentIdUser = (long) 0;
	private String currentNameUser;
	private boolean isRunning = true;

	public SuperAdminView(ProductService productService, UserService userService,
			ProductCustomerService productCustomerService, PicCustomerService picCustomerService,
			CompanyService companyService, RoleService roleService) {
		this.productService = productService;
		this.userService = userService;
		this.productCustomerService = productCustomerService;
		this.picCustomerService = picCustomerService;
		this.companyService = companyService;
		this.roleService = roleService;
	}

	public void show() throws Exception {
		while (isRunning) {
			System.out.println("-----------------------------------");
			System.out.println("--- Halooo " + currentNameUser);
			System.out.println("-----------------------------------");
			System.out.println("Menu untuk Super Admin");
			System.out.println("1. Product Customer");
			System.out.println("2. PIC Customer");
			System.out.println("3. Tambah Master Data");
			System.out.println("4. Ganti Foto Profil");
			System.out.println("5. Keluar");
			System.out.println("===================================");

			final int option = (int) ScannerUtil.getScannerNumber("Pilih Menu: ", 5);
			if (option == 1) {
				showProductCust();
			} else if (option == 2) {
				showPicCustomer();
			} else if (option == 3) {
				showMenuCreateMaster();
			} else if (option == 4) {
				changeProfilePhoto();
			}else if (option == 5) {
				this.isRunning = false;
				System.out.println("Terimakasihhh .... ");
			} else {
				System.out.println("Pilihan tidak ada,input ulang");
			}
		}
	}

	private void showMenuCreateMaster() throws Exception {
		boolean exitCreateMaster = true;
		while (exitCreateMaster) {
			System.out.println("======== Menu Create Master ========");
			System.out.println("1. Tambah user baru");
			System.out.println("2. Tambah Produk");
			System.out.println("3. Edit Produk");
			System.out.println("4. Tambah Perusahaan");
			System.out.println("5. Edit Perusahaan");
			System.out.println("6. Keluar");

			byte choice = (byte) ScannerUtil.getScannerNumber("Pilih opsi (1-6): ", 6);

			switch (choice) {
			case 1:
				addNewUser();
				break;
			case 2:
				addNewProduct();
				break;
			case 3:
				editProduct();
				break;
			case 4:
				addNewCompany();
				break;
			case 5:
				editCompany();
				break;
			case 6:
				exitCreateMaster = false;
				System.out.println("Keluar dari Menu Create Master");
				break;
			default:
				System.out.println("Opsi tidak valid. Silakan pilih opsi yang benar.");
				break;
			}
		}

	}

	private void editCompany() throws SQLException {
		System.out.println("---- Tampilan Semua Company -----");
		List<Company> companies = companyService.getAllCompany();

		for (int i = 0; i < companies.size(); i++) {
			Company c = companies.get(i);
			System.out.println((i + 1) + ". " +c.getCompanyCode());
			System.out.println("         "+c.getNameCompany()+"  "+c.getCompanyAddress());
			System.out.println("====================================");
		}
		if(companies.size()>0) {
			final int companyChoice = (int) ScannerUtil.getScannerNumber("Pilih Company : ", companies.size());
			Company company = companies.get(companyChoice-1);
			final String newName = ScannerUtil.getScannerString("Nama Baru : ");
			final String newAddress = ScannerUtil.getScannerString("Alamat Baru : ");
			final String newPhoto = ScannerUtil.getScannerString("Foto Baru : ");
			final File file = new File();
			file.setFiles(newPhoto);
			final int lastDot = newPhoto.lastIndexOf(".");
			final String extPhoto = newPhoto.substring(lastDot + 1);
			file.setExt(extPhoto);
			if(companyService.updateCompany(company.getId(), newName, newAddress, file, currentIdUser)!=null) {
				System.out.println("Berhasil Ubah data");
			}else {
				System.out.println("Gagal Ubah Data");
			}
		}else {
			System.out.println("Company belum tersedia");
		}
	}

	private void editProduct() throws Exception {
		System.out.println("---- Tampilan Semua Product -----");
		List<Product> products = productService.getAll();

		for (int i = 0; i < products.size(); i++) {
			Product p = products.get(i);
			System.out.println((i + 1) + ". " + p.getProductCode() + "   " + p.getProductName());
		}
		if(products.size()>0) {
			final int productChoice = (int) ScannerUtil.getScannerNumber("Pilih Product : ", products.size());
			Product product = products.get(productChoice-1);
			System.out.println("Nama Lama : "+product.getProductName());
			final String newName = ScannerUtil.getScannerString("Nama Baru : ");
			if(productService.updateProduct(product.getId(),newName, currentIdUser)!=null) {
				System.out.println("Berhasil Ubah data");
			}else {
				System.out.println("Gagal Ubah Data");
			}
		}
	}

	private void addNewCompany() {
		System.out.println("----- Tambah Company Baru -----");
		final String companyCode = ScannerUtil.getScannerString("Masukkan Kode Company : "); 
		final String companyName = ScannerUtil.getScannerString("Masukkan Nama Company : ");
		final String companyAddress = ScannerUtil.getScannerString("Masukkan Alamat Company : ");
		final String photo = ScannerUtil.getScannerString("Foto Company(gambar.jpg) : ");
		final int lastDot = photo.lastIndexOf(".");
		final String extPhoto = photo.substring(lastDot + 1);

		final File file = new File();
		file.setFiles(photo);
		file.setExt(extPhoto);
		
		if(companyService.insertCompany(companyName,companyAddress,companyCode.toUpperCase(),file, currentIdUser)!=null) {
			System.out.println("Tambah Data Berhasil");
		}else {
			System.out.println("Tambah data gagal");
		}
	}

	private void addNewProduct() throws SQLException {
		System.out.println("----- Tambah Produk Baru -----");
		final String productCode = ScannerUtil.getScannerString("Masukkan Kode Produk : "); 
		final String productName = ScannerUtil.getScannerString("Masukkan Nama Produk : ");
		
		if(productService.insertProduct(productCode.toUpperCase(), productName, currentIdUser)!=null) {
			System.out.println("Tambah Data Berhasil");
		}else {
			System.out.println("Tambah data gagal");
		}
	}

	private void changeProfilePhoto() throws SQLException {
			System.out.println("======== Edit Foto Profil ========");
			User user = userService.getUserById(currentIdUser);
			
			final String newPhoto = ScannerUtil.getScannerString("Masukkan foto baru : ");
			final Long idProfil = user.getProfile().getId();
			final File file = new File();
			file.setFiles(newPhoto);
			final int lastDot = newPhoto.lastIndexOf(".");
			final String extPhoto = newPhoto.substring(lastDot + 1);
			file.setExt(extPhoto);
			if(userService.changePhoto(currentIdUser, idProfil, file)!=null) {
				System.out.println("Update Photo Berhasil");
			}else {
				System.out.println("Gagal Ganti foto Profil");
			}
	}

	private void addNewUser() throws SQLException {
		boolean exitAddUser = true;

		while (exitAddUser) {
			System.out.println("======== Menu Tambah User Baru ========");
			System.out.println("1. Tambah Customer");
			System.out.println("2. Tambah PIC");
			System.out.println("3. Tambah Developer");
			System.out.println("4. Kembali");

			final byte choice = (byte) ScannerUtil.getScannerNumber("Pilih opsi (1-4): ", 4);

			switch (choice) {
			case 1:
				System.out.println("===== Menu tambah User =====");
				final List<Company> companies = companyService.getAllCompany();
				for (int i = 0; i < companies.size(); i++) {
					final Company company = companies.get(i);
					System.out.println(i + 1 + ". " + company.getNameCompany());
				}
				final int optionCompany = (int) ScannerUtil.getScannerNumber("Pilih Perusahaan : ", companies.size());
				final Company company = companies.get(optionCompany - 1);

				final List<Role> roles = roleService.getAll();
				for (int i = 0; i < roles.size(); i++) {
					final Role role = roles.get(i);
					System.out.println(i + 1 + ". " + role.getNameRole());
				}
				final int optionRole = (int) ScannerUtil.getScannerNumber("Pilih Role : ", roles.size());
				final Role role = roles.get(optionRole - 1);

				final String fullName = ScannerUtil.getScannerString("Nama user : ");
				final String phone = ScannerUtil.getScannerString("Nomor Hp : ");
				final String email = ScannerUtil.getScannerString("Email : ");
				final String password = ScannerUtil.getScannerString("Password : ");
				final String photo = ScannerUtil.getScannerString("Foto(gambar.jpg) : ");
				final int lastDot = photo.lastIndexOf(".");
				final String extPhoto = photo.substring(lastDot + 1);

				final File file = new File();
				file.setFiles(photo);
				file.setExt(extPhoto);
				final Profile profile = new Profile();
				profile.setFullName(fullName);
				profile.setPhone(phone);
				profile.setProfilePhoto(file);
				final User user = new User();
				user.setUserEmail(email);
				user.setPasswordUser(password);
				user.setRole(role);
				user.setCompany(company);
				user.setCreatedBy(currentIdUser);
				final User userResult = userService.insert(user, profile, file);

				if (userResult != null) {
					System.out.println("Create User Berhasil");
				} else {
					System.out.println("Create User Gagal");
				}
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				exitAddUser = false;
				System.out.println("Kembali ke Menu Create Master");
				break;
			default:
				System.out.println("Opsi tidak valid. Silakan pilih opsi yang benar.");
				break;
			}
		}
	}

	private void showProductCust() throws Exception {

		System.out.println("Menu Product Customer");
		System.out.println("1. Tampilkan Semua Product");
		System.out.println("2. Assign Product Customer");
		System.out.println("3. Keluar");
		System.out.println("===================================");
		String roleCode = "";

		final int option = (int) ScannerUtil.getScannerNumber("Pilih Menu: ", 3);
		if (option == 1) {
			final List<Product> products = productService.getAll();
			int counter = 1;
			System.out.println("+===============================+");
			System.out.println("+==   Tampilan Semua Product  ==+");
			System.out.println("+===============================+");
			products.forEach(p -> {
				System.out.println(counter + ". " + p.getProductName());
			});
		} else if (option == 2) {
			final List<Product> products = productService.getAll();
			System.out.println("=================================");
			System.out.println("===   Tampilan Semua Product  ===");
			System.out.println("=================================");
			int counter = 0;
			for (Product p : products) {
				System.out.println(counter + 1 + ". " + p.getProductName());
				++counter;
			}
			final int productChoice = (int) ScannerUtil.getScannerNumber("Pilih Product : ", counter);

			roleCode = RoleConst.CUST.getRoleCode();
			final List<User> customers = userService.getByRoleCode(roleCode);
			System.out.println("=================================");
			System.out.println("======  Tampilan Customer  ======");
			System.out.println("=================================");
			int counterC = 0;
			for (User u : customers) {
				System.out.println(counterC + 1 + ". " + u.getProfile().getFullName());
				++counterC;
			}
			final int customerChoice = (int) ScannerUtil.getScannerNumber("Pilih Customer : ", counterC);

			productCustomerService.insert(getIdCustomer(customerChoice), getIdProduct(productChoice), currentIdUser);
		} else if (option == 3) {
			System.out.println("Terimakasihhh .... ");
		} else {
			System.out.println("Pilihan tidak ada,input ulang");
			show();
		}
	}

	private void showPicCustomer() throws Exception {
		System.out.println("Halooo " + currentNameUser);
		System.out.println("-- Menu Product Customer");
		System.out.println("1. Tampilkan Semua Customer yg belum memiliki PIC");
		System.out.println("2. Assign PIC Customer");
		System.out.println("3. Keluar");
		System.out.println("===================================");
		String roleCode = "";

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
				System.out.println(counterPic + 1 + ". " + u.getProfile().getFullName());
				++counterPic;
			}
			if (counterPic > 0) {
				final int picChoice = (int) ScannerUtil.getScannerNumber("Pilih Product : ", counterPic);
				roleCode = RoleConst.CUST.getRoleCode();
				final List<User> customers = userService.getByRoleCode(roleCode);
				System.out.println("=================================");
				System.out.println("======  Tampilan Customer  ======");
				System.out.println("=================================");
				int counterC = 0;
				for (User u : customers) {
					System.out.println(counterC + 1 + ". " + u.getProfile().getFullName());
					++counterC;
				}
				if (counterC > 0) {
					final int customerChoice = (int) ScannerUtil.getScannerNumber("Pilih Customer : ", counterC);
					picCustomerService.insert(getIdPic(picChoice), getIdCustomer(customerChoice), currentIdUser);
				} else {
					System.out.println("");
				}
			} else {
				System.out.println("PIC Masih Kosong");
			}

		} else if (option == 3) {
			System.out.println("Terimakasihhh .... ");
		} else {
			System.out.println("Pilihan tidak ada,input ulang");
			show();
		}
	}

	private Long getIdProduct(int index) throws Exception {
		final List<Product> listProduct = productService.getAll();
		final Long id = listProduct.get(index - 1).getId();
		return id;
	}

	private Long getIdCustomer(int index) throws Exception {
		final List<User> listCustomer = userService.getByRoleCode(RoleConst.CUST.getRoleCode());
		final Long id = listCustomer.get(index - 1).getId();
		return id;
	}

	private Long getIdPic(int index) throws Exception {
		final List<User> listPic = userService.getByRoleCode(RoleConst.PIC.getRoleCode());
		final Long id = listPic.get(index - 1).getId();
		return id;
	}

	public void setIdCurrentUser(Long id) {
		this.currentIdUser = id;
	}

	public void setNameUser(String user) {
		this.currentNameUser = user;
	}
}
