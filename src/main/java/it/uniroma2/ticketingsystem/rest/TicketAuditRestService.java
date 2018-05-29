package it.uniroma2.ticketingsystem.rest;


import it.uniroma2.ticketingsystem.controller.TicketAuditController;
import it.uniroma2.ticketingsystem.controller.TicketController;
import it.uniroma2.ticketingsystem.entity.Ticket;
import it.uniroma2.ticketingsystem.entity.TicketAudit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;

@RestController
@Service
public class TicketAuditRestService {

    @Autowired
    private TicketAuditController ticketAuditController;

    public void registerTicketInsert (Ticket ticket) {
        ticketAuditController.registraTicketInsert(new TicketAudit(ticket.getId(),new Timestamp(System.currentTimeMillis()),
                0));
    }
}
