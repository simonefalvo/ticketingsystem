package it.uniroma2.ticketingsystem.event;

import it.uniroma2.ticketingsystem.entity.Ticket;
import it.uniroma2.ticketingsystem.rest.TicketAuditRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

@Configurable
public class TicketEvent extends ApplicationEvent {

    @Autowired
    TicketAuditRestService ticketAuditRestService;

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

    public void registerTicketInsert(){
        if(ticketAuditRestService == null)
            System.out.println("\n\n\n\n ticketAuditRestService è null");
        ticketAuditRestService.registerTicketInsert(this.ticket);
    }

    public void registerTicketDelete(){
        //TODO
    }

    public void registerTicketMod(){
        //TODO
    }
}
