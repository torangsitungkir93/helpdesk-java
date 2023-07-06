package com.lawencon.ticketing.view;

import java.sql.SQLException;
import java.util.List;

import com.lawencon.ticketing.constant.RoleConst;
import com.lawencon.ticketing.constant.StatusConst;
import com.lawencon.ticketing.model.Ticket;
import com.lawencon.ticketing.model.TicketDeveloper;
import com.lawencon.ticketing.model.User;
import com.lawencon.ticketing.service.TicketDeveloperService;
import com.lawencon.ticketing.service.TicketService;
import com.lawencon.ticketing.service.UserService;
import com.lawencon.ticketing.util.ScannerUtil;

public class PicView {
	private final TicketService ticketService;
	private final TicketDeveloperService ticketDeveloperService;
	private final UserService userService;
	
	private Long currentIdUser = (long) 0;
	private String currentNameUser; 
	
	public PicView(TicketService ticketService,TicketDeveloperService ticketDeveloperService,UserService userService) {
		this.ticketService = ticketService;
		this.ticketDeveloperService = ticketDeveloperService;
		this.userService = userService;
	}

	public void show() throws SQLException {
		System.out.println("-----------------------------------");
		System.out.println("--- Halooo "+currentNameUser);
		System.out.println("-----------------------------------");
		System.out.println("Menu untuk PIC");
		System.out.println("1. Show my Ticket");
		System.out.println("2. Change Status Ticket");
		System.out.println("3. Keluar");
		
		byte choice = (byte) ScannerUtil.getScannerNumber("Masukkan Pilihan Anda : ", 4);
		switch (choice) {
		case 1:
			System.out.println("Show my Ticket");
			final List<Ticket> tickets = ticketService.getAllByIdPic(currentIdUser,StatusConst.OPEN.getStatusCode(),StatusConst.REOPEN.getStatusCode());

			System.out.println("+===============================+");
			System.out.println("+=========  My Ticket  =========+");
			System.out.println("+===============================+");

			System.out.println("CEK bug : "+tickets.size());
			for (int i = 0; i < tickets.size(); i++) {
				Ticket t = tickets.get(i);
				System.out.println((i + 1) + ". " + t.getTicketCode() + " " + t.getTicketTitle()+" "+ t.getStatus().getStatusName());
			}
			break;
		case 2:
			final List<Ticket> ticketStatus = ticketService.getAllByIdPic(currentIdUser,StatusConst.OPEN.getStatusCode(),
					StatusConst.REOPEN.getStatusCode());

			System.out.println("+===============================+");
			System.out.println("+======== Change Status ========+");
			System.out.println("+===============================+");
			
			for (int i = 0; i < ticketStatus.size(); i++) {
				Ticket t = ticketStatus.get(i);
				System.out.println((i + 1) + ". " + t.getTicketCode() + " " + t.getTicketTitle()+" "+ t.getStatus().getStatusName());
			}
			
			if(ticketStatus.size()>0) {
				final int ticketChoice = (int) ScannerUtil.getScannerNumber("Pilih Ticket : ", ticketStatus.size());
				final Ticket ticket = ticketStatus.get(ticketChoice-1);
				
				final List<User> users = userService.getByRoleCode(RoleConst.DEVELOPER.getRoleCode());

				System.out.println("+===============================+");
				System.out.println("+======== List Developer =======+");
				System.out.println("+===============================+");
				
				for (int i = 0; i < users.size(); i++) {
					User u = users.get(i);
					System.out.println((i + 1) + ". " + u.getProfile().getFullName());
				}
				if(users.size()>0) {
					final int devChoice = (int) ScannerUtil.getScannerNumber("Pilih Developer : ", users.size());
					final Long idDeveloper = users.get(devChoice-1).getId();
					final TicketDeveloper tService=ticketDeveloperService.insert(ticket.getId(), idDeveloper, currentIdUser);
					ticketService.updateStatusTicket(RoleConst.PIC.getRoleCode(),ticket.getStatus().getStatusCode(), ticket, currentIdUser);
					if(tService!=null) {
						System.out.println("Berhasil Mengubah Status");
					}else {
						System.out.println("Gagal Mmengubah Status");
					}
				}else {
					System.out.println("Developer Masih belum tersedia");
				}
			}else {
				System.out.println("Ticket Masih kosong !");
			}
			break;
		case 3:
			break;
		default:
			System.out.println("Menu tidak tersedia");
		}
	}
	
	public void setIdCurrentUser(Long id) {
		this.currentIdUser = id;
	}
	
	public void setNameUser(String user) {
		this.currentNameUser = user;
	}
}