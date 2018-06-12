package it.uniroma2.ticketingsystem.logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import javax.validation.constraints.NotNull;

@Component
public class RecordReader {

    @Autowired
    private RecordController recordController;

    public void deleteRecord(@NotNull Integer id){
        recordController.deleteRecord(id);
    }

    public List<Record> getAllRecords(){
        return  recordController.getAllRecords();
    }

    /*
    public List<Record> getAllRecordsByObjectId(@NotNull Integer id){
        return recordController.getAllRecordsByObjectId(id);
    }
    */





}
