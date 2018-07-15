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
    private MongoRecordDao recordDao;

    public @NotNull Record createRecord(@NotNull Record record) {
        return recordDao.save(record);
    }

    public List<Record> getAllRecords() {
        return recordDao.findAll();
    }

    public List<Record> getRecordsByTag(@NotNull String tag) {
        return recordDao.getRecordsByTag(tag);
    }

    public List<Record> getRecordsByAuthor(@NotNull String author) {
        return recordDao.getRecordsByAuthor(author);
    }

    public List<Record> getRecordsByOperationName(@NotNull String opname) {
        return recordDao.getRecordsByOperationName(opname);
    }
}