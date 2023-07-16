package com.lawencon.ticketing.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.lawencon.ticketing.dao.FileDao;
import com.lawencon.ticketing.dao.FileTicketDao;
import com.lawencon.ticketing.dao.PriorityDao;
import com.lawencon.ticketing.dao.ProductDao;
import com.lawencon.ticketing.dao.StatusDao;
import com.lawencon.ticketing.dao.TicketDao;
import com.lawencon.ticketing.dao.UserDao;
import com.lawencon.ticketing.dto.InsertResDto;
import com.lawencon.ticketing.dto.ticket.TicketInsertReqDto;
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


@Service
public class TicketServiceImpl implements TicketService {
	private final static Long USER_ID = (long) 1;
	private final TicketDao ticketDao;
	private final FileDao fileDao;
	private final FileTicketDao fileTicketDao;
	private final StatusService statusService;
	private final PriorityDao priorityDao;
	private final StatusDao statusDao;
	private final UserDao userDao;
	private final ProductDao productDao;
	@PersistenceContext
	private EntityManager em;

	public TicketServiceImpl(StatusDao statusDao, TicketDao ticketDao, FileDao fileDao,
			FileTicketDao fileTicketDao, StatusService statusService,PriorityDao priorityDao,
			UserDao userdao,ProductDao productDao) {
		this.ticketDao = ticketDao;
		this.fileDao = fileDao;
		this.fileTicketDao = fileTicketDao;
		this.statusService = statusService;
		this.priorityDao = priorityDao;
		this.statusDao = statusDao;
		this.userDao = userdao;
		this.productDao = productDao;
	}

	@Transactional
	@Override
	public InsertResDto createTicket(TicketInsertReqDto data) {
		InsertResDto response = null;
		Ticket ticketResult = null;
		final Ticket ticket = new Ticket();
		final User user = userDao.getById(data.getUserId());
		final Priority priority = priorityDao.getByIdRef(data.getPriorityId());
		final Product product = productDao.getById(data.getProductId());
		final Long statusId = statusService.getByRoleAndStatus(null, null).getId();
		final Status status = statusDao.getByIdRef(statusId);

			ticket.setUser(user);
			ticket.setPriority(priority);
			ticket.setStatus(status);
			ticket.setProduct(product);

			ticket.setTicketCode(GeneratorUtil.generateRandomCode());
			ticket.setTicketTitle(data.getTicketTitle());
			ticket.setTicketBody(data.getTicketBody());

			ticket.setCreatedBy(USER_ID);
			final LocalDateTime timeNow = LocalDateTime.now();
			ticket.setCreatedAt(timeNow);

			ticketResult=ticketDao.insert(ticket);
			if (data.getFileList().size() > 0) {
				for (int i = 0; i < data.getFileList().size(); i++) {
					final FileTicket fileTicket = new FileTicket();
					final File newFile = new File();
					newFile.setFiles(data.getFileList().get(i).getFiles());
					newFile.setExt(data.getFileList().get(i).getExt());
					newFile.setCreatedBy(USER_ID);
					final File file = fileDao.insert(newFile);
					fileTicket.setTicket(ticket);
					fileTicket.setFile(file);
					fileTicket.setCreatedBy(USER_ID);
					fileTicket.setCreatedAt(timeNow);
					fileTicketDao.insert(fileTicket);
				}
			}
			response = new InsertResDto();
			response.setId(ticketResult.getId());
			response.setMessage("Berhasil Input User");
		return response;
	}


	@Override
	public Ticket updateStatusTicket(String roleCode, String statusCode, Ticket ticket, Long createdBy)
			{
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
	public Ticket getTicketById(Long idTicket) {
		final Ticket ticket = ticketDao.getTicketById(idTicket);
		return ticket;
	}
	
	@Override
	public List<Ticket> getAllByIdCust(Long idCust) {
		final List<Ticket> tickets = ticketDao.getAllByIdCust(idCust);
		return tickets;
	}

	@Override
	public List<Ticket> getAllByIdPic(Long idCust, String statusCode, String statusCode2) {
		final List<Ticket> tickets = ticketDao.getAllByIdPic(idCust, statusCode, statusCode2);
		return tickets;
	}

	@Override
	public List<Ticket> getAllByIdDev(Long idDev, String statusCode, String statusCode2){
		final List<Ticket> tickets = ticketDao.getAllByIdDev(idDev, statusCode, statusCode2);
		return tickets;
	}
	
}
