package com.lawencon.ticketing.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;

import com.lawencon.ticketing.config.EntityManagerConfig;
import com.lawencon.ticketing.dao.TicketDao;
import com.lawencon.ticketing.model.Priority;
import com.lawencon.ticketing.model.Profile;
import com.lawencon.ticketing.model.Role;
import com.lawencon.ticketing.model.Status;
import com.lawencon.ticketing.model.Ticket;
import com.lawencon.ticketing.model.User;

public class TicketDaoImpl implements TicketDao {
	private final EntityManager em;

	public TicketDaoImpl(SessionFactory sessionFactory) throws SQLException {
		this.em = EntityManagerConfig.get(sessionFactory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Ticket> getAll() throws SQLException {
		final String sql = "SELECT * FROM t_ticket";
		final List<Ticket> tickets = this.em.createNativeQuery(sql, Ticket.class).getResultList();
		return tickets;
	}

	@Override
	public Ticket insert(Ticket ticket) throws SQLException {
		em.persist(ticket);
		return ticket;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Ticket> getAllByIdCust(Long idCust) throws SQLException {
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
	public Ticket update(Ticket ticket) throws SQLException {
//	    final String sql = "UPDATE t_ticket SET ticket_status_id = ?, updated_by = ?, updated_at = ?, is_active = ?, ver = ver + 1 WHERE id = ? RETURNING *";
//	    final PreparedStatement ps = conn.prepareStatement(sql);
//	    ps.setLong(1, ticket.getStatus().getId());
//	    ps.setLong(2, ticket.getUpdatedBy());
//	    ps.setTimestamp(3, Timestamp.valueOf(ticket.getUpdatedAt()));
//	    ps.setBoolean(4, ticket.getIsActive());
//	    ps.setLong(5, ticket.getId());   
//	    final ResultSet rs = ps.executeQuery();
//	    
//	    if (rs.next()) {    
//	    	ticket.setVer(rs.getInt("ver"));
//	    }
		return ticket;
	}

	@Override
	public Ticket getTicketById(Long idTicket) throws SQLException {
		final Ticket ticket = this.em.find(Ticket.class, idTicket);
		return ticket;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Ticket> getAllByIdPic(Long idPic, String statusCode, String statusCode2) throws SQLException {
		final String sql = "SELECT * " + "FROM t_ticket tt " + "INNER JOIN t_status ts ON ts.id = tt.ticket_status_id "
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
	public List<Ticket> getAllByIdDev(Long idDev, String statusCode, String statusCode2) throws SQLException {
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
