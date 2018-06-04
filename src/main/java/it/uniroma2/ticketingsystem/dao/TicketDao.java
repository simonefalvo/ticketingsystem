package it.uniroma2.ticketingsystem.dao;

import it.uniroma2.ticketingsystem.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketDao extends JpaRepository<Ticket,Integer> {
    @Query("select count (distinct t.id) from Ticket t where t.stato = ?1")
    Integer numberOfStatusTickets(String status);
}
