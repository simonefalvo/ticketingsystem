package it.uniroma2.ticketingsystem.dao;

import it.uniroma2.ticketingsystem.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketDao extends JpaRepository<Ticket,Integer> {
}
