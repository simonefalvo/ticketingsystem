package it.uniroma2.ticketingsystem.event;

import it.uniroma2.ticketingsystem.aud.OggettoAudit;
import it.uniroma2.ticketingsystem.controller.OggettoAuditController;
import it.uniroma2.ticketingsystem.entity.Oggetto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
@Getter
@Setter
@Configurable
public class OggettoEvent {
    @Autowired
    OggettoAuditController oggettoAuditController;

    private Oggetto oggetto;
    private int token;

    public OggettoEvent(Oggetto oggetto, int token) {
        this.oggetto = oggetto;
        this.token = token;
    }

}
