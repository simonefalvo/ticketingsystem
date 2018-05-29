package it.uniroma2.ticketingsystem.dao;

import it.uniroma2.ticketingsystem.entity.TicketAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketAuditDao extends JpaRepository<TicketAudit,Integer> {
}
