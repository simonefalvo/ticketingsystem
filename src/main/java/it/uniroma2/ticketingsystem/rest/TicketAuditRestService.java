package it.uniroma2.ticketingsystem.rest;

import it.uniroma2.ticketingsystem.aud.TicketAudit;
import it.uniroma2.ticketingsystem.controller.TicketAuditController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "ticketaudit")
public class TicketAuditRestService {


    @Autowired
    private TicketAuditController ticketAuditController;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public ResponseEntity<List<TicketAudit>> prelevaTicketAuds() {
        List<TicketAudit> ticketAud = ticketAuditController.prelevaTicketAuds();
        return new ResponseEntity<>(ticketAud, HttpStatus.OK);
    }

    @RequestMapping(path = "{id}", method = RequestMethod.GET)
    public ResponseEntity<List<TicketAudit>> getTicketAudsByTicketId(@PathVariable Integer id) {
        List<TicketAudit> ticketAud = ticketAuditController.getTicketAudsByTicketId(id);
        return new ResponseEntity<>(ticketAud, HttpStatus.OK);
    }

    @RequestMapping(path = "status/{status}", method = RequestMethod.GET)
    public ResponseEntity<Integer> NumberOfStatusTickets(@PathVariable String status) {
        Integer total = ticketAuditController.numberOfStatusTickets(status);
        return new ResponseEntity<>(total, total == null ? HttpStatus.NOT_FOUND : HttpStatus.CREATED);
    }

    @RequestMapping(path = "timestamp/{timestamp}", method = RequestMethod.GET)
    public ResponseEntity<Integer> DailyTickets(@PathVariable String timestamp) {
        Integer total=0;
        try {
            total = ticketAuditController.dailyTickets(timestamp);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(total, total == null ? HttpStatus.NOT_FOUND : HttpStatus.CREATED);
    }
}