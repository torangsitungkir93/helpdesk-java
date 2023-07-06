package com.lawencon.ticketing.view;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.lawencon.ticketing.constant.RoleConst;
import com.lawencon.ticketing.model.File;
import com.lawencon.ticketing.model.Priority;
import com.lawencon.ticketing.model.ProductCustomer;
import com.lawencon.ticketing.model.Ticket;
import com.lawencon.ticketing.service.CommentService;
import com.lawencon.ticketing.service.PriorityService;
import com.lawencon.ticketing.service.ProductCustomerService;
import com.lawencon.ticketing.service.TicketService;
import com.lawencon.ticketing.util.ScannerUtil;

public class CustomerView {
	private final ProductCustomerService productCustomerService;
	private final PriorityService priorityService;
	private final TicketService ticketService;
	private final CommentService commentService;

	private Long currentIdUser = (long) 0;
	private byte choice=0;
	private String currentNameUser;

	public CustomerView(ProductCustomerService productCustomerService, PriorityService priorityService,
			TicketService ticketService, CommentService commentService) {
		this.productCustomerService = productCustomerService;
		this.priorityService = priorityService;
		this.ticketService = ticketService;
		this.commentService = commentService;
	}

	public void show() throws Exception {
		do {
			System.out.println("-----------------------------------");
			System.out.println("--- Halooo " + currentNameUser);
			System.out.println("-----------------------------------");
			System.out.println("Menu untuk Customer");
			System.out.println("1. Ticket Detail");
			System.out.println("2. Buat Ticket");
			System.out.println("3. Ubah Status Ticket");
			System.out.println("4. Keluar");

			choice = (byte) ScannerUtil.getScannerNumber("Masukkan Pilihan Anda : ", 4);
			switch (choice) {
			case 1:
				showTicketDetail();
				break;
			case 2:
				createTicket();
				break;
			case 3:
				System.out.println("--- Ubah Status Ticket ---");
				final List<Ticket> tickets = ticketService.getAllByIdCust(currentIdUser);

				System.out.println("+===============================+");
				System.out.println("+=========  My Ticket  =========+");
				System.out.println("+===============================+");

				for (int i = 0; i < tickets.size(); i++) {
					Ticket t = tickets.get(i);
					System.out.printf("%-3s %-8s %-16s %-17s %s\n", (i + 1) + ".", t.getTicketCode(),
							t.getPriority().getNamePriority(), t.getStatus().getStatusName(), t.getTicketTitle());

				}

				if (tickets.size() > 0) {
					final int ticketChoice = (int) ScannerUtil.getScannerNumber("Pilih Ticket : ", tickets.size());
					final String currStatus = tickets.get(ticketChoice - 1).getStatus().getStatusCode();
					final String roleCode = RoleConst.CUST.getRoleCode();
					if (ticketService.updateStatusTicket(roleCode, currStatus, tickets.get(ticketChoice - 1),
							currentIdUser) != null) {
						System.out.println("Telah berhasil merubah status ticket");
					} else {
						System.out.println("Gagal Merubah Status Ticket ! ");
					}
				} else {
					System.out.println("Anda belum memiliki ticket");
				}
				break;
			case 4:
				currentNameUser = "";
				break;
			default:
				System.out.println("Pilihan tidak valid");
			}
			if (currentNameUser.equals("")) {
				System.out.println("Anda telah Logout");
			}
		} while (choice != 4 && !currentNameUser.equals(""));
	}

