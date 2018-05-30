package it.uniroma2.ticketingsystem.rest;

import it.uniroma2.ticketingsystem.controller.TicketAudController;
import it.uniroma2.ticketingsystem.aud.TicketAud;
import it.uniroma2.ticketingsystem.exception.EntitaNonTrovataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "ticketaud")
public class TicketAudRestService {


    @Autowired
    private TicketAudController ticketAudController;

    /*
    @RequestMapping(path = "", method = RequestMethod.POST)
    public ResponseEntity<TicketAud> creaTicketAud(@RequestBody TicketAud ticketAud) {
        TicketAud ticketAudCreato = ticketAudController.creaTicketAud(ticketAud);
        return new ResponseEntity<>(ticketAudCreato, HttpStatus.CREATED);
    }

    @RequestMapping(path = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> eliminaTicketAud(@PathVariable Integer id) {
        boolean ticketAudEliminato = ticketAudController.eliminaTicketAud(id);
        return new ResponseEntity<>(ticketAudEliminato, ticketAudEliminato ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "{id}", method = RequestMethod.GET)
    public ResponseEntity<TicketAud> cercaTicketAud(@PathVariable Integer id) {
        System.out.print("\n Sono in responseEntity");
        TicketAud ticketAudTrovato = ticketAudController.cercaTicketAudById(id);
        return new ResponseEntity<>(ticketAudTrovato, ticketAudTrovato == null ? HttpStatus.NOT_FOUND : HttpStatus.CREATED);
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public ResponseEntity<List<TicketAud>> prelevaTicketAuds() {
        List<TicketAud> ticketAud = ticketAudController.prelevaTicketAuds();
        return new ResponseEntity<>(ticketAud, HttpStatus.OK);
    }

    @RequestMapping(path = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<TicketAud> aggiornaTicketAud(@PathVariable Integer id, @RequestBody TicketAud ticketAud) {
        TicketAud ticketAudAggiornato;
        try {
            ticketAudAggiornato = ticketAudController.aggiornaTicketAud(id, ticketAud);
        } catch (EntitaNonTrovataException e) {
            return new ResponseEntity<>(ticketAud, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ticketAudAggiornato, HttpStatus.OK);
    }
    */

    @RequestMapping(path = "", method = RequestMethod.GET)
    public ResponseEntity<Integer> cercaTicketAud() {
        Integer total = ticketAudController.numberOfOpenTickets();
        return new ResponseEntity<>(total, total == null ? HttpStatus.NOT_FOUND : HttpStatus.CREATED);
    }

}
