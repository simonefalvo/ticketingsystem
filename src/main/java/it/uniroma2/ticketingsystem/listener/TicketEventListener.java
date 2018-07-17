package it.uniroma2.ticketingsystem.listener;

import it.uniroma2.ticketingsystem.aud.OggettoAudit;
import it.uniroma2.ticketingsystem.aud.UtenteAudit;
import it.uniroma2.ticketingsystem.controller.OggettoAuditController;
import it.uniroma2.ticketingsystem.controller.TicketAuditController;
import it.uniroma2.ticketingsystem.controller.UtenteAuditController;
import it.uniroma2.ticketingsystem.controller.UtenteController;
import it.uniroma2.ticketingsystem.entity.Oggetto;
import it.uniroma2.ticketingsystem.aud.TicketAudit;
import it.uniroma2.ticketingsystem.entity.Utente;
import it.uniroma2.ticketingsystem.event.TicketEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class TicketEventListener {

    @Autowired
    TicketAuditController ticketAuditController;
    @Autowired
    OggettoAuditController oggettoAuditController;
    @Autowired
    UtenteAuditController utenteAuditController;
    @Autowired
    UtenteController utenteController;

    @EventListener
    public void handleTicketEvent(TicketEvent ticketEvent){

        OggettoAudit oggettoAudit;
        UtenteAudit utenteAudit;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        switch (ticketEvent.getToken()){
            case 0:
                //registra inserimento
                //prendi ultima istanza di OggettoAudit legato a ticket.oggettoID
                oggettoAudit = getLastOggettoAudit(ticketEvent.getTicket().getOggetto());
                utenteAudit = getLastUtenteAudit(utenteController.cercaPerUsername(auth.getName()));
                registraInserimentoTicket(new TicketAudit(
                                                        ticketEvent.getTicket(),
                                                        new Timestamp(System.currentTimeMillis()),
                                                        oggettoAudit,
                                                        utenteAudit,
                                                        0));
                break;
            case 1:

                //registra modifica
                //prendi ultima istanza di OggettoAudit legato a ticket.oggettoID
                oggettoAudit = getLastOggettoAudit(ticketEvent.getTicket().getOggetto());
                utenteAudit = getLastUtenteAudit(utenteController.cercaPerUsername(auth.getName()));
                registraModificaTicket(new TicketAudit(
                        ticketEvent.getTicket(),
                        new Timestamp(System.currentTimeMillis()),
                        oggettoAudit,
                        utenteAudit,
                        1));
               break;
            case 2:
                //registra cancellazione
                oggettoAudit = getLastOggettoAudit(ticketEvent.getTicket().getOggetto());
                utenteAudit = getLastUtenteAudit(utenteController.cercaPerUsername(auth.getName()));
                registraCancellazioneTicket(new TicketAudit(
                        ticketEvent.getTicket(),
                        new Timestamp(System.currentTimeMillis()),
                        oggettoAudit,
                        utenteAudit,
                        2));
                break;
        }
    }
    private OggettoAudit getLastOggettoAudit(Oggetto oggetto){
        OggettoAudit oggettoAudit = oggettoAuditController.getMostRecentOggettoAudit(oggetto);
        return oggettoAudit;
    }
    private UtenteAudit getLastUtenteAudit(Utente utente){
        UtenteAudit utenteAudit = utenteAuditController.getMostRecentUtenteAudit(utente);
        return utenteAudit;
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
