package it.uniroma2.ticketingsystem.dao;

import it.uniroma2.ticketingsystem.entity.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtenteDao extends JpaRepository<Utente,Integer>  {
}
