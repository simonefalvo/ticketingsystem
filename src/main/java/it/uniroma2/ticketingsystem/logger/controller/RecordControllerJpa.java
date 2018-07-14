package it.uniroma2.ticketingsystem.logger.controller;

import it.uniroma2.ticketingsystem.logger.dao.JpaRecordDao;
import it.uniroma2.ticketingsystem.logger.entity.jpa.Record;
import it.uniroma2.ticketingsystem.logger.utils.ObjSer;
import it.uniroma2.ticketingsystem.logger.utils.ReflectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

@Service
@Configuration
@Profile("jpa")
public class RecordControllerJpa {

    @Autowired
    private JpaRecordDao recordDao;

    @Transactional
    public @NotNull Record createRecord(@NotNull Record record) {
        return recordDao.save(record);
    }

    public List<Record> getAllRecords() {
        return recordDao.findAll();
    }

    public boolean deleteRecord(@NotNull Integer id) {
        if (!recordDao.existsById(id)) {
            return false;
        }
        recordDao.deleteById(id);
        return true;
    }

    public Record getRecordById(@NotNull Integer id) {
        if (!recordDao.existsById(id)) {
            return null;
        }
        return recordDao.getOne(id);
    }

    public List<Record> getRecordsByTag(@NotNull String tag) { return recordDao.getRecordsByTag(tag); }

    public List<Record> getRecordsByAuthor(@NotNull String author) {
        return recordDao.getRecordsByAuthor(author);
    }

    public List<Record> getRecordsByOperation(@NotNull String opName) { return recordDao.getRecordsByOperationName(opName); }

    public List<Record> getRecordsByObjectId(@NotNull Object object) {
        String[] idParams = ReflectUtils.getIDParameters(object);
        String objectId = null;

        try {
            objectId = ObjSer.buildIDJson(object, idParams);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        return recordDao.getRecordsByObjectId(objectId);
    }

    public Integer getNumberOfOpNameEvents(@NotNull String opName) {
        return recordDao.getNumberOfOpNameEvents(opName);
    }

    public Integer getNumberOfTaggedEvents(@NotNull String tag) {
        return recordDao.getNumberOfTaggedEvents(tag);
    }

    public Integer getNumberOfOpNameEventsBetween(@NotNull String opName, @NotNull Timestamp start, @NotNull Timestamp end) {
        return recordDao.getNumberOfOpNameEventsBetween(opName, start, end);
    }

    public Integer getNumberOfTaggedEventsBetween(@NotNull String tag, @NotNull Timestamp start, @NotNull Timestamp end) {
        return recordDao.getNumberOfTaggedEventsBetween(tag, start, end);
    }

    public Integer countRecordsByOperationNameAndTimestampBetween(String opName, Timestamp startDate, Timestamp endDate ) {
        return recordDao.countRecordsByOperationNameAndTimestampBetween(opName, startDate, endDate);
    }


}