package it.uniroma2.ticketingsystem.dao;

import it.uniroma2.ticketingsystem.aud.TicketAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface TicketAuditDao extends JpaRepository<TicketAudit,Integer> {

    @Query("select count (u) from TicketAudit u where u.stato = 'open'")
    public Integer numberOfOpenTickets();

}