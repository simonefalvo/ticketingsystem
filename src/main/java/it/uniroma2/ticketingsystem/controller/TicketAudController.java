package it.uniroma2.ticketingsystem.controller;

import it.uniroma2.ticketingsystem.aud.TicketAud;
import it.uniroma2.ticketingsystem.dao.TicketAudDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class TicketAudController {

    @Autowired
    private TicketAudDao ticketAudDao;

    /*
    @Transactional
    public @NotNull TicketAud creaTicketAud(@NotNull TicketAud ticketAud){

        TicketAud ticketAudSalvato = ticketAudDao.save(ticketAud);
        return ticketAudSalvato;

    }

    @Transactional
    public @NotNull TicketAud aggiornaTicketAud(@NotNull Integer id, @NotNull TicketAud datiAggiornati) throws EntitaNonTrovataException {
        TicketAud ticketAudDaAggiornare = ticketAudDao.getOne(id);
        if (ticketAudDaAggiornare == null)
            throw new EntitaNonTrovataException();

        ticketAudDaAggiornare.aggiorna(datiAggiornati);

        TicketAud ticketAudAggiornato = ticketAudDao.save(ticketAudDaAggiornare);
        return ticketAudAggiornato;
    }

    public boolean eliminaTicketAud(@NotNull Integer id){
        if(!ticketAudDao.existsById(id)){
            return false;
        }

        ticketAudDao.deleteById(id);
        return true;
    }

    public TicketAud cercaTicketAudById(@NotNull Integer id){
        TicketAud ticketAudTrovato = ticketAudDao.getOne(id);
        return ticketAudTrovato;
    }

    public List<TicketAud> prelevaTicketAuds() {
        return ticketAudDao.findAll();
    }
    */

        public Integer numberOfOpenTickets(){
            return ticketAudDao.numberOfOpenTickets();
        }

}
