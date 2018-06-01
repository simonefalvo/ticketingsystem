package it.uniroma2.ticketingsystem.event;

import it.uniroma2.ticketingsystem.aud.OggettoAudit;
import it.uniroma2.ticketingsystem.controller.OggettoAuditController;
import it.uniroma2.ticketingsystem.entity.Oggetto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
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

}
