package it.uniroma2.ticketingsystem.logger;

import it.uniroma2.ticketingsystem.logger.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

@Repository
public interface RecordDao extends JpaRepository<Record,Integer> {


    List<Record> getRecordsByTag(@NotNull String tag);

    List<Record> getRecordsByAuthor(@NotNull String author);

    List<Record> getRecordsByOperationName(@NotNull String opName);

    @Query("select distinct r from Record r, Payload p where r.id = p.record and p.objectId = ?1")
    List<Record> getRecordsByObjectId(@NotNull String objectId);

    @Query("select count (distinct r.id) from Record r where r.operationName = ?1")
    Integer getNumberOfOpNameEvents(@NotNull String opName);

    @Query("select count (distinct r.id) from Record r where r.tag = ?1")
    Integer getNumberOfTaggedEvents(@NotNull String tag);

    @Query("select count (distinct r.id) from Record r where r.operationName = ?1 and r.timestamp between ?2 and ?3")
    Integer getNumberOfOpNameEventsBetween(@NotNull String opName, @NotNull Timestamp start, @NotNull Timestamp end);

    @Query("select count (distinct r.id) from Record r where r.tag = ?1 and r.timestamp between ?2 and ?3")
    Integer getNumberOfTaggedEventsBetween(@NotNull String tag, @NotNull Timestamp start, @NotNull Timestamp end);

}
