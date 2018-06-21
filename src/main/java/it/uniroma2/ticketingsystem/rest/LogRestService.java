package it.uniroma2.ticketingsystem.rest;

import it.uniroma2.ticketingsystem.controller.LogController;
import it.uniroma2.ticketingsystem.logger.entity.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "log")
public class LogRestService {
    
    @Autowired
    private LogController logController;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public ResponseEntity<List<Record>> prelevaLog() {
        List<Record> records = logController.prelevaLog();
        return new ResponseEntity<>(records, HttpStatus.OK);
    }

    @RequestMapping(path = "tag/{tag}", method = RequestMethod.GET)
    public ResponseEntity<List<Record>> prelevaRecordPerTag(@PathVariable String tag) {
        List<Record> records = logController.cercaRecordPerTag(tag);
        return new ResponseEntity<>(records, HttpStatus.OK);
    }

    @RequestMapping(path = "author/{author}", method = RequestMethod.GET)
    public ResponseEntity<List<Record>> prelevaRecordPerAutore(@PathVariable String author) {
        List<Record> records = logController.cercaRecordPerAutore(author);
        return new ResponseEntity<>(records, HttpStatus.OK);
    }


}
