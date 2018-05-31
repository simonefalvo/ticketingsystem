package it.uniroma2.ticketingsystem.controller;

import it.uniroma2.ticketingsystem.aud.TicketAudit;
import it.uniroma2.ticketingsystem.dao.TicketAuditDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

@Service
public class TicketAuditController {

    @Autowired
    private TicketAuditDao ticketAuditDao;


    @Transactional
    public @NotNull TicketAudit creaTicketAud(@NotNull TicketAudit ticketAudit){

        TicketAudit ticketAuditSalvato = ticketAuditDao.save(ticketAudit);
        return ticketAuditSalvato;

    }
/*
    @Transactional
    public @NotNull TicketAudit aggiornaTicketAud(@NotNull Integer id, @NotNull TicketAudit datiAggiornati) throws EntitaNonTrovataException {
        TicketAudit ticketAudDaAggiornare = ticketAuditDao.getOne(id);
        if (ticketAudDaAggiornare == null)
            throw new EntitaNonTrovataException();

        ticketAudDaAggiornare.aggiorna(datiAggiornati);

        TicketAudit ticketAudAggiornato = ticketAuditDao.save(ticketAudDaAggiornare);
        return ticketAudAggiornato;
    }

    public boolean eliminaTicketAud(@NotNull Integer id){
        if(!ticketAuditDao.existsById(id)){
            return false;
        }

        ticketAuditDao.deleteById(id);
        return true;
    }

    public TicketAudit cercaTicketAudById(@NotNull Integer id){
        TicketAudit ticketAudTrovato = ticketAuditDao.getOne(id);
        return ticketAudTrovato;
    }

    public List<TicketAudit> prelevaTicketAuds() {
        return ticketAuditDao.findAll();
    }
    */

    public Integer numberOfStatusTickets(String status){
        return ticketAuditDao.numberOfStatusTickets(status);
    }

}
