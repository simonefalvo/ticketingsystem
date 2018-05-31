package it.uniroma2.ticketingsystem.listener;

import it.uniroma2.ticketingsystem.controller.TicketAuditController;
import it.uniroma2.ticketingsystem.entity.Ticket;
import it.uniroma2.ticketingsystem.entity.TicketAudit;
import it.uniroma2.ticketingsystem.event.TicketEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class TicketEventListener {

    @Autowired
    TicketAuditController ticketAuditController;

    @EventListener
    public void handleTicketEvent(TicketEvent ticketEvent){

        switch (ticketEvent.getToken()){
            case 0:
                //registra inserimento
                //ticketEvent.registerTicketInsert();
                registraInserimentoTicket(new TicketAudit(
                                                        ticketEvent.getTicket().getId(),
                                                        new Timestamp(System.currentTimeMillis()),
                                                        0));
                break;
            case 1:
                //registra cancellazione
                registraCancellazioneTicket(new TicketAudit(
                                                        ticketEvent.getTicket().getId(),
                                                        new Timestamp(System.currentTimeMillis()),
                                                        1));
                break;
            case 2:
                //registra modifica
                registraModificaTicket(new TicketAudit(
                                                    ticketEvent.getTicket().getId(),
                                                    new Timestamp(System.currentTimeMillis()),
                                                    2));
                break;
        }
    }

    private void registraInserimentoTicket(TicketAudit ticketAudit){
        ticketAuditController.registraTicketInsert(ticketAudit);
    }

    private void registraCancellazioneTicket(TicketAudit ticketAudit){
        ticketAuditController.registraTicketDelete(ticketAudit);
    }
    private void registraModificaTicket(TicketAudit ticketAudit){
        ticketAuditController.registraTicketEdit(ticketAudit);
    }

}
