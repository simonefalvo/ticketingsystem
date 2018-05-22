package it.uniroma2.ticketingsystem.controller;

import it.uniroma2.ticketingsystem.dao.UtenteAuditDao;
import it.uniroma2.ticketingsystem.entity.UtenteAudit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

@Service
public class UtenteAuditController {

    @Autowired
    private UtenteAuditDao utenteAuditDao;

    @Transactional
    public @NotNull UtenteAudit creaUtenteAudit(@NotNull UtenteAudit utente) {
        UtenteAudit utenteAuditSalvato = utenteAuditDao.save(utente);
        return utenteAuditSalvato;
    }
}
