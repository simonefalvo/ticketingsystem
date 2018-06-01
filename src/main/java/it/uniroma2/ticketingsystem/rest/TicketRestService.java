package it.uniroma2.ticketingsystem.rest;

import it.uniroma2.ticketingsystem.controller.TicketController;
import it.uniroma2.ticketingsystem.entity.Ticket;
import it.uniroma2.ticketingsystem.event.TicketEvent;
import it.uniroma2.ticketingsystem.exception.EntitaNonTrovataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping(path = "ticket")
public class TicketRestService {

        @Autowired
        private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private TicketController ticketController;

    @RequestMapping(path = "", method = RequestMethod.POST)
    public ResponseEntity<Ticket> creaTicket(@RequestBody Ticket ticket) {

//        ticket.setTimestamp(new Timestamp(System.currentTimeMillis()));

        Ticket ticketCreato = ticketController.creaTicket(ticket);

        System.out.println("\n\n\n Crea Ticket: dato arrivato dal web: "+ticket.toString());
        System.out.println("\n\n\n Crea Ticket: dato arrivato dal controller: "+ticketCreato.toString());

        TicketEvent ticketEvent = new TicketEvent(this,ticket,0);
        applicationEventPublisher.publishEvent(ticketEvent);

        return new ResponseEntity<>(ticketCreato, HttpStatus.CREATED);
    }

    @RequestMapping(path = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> eliminaTicket(@PathVariable Integer id) {

        //Creo l'evento ticket prima di eliminare il ticket stesso
        TicketEvent ticketEvent = new TicketEvent(this,ticketController.cercaTicketById(id),1);

        //scateno gli eventi in seguito all'eliminazione richiamando il TicketEventListener
        applicationEventPublisher.publishEvent(ticketEvent);

        //elimino il ticket
        boolean ticketEliminato = ticketController.eliminaTicket(id);

        return new ResponseEntity<>(ticketEliminato, ticketEliminato ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "{id}", method = RequestMethod.GET)
    public ResponseEntity<Ticket> cercaTicket(@PathVariable Integer id) {
        System.out.print("\n Sono in responseEntity");
        Ticket ticketTrovato = ticketController.cercaTicketById(id);
        return new ResponseEntity<>(ticketTrovato, ticketTrovato == null ? HttpStatus.NOT_FOUND : HttpStatus.CREATED);
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public ResponseEntity<List<Ticket>> prelevaTickets() {
        List<Ticket> ticket = ticketController.prelevaTickets();
        return new ResponseEntity<>(ticket, HttpStatus.OK);
    }

    @RequestMapping(path = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<Ticket> aggiornaTicket(@PathVariable Integer id, @RequestBody Ticket ticket) {
        Ticket ticketAggiornato;
        try {
            System.out.println("\n\n\n\n\n\n aggiornaTicket Ticket = "+ticket.toString());
            ticketAggiornato = ticketController.aggiornaTicket(id, ticket);
            //TODO: se nella prossima tabella teniamo tutti i dati del ticket allora devo archiviare il vecchio ticket (giusto?)
            TicketEvent ticketEvent = new TicketEvent(this,ticket,2);
            applicationEventPublisher.publishEvent(ticketEvent);

        } catch (EntitaNonTrovataException e) {
            return new ResponseEntity<>(ticket, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ticketAggiornato, HttpStatus.OK);
    }

}
