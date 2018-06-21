package it.uniroma2.ticketingsystem.logger;

import it.uniroma2.ticketingsystem.logger.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface RecordDao extends JpaRepository<Record,Integer> {


    @Query("select r from Record r where r.tag = ?1")
    List<Record> getRecordsByTag(@NotNull String tag);

    @Query("select r from Record r where r.author = ?1")
    List<Record> getRecordsByAuthor(@NotNull String author);

}
