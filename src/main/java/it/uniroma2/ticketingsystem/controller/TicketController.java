package it.uniroma2.ticketingsystem.controller;

import it.uniroma2.ticketingsystem.dao.TicketDao;
import it.uniroma2.ticketingsystem.entity.Ticket;
import it.uniroma2.ticketingsystem.exception.EntitaNonTrovataException;
import it.uniroma2.ticketingsystem.entity.Utente;
import it.uniroma2.ticketingsystem.event.TicketEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class TicketController {

    @Autowired
    private TicketDao ticketDao;

    @Transactional
    public @NotNull Ticket creaTicket(@NotNull Ticket ticket){

        Ticket ticketSalvato = ticketDao.save(ticket);
        return ticketSalvato;

    }

    @Transactional
    public @NotNull Ticket aggiornaTicket(@NotNull Integer id, @NotNull Ticket datiAggiornati) throws EntitaNonTrovataException {
        Ticket ticketDaAggiornare = ticketDao.getOne(id);
        if (ticketDaAggiornare == null)
            throw new EntitaNonTrovataException();

        ticketDaAggiornare.aggiorna(datiAggiornati);

        Ticket ticketAggiornato = ticketDao.save(ticketDaAggiornare);
        return ticketAggiornato;
    }

    public boolean eliminaTicket(@NotNull Integer id){
        if(!ticketDao.existsById(id)){
            return false;
        }

        ticketDao.deleteById(id);
        return true;
    }

    public Ticket cercaTicketById(@NotNull Integer id){
        Ticket ticketTrovato = ticketDao.getOne(id);
        return ticketTrovato;
    }

    public List<Ticket> prelevaTickets() {
        return ticketDao.findAll();
    }

}
