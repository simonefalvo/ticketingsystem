package it.uniroma2.ticketingsystem.logger.controller;


import it.uniroma2.ticketingsystem.logger.dao.MongoRecordDao;
import it.uniroma2.ticketingsystem.logger.entity.mongo.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;


@Service

@Configuration
@Profile("mongo")
public class RecordControllerMongo {

    @Autowired
    private MongoRecordDao RecordDao;

//    @Transactional
    public @NotNull Record createRecord(@NotNull Record record) {
        return RecordDao.save(record);
    }

    public List<Record> getAllRecords() {
        return RecordDao.findAll();
    }

}