package it.uniroma2.ticketingsystem.rest;

import it.uniroma2.ticketingsystem.aud.StatoTicketAudit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import it.uniroma2.ticketingsystem.controller.StatoTicketAuditController;

@RestController
@RequestMapping(path = "statoticketaudit")
public class StatoTicketRestService {

    @Autowired
    private StatoTicketAuditController stController;

    @RequestMapping(path = "", method = RequestMethod.POST)
    public ResponseEntity<StatoTicketAudit> creaOggetto(@RequestBody StatoTicketAudit sta) {
        StatoTicketAudit staCreato = stController.creaStatoTicketAudit(sta);
        return new ResponseEntity<>(staCreato, HttpStatus.CREATED);
    }

    @RequestMapping(path = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> eliminaStatoTicketAudit(@PathVariable Integer id) {
        boolean staEliminato = stController.eliminaStatoTicketAudit(id);
        return new ResponseEntity<>(staEliminato, staEliminato ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }


}
