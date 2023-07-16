package com.lawencon.ticketing.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketing.dao.TicketDao;
import com.lawencon.ticketing.model.Ticket;


@Repository
@Profile("native")
public class TicketDaoImpl implements TicketDao {
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<Ticket> getAll() {
		final String sql = "SELECT * FROM t_ticket";
		final List<Ticket> tickets = this.em.createNativeQuery(sql, Ticket.class).getResultList();
		return tickets;
	}

	@Override
	public Ticket insert(Ticket ticket) {
		em.persist(ticket);
		return ticket;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Ticket> getAllByIdCust(Long idCust) {
		final String sql = "SELECT * " + "FROM t_ticket "
				+ "INNER JOIN t_status ON t_ticket.ticket_status_id = t_status.id "
				+ "INNER JOIN t_priority ON t_ticket.priority_id = t_priority.id "
				+ "INNER JOIN t_users tu ON t_ticket.user_id = tu.id " + "WHERE tu.id = :idCust";

		final List<Ticket> tickets = this.em.createNativeQuery(sql, Ticket.class)
				.setParameter("idCust", idCust)
				.getResultList();
		return tickets;
	}

	@Override
	public Ticket update(Ticket ticket) {
		return ticket;
	}

	@Override
	public Ticket getTicketById(Long idTicket)  {
		final Ticket ticket = this.em.find(Ticket.class, idTicket);
		return ticket;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Ticket> getAllByIdPic(Long idPic, String statusCode, String statusCode2) {
		final String sql = "SELECT * " + "FROM t_ticket tt " 
				+ "INNER JOIN t_status ts ON ts.id = tt.ticket_status_id "
				+ "INNER JOIN t_pic_customer tpc ON tpc.customer_id = tt.user_id "
				+ "INNER JOIN t_users tu ON tpc.customer_id = tu.id "
				+ "INNER JOIN t_profile tp ON tp.id = tu.profile_id "
				+ "WHERE tpc.pic_id = :idPic AND ts.status_code = :statusCode OR ts.status_code = :statusCode2";
		final List<Ticket> tickets = this.em.createNativeQuery(sql, Ticket.class)
				.setParameter("idPic",idPic)
				.setParameter("statusCode", statusCode)
				.setParameter("statusCode2", statusCode2)
				.getResultList();
		return tickets;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Ticket> getAllByIdDev(Long idDev, String statusCode, String statusCode2) {
		final String sql = "SELECT * " + "FROM t_ticket tt " + "INNER JOIN t_status ts ON ts.id = tt.ticket_status_id "
				+ "INNER JOIN t_ticket_developer ttd ON ttd.ticket_id = tt.id "
				+ "INNER JOIN t_users tu ON ttd.developer_id = tu.id "
				+ "INNER JOIN t_profile tp ON tp.id = tu.profile_id "
				+ "WHERE ttd.developer_id = :idDev AND ts.status_code = :statusCode OR ts.status_code = :statusCode2";

		
		final List<Ticket> tickets = this.em.createNativeQuery(sql, Ticket.class)
				.setParameter("idDev", idDev)
				.setParameter("statusCode", statusCode)
				.setParameter("statusCode2", statusCode2)
				.getResultList();
		return tickets;
	}
}
