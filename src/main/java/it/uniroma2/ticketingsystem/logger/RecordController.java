package it.uniroma2.ticketingsystem.logger;

import it.uniroma2.ticketingsystem.logger.entity.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import javax.validation.constraints.NotNull;

@Service
public class RecordController {

    @Autowired
    private RecordDao recordDao;

    public @NotNull Record createRecord(@NotNull Record record){
        return recordDao.save(record);
    }

    public boolean deleteRecord(@NotNull Integer id){
        if(!recordDao.existsById(id)){
            return false;
        }
        recordDao.deleteById(id);
        return true;
    }

    public List<Record> getAllRecords(){
        return recordDao.findAll();
    }

    public Record findRecordById(@NotNull Integer id){
        if(!recordDao.existsById(id)){
            return null;
        }
        return recordDao.getOne(id);
    }
/*
    public List<Record> getAllRecordsByObjectId(@NotNull Integer id){
        return recordDao.findRecordsByObjectId(id);
    }
    */
}
