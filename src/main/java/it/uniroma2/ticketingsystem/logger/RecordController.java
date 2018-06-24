package it.uniroma2.ticketingsystem.logger;

import it.uniroma2.ticketingsystem.logger.entity.Record;
import it.uniroma2.ticketingsystem.logger.utils.ObjSer;
import it.uniroma2.ticketingsystem.logger.utils.ReflectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.persistence.criteria.CriteriaBuilder;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

@Service
public class RecordController {

    @Autowired
    private RecordDao recordDao;

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


    /*
    public Map<Timestamp,Integer> getNumberOperationForEachDayBetween(Timestamp startDate, Timestamp endDate, String operationName){
        Map<Timestamp,Integer> myMap = new HashMap<>();

        //get all date beetween two timestamp
        long numOfDaysBetween = ChronoUnit.DAYS.between(startDate.toLocalDateTime().toLocalDate(), endDate.toLocalDateTime().toLocalDate());
        List<LocalDate> listDate =
                IntStream.iterate(0, i -> i + 1)
                .limit(numOfDaysBetween)
                .mapToObj(i -> startDate.toLocalDateTime().toLocalDate().plusDays(i))
                .collect(Collectors.toList());

        for (LocalDate myDate: listDate) {
            //calcolo il numero di operazioni di quel tipo in quel dato giorno
            //converto l'oggetto LocalDate in Timestamp iniziale e finale del giorno
            Timestamp start = Timestamp.valueOf(myDate.atTime(0,0,0,0));
            Timestamp end = Timestamp.valueOf(myDate.atTime(23,59,59, 999999999));

            //restituisce la lista degli oggetti, in futuro cambiare e fare una count direttamente
            Integer count = countRecordsByOperationAndDate(operationName,start, end);

            //salvo nella mappa la coppia: data - #operazioni
            myMap.put(start, count);

        }

        return myMap;
    }

    */
}