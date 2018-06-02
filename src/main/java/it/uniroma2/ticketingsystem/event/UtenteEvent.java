package it.uniroma2.ticketingsystem.event;

import it.uniroma2.ticketingsystem.controller.UtenteAuditController;
import it.uniroma2.ticketingsystem.entity.Utente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.ApplicationEvent;

/*
    Classe degli eventi relativi a utente

 */

@Configurable
public class UtenteEvent extends ApplicationEvent {

    @Autowired
    UtenteAuditController utenteAuditController;

    private Utente utente;
    private int token;

    public UtenteEvent(Object source, Utente utente, int token){
        super(source);
        this.utente = utente;
        this.token = token;

    }

    public int getToken() {
        return token;
    }

    public Utente getUtente() {
        return utente;
    }
}
