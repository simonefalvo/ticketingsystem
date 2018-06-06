package it.uniroma2.ticketingsystem.dao;

import it.uniroma2.ticketingsystem.aud.TicketAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.List;


public interface TicketAuditDao extends JpaRepository<TicketAudit,Integer> {

    @Query("select count (distinct t.idTicket) from TicketAudit t where t.stato = ?1")
    Integer numberOfStatusTickets(String status);

    @Query("select count (distinct t.idTicket) from TicketAudit t where t.timestamp >= ?1 and t.timestamp <= ?2 and t.stato = ?3")
    Integer ticketInIntervallo(Timestamp timestamp1, Timestamp timestamp2, String stato);

    @Query("select count (distinct t.idTicket) from TicketAudit t where t.timestamp = ?1 and t.stato = ?2")
    Integer dailyTickets(Timestamp timestamp, String stato);

    @Query("select t from TicketAudit t where t.idTicket = ?1")
    List<TicketAudit> getTicketAudsByTicketId(Integer ticketId);
}