package it.uniroma2.ticketingsystem.listener;

import it.uniroma2.ticketingsystem.aud.OggettoAudit;
import it.uniroma2.ticketingsystem.aud.TicketAudit;
import it.uniroma2.ticketingsystem.controller.OggettoAuditController;
import it.uniroma2.ticketingsystem.controller.TicketAuditController;
import it.uniroma2.ticketingsystem.entity.Oggetto;
import it.uniroma2.ticketingsystem.event.OggettoEvent;
import it.uniroma2.ticketingsystem.event.TicketEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.sql.Timestamp;

@Component
public class OggettoEventListener {
    @Autowired
    OggettoAuditController oggettoAuditController;

    @EventListener
    public void handleOggettoEvent(OggettoEvent oggettoEvent){

        switch (oggettoEvent.getToken()){
            case 0:
                //registra inserimento
                //prendi ultima istanza di OggettoAudit legato a ticket.oggettoID

                break;
            case 1:
                //registra cancellazione

                break;
            case 2:
                //registra modifica

                break;
        }
    }

}
