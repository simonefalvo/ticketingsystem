package it.uniroma2.ticketingsystem.dao;


import it.uniroma2.ticketingsystem.entity.Ruolo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RuoloDao extends JpaRepository<Ruolo,Integer> {
    Ruolo findByName(String name);
}
