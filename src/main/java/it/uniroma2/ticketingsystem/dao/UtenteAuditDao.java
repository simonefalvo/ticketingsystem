package it.uniroma2.ticketingsystem.dao;

import it.uniroma2.ticketingsystem.aud.UtenteAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UtenteAuditDao extends JpaRepository<UtenteAudit,Integer> {

    @Query("select u.id from UtenteAudit u where u.idUtente = :my_id AND u.timestamp=(select max(timestamp) from UtenteAudit where idUtente = :my_id)")
    Integer getIdOfMostRecentUtenteAuditByUtente(@Param("my_id") int id);
}
