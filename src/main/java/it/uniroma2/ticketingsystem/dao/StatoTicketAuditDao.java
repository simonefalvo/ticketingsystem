package it.uniroma2.ticketingsystem.dao;

import it.uniroma2.ticketingsystem.aud.StatoTicketAudit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatoTicketAuditDao extends JpaRepository<StatoTicketAudit,Integer> {}