package it.uniroma2.ticketingsystem.controller;

import it.uniroma2.ticketingsystem.dao.TicketAuditDao;
import it.uniroma2.ticketingsystem.entity.TicketAudit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

@Service
public class TicketAuditController {

    @Autowired
    private TicketAuditDao ticketAuditDao;

    @Transactional
    public void registraTicketInsert(TicketAudit ticketAudit){
        ticketAuditDao.save(ticketAudit);
    }
}
