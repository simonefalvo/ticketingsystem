package it.uniroma2.ticketingsystem.listener;

import it.uniroma2.ticketingsystem.aud.TicketAudit;
import it.uniroma2.ticketingsystem.aud.UtenteAudit;
import it.uniroma2.ticketingsystem.controller.UtenteAuditController;
import it.uniroma2.ticketingsystem.event.UtenteEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class UtenteEventListener {

    @Autowired
    UtenteAuditController utenteAuditController;

    @EventListener
    public void handleUtenteEvent(UtenteEvent utenteEvent){

        switch (utenteEvent.getToken()){
            case 0:
                //registra inserimento
                //prendi ultima istanza di OggettoAudit legato a ticket.oggettoID
                registraInserimentoUtente(new UtenteAudit(utenteEvent.getUtente(), new Timestamp(System.currentTimeMillis()),0));
                break;
            case 1:

                //registra modifica
                //prendi ultima istanza di OggettoAudit legato a ticket.oggettoID
                registraInserimentoUtente(new UtenteAudit(utenteEvent.getUtente(), new Timestamp(System.currentTimeMillis()),1));

                break;
            case 2:
                //registra cancellazione
                registraInserimentoUtente(new UtenteAudit(utenteEvent.getUtente(), new Timestamp(System.currentTimeMillis()),2));

                break;
        }
    }

    private void registraInserimentoUtente(UtenteAudit utenteAudit){
        utenteAuditController.registraUtente(utenteAudit);
    }


}
