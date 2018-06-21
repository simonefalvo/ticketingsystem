package it.uniroma2.ticketingsystem.logger;

import it.uniroma2.ticketingsystem.logger.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface RecordDao extends JpaRepository<Record,Integer> {

    /*
    @Query("select r from Record r where r.idObject = ?1")
    List<Record> findRecordsByObjectId(@NotNull Integer id);
    */
}
