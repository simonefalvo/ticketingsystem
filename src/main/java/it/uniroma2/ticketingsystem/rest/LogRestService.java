package it.uniroma2.ticketingsystem.rest;

import it.uniroma2.ticketingsystem.controller.LogController;
import it.uniroma2.ticketingsystem.logger.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Record>> prelevaOggetti() {
        List<Record> Record = logController.prelevaLog();
        return new ResponseEntity<>(Record, HttpStatus.OK);
    }
}
