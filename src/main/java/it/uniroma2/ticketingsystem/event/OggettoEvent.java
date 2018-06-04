package it.uniroma2.ticketingsystem.event;

import it.uniroma2.ticketingsystem.controller.OggettoAuditController;
import it.uniroma2.ticketingsystem.entity.Oggetto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.ApplicationEvent;

@Configurable
public class OggettoEvent extends ApplicationEvent {
    
    @Autowired
    OggettoAuditController oggettoAuditController;

    private Oggetto oggetto;
    private int token;

    public OggettoEvent(Object source, Oggetto oggetto, int token) {
        super(source);
        this.oggetto = oggetto;
        this.token = token;
    }

    public int getToken() {
        return token;
    }

    public Oggetto getOggetto() {
        return oggetto;
    }
}
