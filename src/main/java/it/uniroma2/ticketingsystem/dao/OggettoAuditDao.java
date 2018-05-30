package it.uniroma2.ticketingsystem.dao;

import it.uniroma2.ticketingsystem.aud.OggettoAudit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OggettoAuditDao extends JpaRepository<OggettoAudit,Integer> {
}
