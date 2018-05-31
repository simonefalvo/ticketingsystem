package it.uniroma2.ticketingsystem.dao;

import it.uniroma2.ticketingsystem.entity.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UtenteDao extends JpaRepository<Utente,Integer>  {
    /*
    @Query("select u from Utente u where u.email = ?1")
    Utente findByEmailAddress(String emailAddress);
    */
}
