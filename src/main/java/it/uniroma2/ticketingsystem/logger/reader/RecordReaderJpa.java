package it.uniroma2.ticketingsystem.logger.reader;

import it.uniroma2.ticketingsystem.logger.controller.RecordControllerJpa;
import it.uniroma2.ticketingsystem.logger.entity.jpa.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;
import javax.validation.constraints.NotNull;

@Component

@Configuration
@Profile("jpa")
public class RecordReaderJpa {

    @Autowired
    private RecordControllerJpa recordController;

    public void deleteRecord(@NotNull Integer id){
        recordController.deleteRecord(id);
    }

    public List<Record> getAllRecords(){
        return  recordController.getAllRecords();
    }

    public List<Record> getRecordsByTag(@NotNull String tag) {return recordController.getRecordsByTag(tag);}
    public List<Record> getRecordsByAuthor(@NotNull String author) {return recordController.getRecordsByAuthor(author);}
    public List<Record> getRecordsByOperation(@NotNull String opName) {return recordController.getRecordsByOperation(opName);}

    public Integer countRecordsByOperationNameAndTimestampBetween(@NotNull String opName, @NotNull Timestamp startDate, @NotNull Timestamp endDate){
        return recordController.countRecordsByOperationNameAndTimestampBetween(opName, startDate, endDate);
    }
    public List<Record> getRecordsByObjectId(@NotNull Object object) {
        return recordController.getRecordsByObjectId(object);
    }

    public Integer getNumberOfOpNameEvents(@NotNull String opName) {
        return recordController.getNumberOfOpNameEvents(opName);
    }

    public Integer getNumberOfTaggedEvents(@NotNull String tag) {
        return recordController.getNumberOfTaggedEvents(tag);
    }

    public Integer getNumberOfOpNameEventsBetween(@NotNull String opName, @NotNull Timestamp start, @NotNull Timestamp end) {
        return recordController.getNumberOfOpNameEventsBetween(opName, start, end);
    }

    public Integer getNumberOfTaggedEventsBetween(@NotNull String tag, @NotNull Timestamp start, @NotNull Timestamp end) {
        return recordController.getNumberOfTaggedEventsBetween(tag, start, end);
    }

    //TODO: query combinazione delle precedenti
}
