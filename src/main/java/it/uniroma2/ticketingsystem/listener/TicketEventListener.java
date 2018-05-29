package it.uniroma2.ticketingsystem.listener;

import it.uniroma2.ticketingsystem.event.TicketEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class TicketEventListener {

    @EventListener
    public void handleTicketEvent(TicketEvent ticketEvent){

        switch (ticketEvent.getToken()){
            case 0:
                //registra inserimento
                ticketEvent.registerTicketInsert();
                break;
            case 1:
                //registra cancellazione
                ticketEvent.registerTicketDelete();
                break;
            case 2:
                //registra modifica
                ticketEvent.registerTicketMod();
                break;
        }
    }
}
