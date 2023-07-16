package com.lawencon.ticketing.dao.impl.hql;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketing.dao.TicketDao;
import com.lawencon.ticketing.model.Ticket;


@Repository
@Profile("hql-query")
public class TicketDaoHQLImpl implements TicketDao {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Ticket> getAll(){
		final String sql = 
				"SELECT t "
				+ "FROM Ticket ";
		final List<Ticket> tickets = this.em.createQuery(sql, Ticket.class).getResultList();
		return tickets;
	}

	@Override
	public Ticket insert(Ticket ticket){
		em.persist(ticket);
		return ticket;
	}

	@Override
	public List<Ticket> getAllByIdCust(Long idCust){
		final String sql = "SELECT tt " 
				+ "FROM Ticket tt "
				+ "WHERE tt.user.id = :idCust ";

		final List<Ticket> tickets = this.em.createQuery(sql, Ticket.class)
				.setParameter("idCust", idCust)
				.getResultList();
		return tickets;
	}
	
	@Override
	public Ticket update(Ticket ticket){
		return ticket;
	}

	@Override
	public Ticket getTicketById(Long idTicket){
		final Ticket ticket = this.em.find(Ticket.class, idTicket);
		return ticket;
	}

	@Override
	public List<Ticket> getAllByIdPic(Long idPic, String statusCode, String statusCode2){
		final String sql = "SELECT tt " 
				+ "FROM Ticket tt " 
				+ "INNER JOIN PicCustomer pc ON pc.customer = tt.user "
				+ "WHERE pc.pic.id = :idPic AND tt.status.statusCode = :statusCode OR tt.status.statusCode = :statusCode2 ";
		
		final List<Ticket> tickets = this.em.createQuery(sql, Ticket.class)
				.setParameter("idPic",idPic)
				.setParameter("statusCode", statusCode)
				.setParameter("statusCode2", statusCode2)
				.getResultList();
		return tickets;
	}
	

	@Override
	public List<Ticket> getAllByIdDev(Long idDev, String statusCode, String statusCode2){
		final String sql = "SELECT tt " 
				+ "FROM Ticket tt " 
				+ "INNER JOIN TicketDeveloper td ON td.developer = tt.user "
				+ "WHERE td.developer.id = :idDev AND tt.status.statusCode = :statusCode OR tt.status.statusCode = :statusCode2 ";
		
		final List<Ticket> tickets = this.em.createQuery(sql, Ticket.class)
				.setParameter("idDev", idDev)
				.setParameter("statusCode", statusCode)
				.setParameter("statusCode2", statusCode2)
				.getResultList();
		return tickets;
	}
}
