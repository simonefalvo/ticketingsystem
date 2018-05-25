package it.uniroma2.ticketingsystem.dao;

import it.uniroma2.ticketingsystem.entity.Oggetto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OggettoDao extends JpaRepository<Oggetto,Integer> {}
