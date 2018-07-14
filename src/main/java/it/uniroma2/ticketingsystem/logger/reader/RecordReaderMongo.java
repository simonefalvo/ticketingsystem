package it.uniroma2.ticketingsystem.logger.reader;

import it.uniroma2.ticketingsystem.logger.controller.RecordControllerJpa;
import it.uniroma2.ticketingsystem.logger.controller.RecordControllerMongo;
import it.uniroma2.ticketingsystem.logger.entity.jpa.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

@Component

@Configuration
@Profile("mongo")
public class RecordReaderMongo {

    @Autowired
    private RecordControllerMongo recordController;
    /*

    public void deleteRecord(@NotNull Integer id){
        recordController.deleteRecord(id);
    }

    public List<Record> getAllRecords(){
        return  recordController.getAllRecords();
    }

    */

    //TODO: creare metodi

}
