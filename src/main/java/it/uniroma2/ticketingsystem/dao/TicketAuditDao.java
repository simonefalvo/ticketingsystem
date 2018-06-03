package it.uniroma2.ticketingsystem.dao;

import it.uniroma2.ticketingsystem.aud.TicketAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.List;


public interface TicketAuditDao extends JpaRepository<TicketAudit,Integer> {

    @Query("select count (distinct t.idTicket) from TicketAudit t where t.stato = ?1")
    Integer numberOfStatusTickets(String status);

    //CAMBIARE TABELLA TICKET IN TICKET_AUDIT
    @Query("select count (distinct t.idTicket) from TicketAudit t where t.timestamp = ?1")
    Integer dailyTickets(Timestamp timestamp);

    @Query("select t from TicketAudit t where t.idTicket = ?1")
    List<TicketAudit> getTicketAudsByTicketId(Integer ticketId);
}