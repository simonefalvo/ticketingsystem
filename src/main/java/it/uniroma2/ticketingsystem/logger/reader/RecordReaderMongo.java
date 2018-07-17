package it.uniroma2.ticketingsystem.logger.reader;


import it.uniroma2.ticketingsystem.logger.controller.RecordControllerMongo;
import it.uniroma2.ticketingsystem.logger.entity.mongo.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.List;

@Component

@Configuration
@Profile("mongo")
public class RecordReaderMongo {

    @Autowired
    private RecordControllerMongo recordController;


    public List<Record> getAllRecords() {
        return recordController.getAllRecords();
    }
    public List<Record> getRecordsByTag(@NotNull String tag) {
        return recordController.getRecordsByTag(tag);
    }
    public List<Record> getRecordsByAuthor(@NotNull String author) {
        return recordController.getRecordsByAuthor(author);
    }
    public List<Record> getRecordsByOperationName(@NotNull String opname) {
        return recordController.getRecordsByOperationName(opname);
    }

}
