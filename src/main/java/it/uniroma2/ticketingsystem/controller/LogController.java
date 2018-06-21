package it.uniroma2.ticketingsystem.controller;

import it.uniroma2.ticketingsystem.logger.entity.Record;
import it.uniroma2.ticketingsystem.logger.RecordReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogController {

    @Autowired
    private RecordReader recordReader;

    public List<Record> prelevaLog() {
        return recordReader.getAllRecords();
    }
}
