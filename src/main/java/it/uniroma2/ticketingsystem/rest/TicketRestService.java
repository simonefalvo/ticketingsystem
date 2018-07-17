package it.uniroma2.ticketingsystem.rest;

import it.uniroma2.ticketingsystem.controller.TicketController;
import it.uniroma2.ticketingsystem.controller.UtenteController;
import it.uniroma2.ticketingsystem.entity.Ruolo;
import it.uniroma2.ticketingsystem.entity.Ticket;
import it.uniroma2.ticketingsystem.entity.Utente;
import it.uniroma2.ticketingsystem.event.TicketEvent;
import it.uniroma2.ticketingsystem.exception.EntitaNonTrovataException;
import it.uniroma2.ticketingsystem.logger.entity.jpa.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "ticket")
public class TicketRestService {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;
    @Autowired
    private TicketController ticketController;
    @Autowired
    private UtenteController utenteController;


    @RequestMapping(path = "", method = RequestMethod.POST)
    public ResponseEntity<Ticket> creaTicket(@RequestBody Ticket ticket) {

//        ticket.setTimestamp(new Timestamp(System.currentTimeMillis()));
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Utente user = utenteController.cercaPerUsername(auth.getName());
        ticket.setAutore(user);
        Ticket ticketCreato = ticketController.creaTicket(ticket);

        System.out.println("\n\n\n Crea Ticket: dato arrivato dal web: "+ticket.toString());
        //System.out.println("\n\n\n Crea Ticket: dato arrivato dal controller: "+ticketCreato.toString());

        TicketEvent ticketEvent = new TicketEvent(this,ticket,0);
        applicationEventPublisher.publishEvent(ticketEvent);

        return new ResponseEntity<>(ticketCreato, HttpStatus.CREATED);
    }

    @RequestMapping(path = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> eliminaTicket(@PathVariable Integer id) {

        //Creo l'evento ticket prima di eliminare il ticket stesso
        TicketEvent ticketEvent = new TicketEvent(this,ticketController.cercaTicketById(id),2);

        //scateno gli eventi in seguito all'eliminazione richiamando il TicketEventListener
        applicationEventPublisher.publishEvent(ticketEvent);

        //elimino il ticket
        boolean ticketEliminato = ticketController.eliminaTicket(id);

        return new ResponseEntity<>(ticketEliminato, ticketEliminato ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "{id}", method = RequestMethod.GET)
    public ResponseEntity<Ticket> cercaTicket(@PathVariable Integer id) {
        Ticket ticketTrovato = ticketController.cercaTicketById(id);
        System.out.println("\n\n\n\n Class TicketRestService method cercaTicket var ticketTrovato = "+ticketTrovato.toString());

        ResponseEntity myResp = new ResponseEntity<>(ticketTrovato, ticketTrovato == null ? HttpStatus.NOT_FOUND : HttpStatus.CREATED);
        System.out.println("\n\n\n\n  Class TicketRestService method cercaTicket var myResp  = "+myResp.toString());


        return myResp;
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public ResponseEntity<List<Ticket>> prelevaTickets() {
        List<Ticket> ticket;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Utente user = utenteController.cercaPerUsername(auth.getName());
        if (user.getRuolo().getName().equals("ADMIN") || user.getRuolo().getName().equals("OPERATOR")){
            //get all tickets
            ticket = ticketController.prelevaTickets();
            System.out.println("\n\n\n TicketRestService prelevaTicket = "+ticket.toString());
        }else{
            //get only user tickets
            ticket = ticketController.prelevaTicketsByUser(user);
            System.out.println("\n\n\n TicketRestService prelevaTicket = "+ticket.toString());
        }

        return new ResponseEntity<>(ticket, HttpStatus.OK);
    }


    @RequestMapping(path = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<Ticket> aggiornaTicket(@PathVariable Integer id, @RequestBody Ticket ticket) {
        Ticket ticketAggiornato;
        try {
            System.out.println("\n\n\n\n\n\n aggiornaTicket Ticket = "+ticket.toString());
            ticketAggiornato = ticketController.aggiornaTicket(id, ticket);

            TicketEvent ticketEvent = new TicketEvent(this,ticket,1);
            applicationEventPublisher.publishEvent(ticketEvent);

        } catch (EntitaNonTrovataException e) {
            return new ResponseEntity<>(ticket, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ticketAggiornato, HttpStatus.OK);
    }

    @RequestMapping(path = "status/{status}", method = RequestMethod.GET)
    public ResponseEntity<Integer> NumberOfStatusTickets(@PathVariable String status) {
        Integer total = ticketController.numberOfStatusTickets(status);
        return new ResponseEntity<>(total, total == null ? HttpStatus.NOT_FOUND : HttpStatus.CREATED);
    }

    @RequestMapping(path = "log/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<Record>> prelevaRecordPerOggetto(@PathVariable Integer id) {
        List<Record> records = ticketController.ottieniLogRecordsById(id);
        return new ResponseEntity<>(records, HttpStatus.OK);
    }
}
