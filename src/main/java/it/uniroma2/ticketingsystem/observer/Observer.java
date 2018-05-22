package it.uniroma2.ticketingsystem.observer;

import it.uniroma2.ticketingsystem.entity.Utente;
import it.uniroma2.ticketingsystem.entity.UtenteAudit;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

public abstract class Observer {

    protected Utente utente;

    public abstract void update(UtenteAudit utente);
}
