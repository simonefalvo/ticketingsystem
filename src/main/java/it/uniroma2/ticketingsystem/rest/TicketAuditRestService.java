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

    /*
    @RequestMapping(path = "", method = RequestMethod.POST)
    public ResponseEntity<TicketAudit> creaTicketAud(@RequestBody TicketAudit ticketAud) {
        TicketAudit ticketAudCreato = ticketAudController.creaTicketAud(ticketAud);
        return new ResponseEntity<>(ticketAudCreato, HttpStatus.CREATED);
    }

    @RequestMapping(path = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> eliminaTicketAud(@PathVariable Integer id) {
        boolean ticketAudEliminato = ticketAudController.eliminaTicketAud(id);
        return new ResponseEntity<>(ticketAudEliminato, ticketAudEliminato ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "{id}", method = RequestMethod.GET)
    public ResponseEntity<TicketAudit> cercaTicketAud(@PathVariable Integer id) {
        System.out.print("\n Sono in responseEntity");
        TicketAudit ticketAudTrovato = ticketAudController.cercaTicketAudById(id);
        return new ResponseEntity<>(ticketAudTrovato, ticketAudTrovato == null ? HttpStatus.NOT_FOUND : HttpStatus.CREATED);
    }
    */

    @RequestMapping(path = "", method = RequestMethod.GET)
    public ResponseEntity<List<TicketAudit>> prelevaTicketAuds() {
        List<TicketAudit> ticketAud = ticketAuditController.prelevaTicketAuds();
        return new ResponseEntity<>(ticketAud, HttpStatus.OK);
    }

    @RequestMapping(path = "status/{status}", method = RequestMethod.GET)
    public ResponseEntity<Integer> NumberOfStatusTickets(@PathVariable String status) {
        Integer total = ticketAuditController.numberOfStatusTickets(status);
        return new ResponseEntity<>(total, total == null ? HttpStatus.NOT_FOUND : HttpStatus.CREATED);
    }
/*
    @RequestMapping(path = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<TicketAudit> aggiornaTicketAud(@PathVariable Integer id, @RequestBody TicketAudit ticketAud) {
        TicketAudit ticketAudAggiornato;
        try {
            ticketAudAggiornato = ticketAudController.aggiornaTicketAud(id, ticketAud);
        } catch (EntitaNonTrovataException e) {
            return new ResponseEntity<>(ticketAud, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ticketAudAggiornato, HttpStatus.OK);
    }
    */

    /*
    @RequestMapping(path = "{status}", method = RequestMethod.GET)
    public ResponseEntity<Integer> NumberOfStatusTickets(@PathVariable String status) {
        Integer total = ticketAuditController.numberOfStatusTickets(status);
        return new ResponseEntity<>(total, total == null ? HttpStatus.NOT_FOUND : HttpStatus.CREATED);
    }
    */

    @RequestMapping(path = "{timestamp}", method = RequestMethod.GET)
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