package it.uniroma2.ticketingsystem.controller;

import it.uniroma2.ticketingsystem.logger.entity.jpa.Record;
import it.uniroma2.ticketingsystem.logger.reader.RecordReaderJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class LogController {

    @Autowired
    private RecordReaderJpa recordReader;

    public List<Record> prelevaLog() {
        return recordReader.getAllRecords();
    }

    public List<Record> cercaRecordPerTag(String tag) {return recordReader.getRecordsByTag(tag);}

    public List<Record> cercaRecordPerAutore(String author) {
        return recordReader.getRecordsByAuthor(author);
    }

    public Integer countRecordsByOperationNameAndTimestampBetween(String opName, Timestamp startDate, Timestamp endDate ) {
        return recordReader.countRecordsByOperationNameAndTimestampBetween(opName, startDate, endDate);
    }

    public Map<Long,Integer> getNumberOperationForEachDayBetween(Long fromMillis, Long toMillis, String operationName){
        Map<Long,Integer> myMap = new LinkedHashMap<>();
        Timestamp startDate = new Timestamp(fromMillis);
        Timestamp endDate = new Timestamp(toMillis);

        //get all date beetween two timestamp
        List<LocalDate> listDate = getAllDayBeteen(startDate,endDate);

        //calcolo il numero di operazioni di quel tipo in quel dato giorno
        for (LocalDate myDate: listDate) {
            //converto l'oggetto LocalDate in Timestamp iniziale e finale del giorno
            Timestamp start = Timestamp.valueOf(myDate.atTime(0,0,0,0));
            Timestamp end = Timestamp.valueOf(myDate.atTime(23,59,59, 999999999));

            //restituisce la lista degli oggetti, in futuro cambiare e fare una count direttamente
            Integer count = countRecordsByOperationNameAndTimestampBetween(operationName,start, end);
            //salvo nella mappa la coppia: data - #operazioni
            myMap.put(start.getTime(), count);

        }

        return myMap;
    }

    private List<LocalDate> getAllDayBeteen(Timestamp startDate, Timestamp endDate){
        //get all date beetween two timestamp
        long numOfDaysBetween = ChronoUnit.DAYS.between(startDate.toLocalDateTime().toLocalDate(), endDate.toLocalDateTime().toLocalDate());
        List<LocalDate> listDate =
                IntStream.iterate(0, i -> i + 1)
                        .limit(numOfDaysBetween)
                        .mapToObj(i -> startDate.toLocalDateTime().toLocalDate().plusDays(i))
                        .collect(Collectors.toList());
        return listDate;
    }




}