	private void showTicketDetail() throws Exception {
		System.out.println(" ------ Menu Ticket Detail ------");

		final List<Ticket> tickets = ticketService.getAllByIdCust(currentIdUser);

		int counterP = 0;
		System.out.println("+===============================+");
		System.out.println("+=========  My Ticket  =========+");
		System.out.println("+===============================+");

		for (Ticket t : tickets) {
			System.out.println((counterP + 1) + ". " + t.getTicketCode() + " " + " " + t.getPriority().getNamePriority()
					+ " " + t.getTicketTitle());
			++counterP;
		}
		if (counterP > 0) {
			final int ticketChoice = (int) ScannerUtil.getScannerNumber("Pilih Ticket : ", counterP);

			System.out.println("---- Menu Komentar ------");
			System.out.println("1. Tampilkan Komentar");
			System.out.println("2. Tambah Komentar");
			System.out.println("3. Keluar");

			final int commentChoice = (int) ScannerUtil.getScannerNumber("Pilih menu : ", 3);

			switch (commentChoice) {
			case 1:
				System.out.println("---- Tampilkan Komentar ------");
				System.out.println("tampilan");
				break;
			case 2:
				System.out.println("---- Berkomentar ------");

				final Long idTicket = getIdTicket(ticketChoice);
				String comment = ScannerUtil.getScannerString("Komentar : ");

				List<File> fileList = new ArrayList<>();
				String addFilesChoice = ScannerUtil.getScannerString("Apakah Anda ingin menambahkan file? (y/n): ");

				if (addFilesChoice.equalsIgnoreCase("y")) {
					String addMoreFiles;
					do {
						final String fileName = ScannerUtil.getScannerString("Masukkan path file (gambar.jpg): ");
						File file = new File();
						file.setFiles(fileName);
						final int lastDot = fileName.lastIndexOf(".");
						String fileExt = fileName.substring(lastDot + 1);
						file.setExt(fileExt);
						file.setCreatedBy(currentIdUser);
						final LocalDateTime timeNow = LocalDateTime.now();
						file.setCreatedAt(timeNow);
						file.setIsActive(true);
						file.setVer(0);
						fileList.add(file);
						addMoreFiles = ScannerUtil.getScannerString("Tambah file lagi? (y/n): ");
					} while (addMoreFiles.equalsIgnoreCase("y"));
				} else {
					System.out.println("Tidak menambahkan file.");
				}
				commentService.createComment(currentIdUser, idTicket, comment, currentIdUser, fileList);
				break;
			case 3:
				System.out.println("---- Keluar ------");
				break;
			default:
				System.out.println("Menu tidak valid.");
			}
		} else {
			System.out.println("Anda Belum memiliki TIcket");
		}

	}

	private void createTicket() throws Exception {
		System.out.println("------- Menu Buat Ticket -------");

		final List<ProductCustomer> products = productCustomerService.getAllByIdCust(currentIdUser);
		int counterP = 0;
		System.out.println("+===============================+");
		System.out.println("+=====  Subscribed Product  ====+");
		System.out.println("+===============================+");

		for (ProductCustomer p : products) {
			System.out.println((counterP + 1) + ". " + p.getProduct().getProductName());
			++counterP;
		}

		final int productChoice = (int) ScannerUtil.getScannerNumber("Pilih Product : ", counterP);

		final List<Priority> priorities = priorityService.getAll();
		int counterPri = 1;
		System.out.println("+===============================+");
		System.out.println("+=======  Priority Level  ======+");
		System.out.println("+===============================+");

		for (Priority p : priorities) {
			System.out.println(counterPri + ". " + p.getNamePriority());
			++counterPri;
		}

		final int priorityChoice = (int) ScannerUtil.getScannerNumber("Pilih Level Prioritas : ", counterPri);

		String title = ScannerUtil.getScannerString("Judul Tiket : ");
		String body = ScannerUtil.getScannerString("Ceritakan keluhan anda : ");

		List<File> fileList = new ArrayList<>();
		String addMoreFiles;
		addMoreFiles = ScannerUtil.getScannerString("Ingin Memasukkan File (y/n) : ");
		while (addMoreFiles.equalsIgnoreCase("y")) {
			final String fileName = ScannerUtil.getScannerString("Masukkan path file : ");
			File file = new File();
			file.setFiles(fileName);
			final int lastDot = fileName.lastIndexOf(".");
			String fileExt = fileName.substring(lastDot + 1);
			file.setExt(fileExt);
			file.setCreatedBy(currentIdUser);
			final LocalDateTime timeNow = LocalDateTime.now();
			file.setCreatedAt(timeNow);
			file.setIsActive(true);
			file.setVer(0);
			fileList.add(file);
			addMoreFiles = ScannerUtil.getScannerString("Ingin Menambah File (y/n) : ");
		}

		ticketService.createTicket(title, body, currentIdUser, getIdProduct(productChoice),
				getIdPriority(priorityChoice), currentIdUser, fileList);

	}

	private Long getIdProduct(int index) throws Exception {
		final List<ProductCustomer> products = productCustomerService.getAllByIdCust(currentIdUser);
		final Long id = products.get(index - 1).getProduct().getId();
		return id;
	}

	private Long getIdPriority(int index) throws Exception {
		final List<Priority> listPriority = priorityService.getAll();
		final Long id = listPriority.get(index - 1).getId();
		return id;
	}

	public void setIdCurrentUser(Long id) {
		this.currentIdUser = id;
	}

	public void setNameUser(String user) {
		this.currentNameUser = user;
	}

	private Long getIdTicket(int index) throws Exception {
		final List<Ticket> tickets = ticketService.getAllByIdCust(currentIdUser);
		final Long id = tickets.get(index - 1).getId();
		return id;
	}
}
