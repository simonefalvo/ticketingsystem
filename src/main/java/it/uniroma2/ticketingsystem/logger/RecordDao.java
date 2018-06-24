package it.uniroma2.ticketingsystem.logger;

import it.uniroma2.ticketingsystem.logger.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

@Repository
public interface RecordDao extends JpaRepository<Record,Integer> {

    @Query("select r from Record r where r.tag = ?1")
    List<Record> getRecordsByTag(@NotNull String tag);

    @Query("select r from Record r where r.author = ?1")
    List<Record> getRecordsByAuthor(@NotNull String author);

    @Query("select distinct r from Record r, Payload p where r.id = p.record and p.objectId = ?1")
    List<Record> getRecordsByObjectId(@NotNull String objectId);

    List<Record> getRecordsByOperationName(@NotNull String opName);

    @Query("select r from Record r where r.operationName = :opName AND r.timestamp BETWEEN :start AND :end ")
    List<Record> getRecordsByOperationNameAndTimestampBetween(@NotNull @Param("opName") String opName, @NotNull @Param("start") Timestamp startDate, @NotNull @Param("end") Timestamp endDate);

    @Query("select COUNT(r.id) from Record r where r.operationName = :opName AND r.timestamp BETWEEN :start AND :end ")
    Integer countRecordsByOperationNameAndTimestampBetween(@NotNull @Param("opName") String opName, @NotNull @Param("start") Timestamp startDate, @NotNull @Param("end") Timestamp endDate);

}
