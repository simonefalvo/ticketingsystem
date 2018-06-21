package it.uniroma2.ticketingsystem.controller;

import it.uniroma2.ticketingsystem.dao.TicketDao;
import it.uniroma2.ticketingsystem.entity.Ticket;
import it.uniroma2.ticketingsystem.exception.EntitaNonTrovataException;
import it.uniroma2.ticketingsystem.logger.aspect.LogOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class TicketController {

    @Autowired
    private TicketDao ticketDao;

    @Transactional
    @LogOperation(inputArgs = "ticket")
    public @NotNull Ticket creaTicket(@NotNull Ticket ticket){
        return ticketDao.save(ticket);
    }

    @Transactional
    @LogOperation(inputArgs ={"id", "datiAggiornati"}, returnObject = true, tag = "prova")
    public @NotNull Ticket aggiornaTicket(@NotNull Integer id, @NotNull Ticket datiAggiornati) throws EntitaNonTrovataException {
        Ticket ticketDaAggiornare = ticketDao.getOne(id);
        if (ticketDaAggiornare == null)
            throw new EntitaNonTrovataException();

        ticketDaAggiornare.aggiorna(datiAggiornati);

        return ticketDao.save(ticketDaAggiornare);

    }

    public boolean eliminaTicket(@NotNull Integer id){
        if(!ticketDao.existsById(id)){
            return false;
        }

        ticketDao.deleteById(id);
        return true;
    }

    public Ticket cercaTicketById(@NotNull Integer id){
        return ticketDao.getOne(id);
    }

    public List<Ticket> prelevaTickets() {
        return ticketDao.findAll();
    }

    public Integer numberOfStatusTickets(String status) {
        return ticketDao.numberOfStatusTickets(status);
    }
}
