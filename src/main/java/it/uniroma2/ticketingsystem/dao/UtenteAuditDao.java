package it.uniroma2.ticketingsystem.dao;

import it.uniroma2.ticketingsystem.entity.UtenteAudit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtenteAuditDao extends JpaRepository<UtenteAudit,Integer> {
}