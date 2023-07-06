package com.lawencon.ticketing.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;

import com.lawencon.ticketing.config.EntityManagerConfig;
import com.lawencon.ticketing.dao.FileDao;
import com.lawencon.ticketing.dao.FileTicketDao;
import com.lawencon.ticketing.dao.StatusDao;
import com.lawencon.ticketing.dao.TicketDao;
import com.lawencon.ticketing.model.File;
import com.lawencon.ticketing.model.FileTicket;
import com.lawencon.ticketing.model.Priority;
import com.lawencon.ticketing.model.Product;
import com.lawencon.ticketing.model.Status;
import com.lawencon.ticketing.model.Ticket;
import com.lawencon.ticketing.model.User;
import com.lawencon.ticketing.service.StatusService;
import com.lawencon.ticketing.service.TicketService;
import com.lawencon.ticketing.util.GeneratorUtil;

public class TicketServiceImpl implements TicketService {

	private final TicketDao ticketDao;
	private final FileDao fileDao;
	private final FileTicketDao fileTicketDao;
	private final StatusService statusService;
	private final EntityManager em;

	public TicketServiceImpl(StatusDao statusDao, TicketDao ticketDao, FileDao fileDao,
			FileTicketDao fileTicketDao, StatusService statusService,DataSource dataSource,SessionFactory factory) throws SQLException  {
		this.ticketDao = ticketDao;
		this.fileDao = fileDao;
		this.fileTicketDao = fileTicketDao;
		this.statusService = statusService;
		this.em = EntityManagerConfig.get(factory);
	}

	@Override
	public Ticket createTicket(String ticketTitle, String ticketBody, Long userId, Long productId, Long priorityId,
			Long createdById, List<File> fileLists) throws SQLException {
		final Ticket ticket = new Ticket();
		final User user = new User();
		final Priority priority = new Priority();
		final Status status = new Status();
		final Product product = new Product();

		try {
			this.em.getTransaction().begin();
			user.setId(userId);
			ticket.setUser(user);

			priority.setId(priorityId);
			ticket.setPriority(priority);

			final Long statusId = statusService.getByRoleAndStatus(null, null).getId();
			status.setId(statusId);
			ticket.setStatus(status);

			product.setId(productId);
			ticket.setProduct(product);

			ticket.setTicketCode(GeneratorUtil.generateRandomCode());
			ticket.setTicketTitle(ticketTitle);
			ticket.setTicketBody(ticketBody);

			ticket.setCreatedBy(createdById);
			final LocalDateTime timeNow = LocalDateTime.now();
			ticket.setCreatedAt(timeNow);
			ticket.setIsActive(true);
			ticket.setVer(0);

			ticketDao.insert(ticket);
			if (fileLists.size() > 0) {
				for (int i = 0; i < fileLists.size(); i++) {
					final FileTicket fileTicket = new FileTicket();
					final File file = fileDao.insert(fileLists.get(i));
					fileTicket.setTicket(ticket);
					fileTicket.setFile(file);
					fileTicket.setCreatedBy(createdById);
					fileTicket.setCreatedAt(timeNow);
					fileTicket.setIsActive(true);
					fileTicket.setVer(0);
					fileTicketDao.insert(fileTicket);
				}
			}
			this.em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				this.em.getTransaction().rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return ticket;
	}

	@Override
	public List<Ticket> getAllByIdCust(Long idCust) throws SQLException {
		final List<Ticket> tickets = ticketDao.getAllByIdCust(idCust);
		return tickets;
	}

	@Override
	public Ticket updateStatusTicket(String roleCode, String statusCode, Ticket ticket, Long createdBy)
			throws SQLException {
		try {
			this.em.getTransaction().begin();
			final Status updatedStatus = statusService.getByRoleAndStatus(roleCode, statusCode);
			ticket.setStatus(updatedStatus);
			final LocalDateTime timeNow = LocalDateTime.now();
			ticket.setUpdatedAt (timeNow);
			ticket.setUpdatedBy(createdBy);
			this.em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace () ;
				try {
					this.em.getTransaction().rollback();
				} catch (Exception el) {
					e.printStackTrace();
				}
		}
		return ticket;
	}

	@Override
	public Ticket getTicketById(Long idTicket) throws SQLException {
		final Ticket ticket = ticketDao.getTicketById(idTicket);
		return ticket;
	}

	@Override
	public List<Ticket> getAllByIdPic(Long idCust, String statusCode, String statusCode2) throws SQLException {
		final List<Ticket> tickets = ticketDao.getAllByIdPic(idCust, statusCode, statusCode2);
		return tickets;
	}

	@Override
	public List<Ticket> getAllByIdDev(Long idDev, String statusCode, String statusCode2) throws SQLException {
		final List<Ticket> tickets = ticketDao.getAllByIdDev(idDev, statusCode, statusCode2);
		return tickets;
	}
	
}
