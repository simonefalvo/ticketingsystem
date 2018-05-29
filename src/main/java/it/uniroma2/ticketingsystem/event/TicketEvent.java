package it.uniroma2.ticketingsystem.event;

import it.uniroma2.ticketingsystem.controller.TicketAuditController;
import it.uniroma2.ticketingsystem.entity.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.ApplicationEvent;
/*
    Classe degli eventi relativi a ticket

 */

@Configurable
public class TicketEvent extends ApplicationEvent {

    @Autowired
    TicketAuditController ticketAuditController;

    private Ticket ticket;
    private int token;

    public TicketEvent(Object source, Ticket ticket, int token){
        super(source);
        this.ticket = ticket;
        this.token = token;
    }

    public int getToken(){
        return this.token;
    }

    public Ticket getTicket(){
        return this.ticket;
    }

}
