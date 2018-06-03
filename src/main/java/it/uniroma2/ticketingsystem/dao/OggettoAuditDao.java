package it.uniroma2.ticketingsystem.dao;

import it.uniroma2.ticketingsystem.aud.OggettoAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OggettoAuditDao extends JpaRepository<OggettoAudit,Integer> {
    /*
    @Query("select * from oggetto_audit u where u.id= :my_id AND u.timestamp=(select max(timestamp) from oggetto_audit where u.id = :my_id)")
    OggettoAudit getMostRecentOggettoAudit(@Param("my_id") int id);
    */
    @Query("select u.id from OggettoAudit u where u.idOggetto= :my_id AND u.timestamp=(select max(timestamp) from OggettoAudit where u.idOggetto = :my_id)")
    Integer getIdOfMostRecentOggettoAuditByOggetto(@Param("my_id") int id);
}
