package it.uniroma2.ticketingsystem.controller;

import it.uniroma2.ticketingsystem.dao.TicketAuditDao;
import it.uniroma2.ticketingsystem.entity.TicketAudit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@Repository
public class TicketAuditController {

    @Autowired
    private TicketAuditDao ticketAuditDao;

    @Transactional
    public void registraTicketInsert(TicketAudit ticketAudit){
        ticketAuditDao.save(ticketAudit);
    }
}
