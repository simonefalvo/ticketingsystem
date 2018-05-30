package it.uniroma2.ticketingsystem.dao;

import it.uniroma2.ticketingsystem.aud.TicketAud;
import it.uniroma2.ticketingsystem.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface TicketAudDao extends JpaRepository<TicketAud,Integer> {
    @Query("select count (u) from TicketAud u where u.stato = 'open'")
    public Integer numberOfOpenTickets();
}