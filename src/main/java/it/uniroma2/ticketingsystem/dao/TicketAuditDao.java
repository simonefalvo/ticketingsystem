package it.uniroma2.ticketingsystem.dao;

import it.uniroma2.ticketingsystem.aud.TicketAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;


public interface TicketAuditDao extends JpaRepository<TicketAudit,Integer> {

    @Query("select count (distinct u.idTicket) from TicketAudit u where u.stato = ?1")
    public Integer numberOfStatusTickets(String status);

    @Query("select count (distinct u.idTicket) from TicketAudit u where u.timestamp = ?1")
    public Integer dailyTickets(Timestamp timestamp);

    @Query("select count (distinct u.idTicket) from TicketAudit u where u.timestamp >= ?1 and u.timestamp <= ?2")
    public Integer ticketInIntervallo(Timestamp timestamp1, Timestamp timestamp2);
}