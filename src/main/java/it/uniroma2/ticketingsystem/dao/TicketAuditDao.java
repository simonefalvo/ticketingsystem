package it.uniroma2.ticketingsystem.dao;

import it.uniroma2.ticketingsystem.aud.TicketAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface TicketAuditDao extends JpaRepository<TicketAudit,Integer> {

    @Query("select count (distinct u.idTicket) from TicketAudit u where u.stato = ?1")
    public Integer numberOfStatusTickets(String status);

}