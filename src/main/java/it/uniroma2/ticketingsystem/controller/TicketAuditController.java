package it.uniroma2.ticketingsystem.controller;

import it.uniroma2.ticketingsystem.dao.TicketAuditDao;
import it.uniroma2.ticketingsystem.aud.TicketAudit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;


@Service
@Repository
public class TicketAuditController {

    @Autowired
    private TicketAuditDao ticketAuditDao;

    @Transactional
    public @NotNull TicketAudit creaTicketAud(@NotNull TicketAudit ticketAudit){

        TicketAudit ticketAuditSalvato = ticketAuditDao.save(ticketAudit);
        return ticketAuditSalvato;

    }



    @Transactional
    public void registraTicketInsert(TicketAudit ticketAudit){
        ticketAuditDao.save(ticketAudit);
    }
    @Transactional
    public void registraTicketDelete(TicketAudit ticketAudit){
        ticketAuditDao.save(ticketAudit);
    }
    @Transactional
    public void registraTicketEdit(TicketAudit ticketAudit){
        ticketAuditDao.save(ticketAudit);
    }

    public Integer numberOfOpenTickets(){
        return ticketAuditDao.numberOfOpenTickets();
    }
}
