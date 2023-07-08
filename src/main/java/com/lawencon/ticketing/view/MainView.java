package com.lawencon.ticketing.view;

import com.lawencon.ticketing.constant.RoleConst;
import com.lawencon.ticketing.model.Role;
import com.lawencon.ticketing.model.User;
import com.lawencon.ticketing.service.PriorityService;
import com.lawencon.ticketing.service.UserService;
import com.lawencon.ticketing.util.ScannerUtil;

public class MainView {
	private final UserService userService;
	private final CustomerView customerView;
	private final SuperAdminView superAdminView;
	private final DeveloperView developerView;
	private final PicView picView;
	private boolean isRunning = true;

	public MainView(UserService userService, PriorityService priorityService, CustomerView customerView,
			SuperAdminView superAdminView, DeveloperView developerView, PicView picView) {
		this.userService = userService;
		this.customerView = customerView;
		this.superAdminView = superAdminView;
		this.developerView = developerView;
		this.picView = picView;
	}

	public void showMain() throws Exception {
		while (isRunning) {
			System.out.println("+=====================+");
			System.out.println("|1. Login             |");
			System.out.println("|2. Keluar            |");
			System.out.println("+=====================+");

			final int choice = (int) ScannerUtil.getScannerNumber("Pilih Menu: ", 2);
			if (choice == 1) {
				final String email = ScannerUtil.getScannerString("Masukkan Email : ");
				final String password = ScannerUtil.getScannerString("Masukkan Password : ");
				final User loggedInUser = userService.login(email, password);

				if (loggedInUser != null) {
					final Role userRole = loggedInUser.getRole();
					if (userRole != null) {
						final String currentRoleUser = userRole.getCodeRole();
						final String currentNameUser = loggedInUser.getProfile().getFullName();
						Long idUser = loggedInUser.getId();

						if (currentRoleUser.equals(RoleConst.SUPERADMIN.getRoleCode())) {
							superAdminView.setIdCurrentUser(idUser);
							superAdminView.setNameUser(currentNameUser);
							superAdminView.show();
						} else if (currentRoleUser.equals(RoleConst.CUST.getRoleCode())) {
							customerView.setIdCurrentUser(idUser);
							customerView.setNameUser(currentNameUser);
							customerView.show();
						} else if (currentRoleUser.equals(RoleConst.DEVELOPER.getRoleCode())) {
							developerView.setIdCurrentUser(idUser);
							developerView.setNameUser(currentNameUser);
							developerView.show();
						} else if (currentRoleUser.equals(RoleConst.PIC.getRoleCode())) {
							picView.setIdCurrentUser(idUser);
							picView.setNameUser(currentNameUser);
							picView.show();
						}
					} else {
						System.out.println("Email atau password yang anda masukkan salah");
					}
				} else {
					System.out.println("Email atau password yang anda masukkan salah");
				}
			} else if (choice == 2) {
				this.isRunning=false;
				System.out.println("Terimakasih telah menggunakan aplikasi ini");
			}
		}
	}
}
