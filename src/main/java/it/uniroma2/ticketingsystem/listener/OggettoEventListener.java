package it.uniroma2.ticketingsystem.listener;

import it.uniroma2.ticketingsystem.aud.OggettoAudit;
import it.uniroma2.ticketingsystem.controller.OggettoAuditController;

import it.uniroma2.ticketingsystem.event.OggettoEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

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
                registraInserimentoOggetto(new OggettoAudit(oggettoEvent.getOggetto(),
                                            new Timestamp(System.currentTimeMillis()),0));

                break;
            case 1:
               // registra modifica
                registraInserimentoOggetto(new OggettoAudit(oggettoEvent.getOggetto(),
                        new Timestamp(System.currentTimeMillis()),1));


                break;
            case 2:
                //registra eliminazione

                registraInserimentoOggetto(new OggettoAudit(oggettoEvent.getOggetto(),
                        new Timestamp(System.currentTimeMillis()),2));



                break;
        }
    }
    private void registraInserimentoOggetto(OggettoAudit oggettoAudit ){
        oggettoAuditController.registraOggettoInsert(oggettoAudit);
    }

}
