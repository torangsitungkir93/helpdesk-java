package com.lawencon.ticketing.view;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.lawencon.ticketing.constant.RoleConst;
import com.lawencon.ticketing.constant.StatusConst;
import com.lawencon.ticketing.model.File;
import com.lawencon.ticketing.model.Ticket;
import com.lawencon.ticketing.service.CommentService;
import com.lawencon.ticketing.service.TicketService;
import com.lawencon.ticketing.util.ScannerUtil;

public class DeveloperView {
	private final TicketService ticketService;
	private final CommentService commentService;

	private Long currentIdUser = (long) 0;
	private String currentNameUser;

	public DeveloperView(TicketService ticketService, CommentService commentService) {
		this.ticketService = ticketService;
		this.commentService = commentService;
	}

	public void show() throws SQLException {
		System.out.println("-----------------------------------");
		System.out.println("--- Halooo " + currentNameUser);
		System.out.println("-----------------------------------");
		System.out.println("--- Menu untuk Developer ---");
		System.out.println("1. Ticket Detail");
		System.out.println("2. Ubah Status Ticket");
		System.out.println("3. Keluar");

		byte choice = (byte) ScannerUtil.getScannerNumber("Masukkan Pilihan Anda : ", 3);
		switch (choice) {
		case 1:
			final List<Ticket> tickets = ticketService.getAllByIdDev(currentIdUser,
					StatusConst.ONPROGGRESS.getStatusCode(), StatusConst.ONPROGGRESS.getStatusCode());

			System.out.println("+===============================+");
			System.out.println("+========== My Ticket ==========+");
			System.out.println("+===============================+");

			for (int i = 0; i < tickets.size(); i++) {
				Ticket t = tickets.get(i);
				System.out.println((i + 1) + ". " + t.getTicketCode() + " " + t.getTicketTitle() + " "
						+ t.getStatus().getStatusName());
			}

			if (tickets.size() > 0) {
				final int ticketChoice = (int) ScannerUtil.getScannerNumber("Pilih Ticket yg ingi dikomentari : ",
						tickets.size());
				System.out.println("---- Berkomentar ------");
				final Long idTicket = tickets.get(ticketChoice-1).getId();
				
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
			} else {
				System.out.println("Tiket Belum tersedia");
			}
			break;
		case 2:
			System.out.println("--- Ubah Status Ticket ---");
			final List<Ticket> ticketStatus = ticketService.getAllByIdDev(currentIdUser,
					StatusConst.PENDINGAGENT.getStatusCode(), StatusConst.ONPROGGRESS.getStatusCode());

			System.out.println("+===============================+");
			System.out.println("+======== Change Status ========+");
			System.out.println("+===============================+");

			for (int i = 0; i < ticketStatus.size(); i++) {
				Ticket t = ticketStatus.get(i);
				System.out.println((i + 1) + ". " + t.getTicketCode() + " " + t.getTicketTitle() + " "
						+ t.getStatus().getStatusName());
			}

			if (ticketStatus.size() > 0) {
				final int ticketChoice = (int) ScannerUtil.getScannerNumber("Pilih Ticket : ", ticketStatus.size());
				final Ticket ticket = ticketStatus.get(ticketChoice - 1);
				ticketService.updateStatusTicket(RoleConst.DEVELOPER.getRoleCode(), ticket.getStatus().getStatusCode(),
						ticket, currentIdUser);
			} else {
				System.out.println("Tiket Belum tersedia");
			}
			break;
		case 3:
			currentNameUser="";
			break;
		default:
			System.out.println("Menu tidak valid.");
		}
		if(currentNameUser!="") {
			show();
		}else {
			System.out.println("Anda telah Logout");
		}
	}

	public void setIdCurrentUser(Long id) {
		this.currentIdUser = id;
	}

	public void setNameUser(String user) {
		this.currentNameUser = user;
	}
}
