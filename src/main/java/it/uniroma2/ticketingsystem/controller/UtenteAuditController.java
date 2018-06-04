package it.uniroma2.ticketingsystem.controller;

import it.uniroma2.ticketingsystem.aud.UtenteAudit;
import it.uniroma2.ticketingsystem.dao.UtenteAuditDao;
import it.uniroma2.ticketingsystem.entity.Utente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

@Service
public class UtenteAuditController {

    @Autowired
    private UtenteAuditDao utenteAuditDao;

    @Transactional
    public @NotNull UtenteAudit creaUtenteAud(@NotNull UtenteAudit utenteAudit){

        UtenteAudit utenteAuditSalvato = utenteAuditDao.save(utenteAudit);
        return utenteAuditSalvato;

    }

    public boolean eliminaUtenteAudit(@NotNull Integer id){
        if(!utenteAuditDao.existsById(id)){
            return false;
        }

        utenteAuditDao.deleteById(id);
        return true;
    }

    @Transactional
    public void registraUtente(UtenteAudit utenteAudit){
        utenteAuditDao.save(utenteAudit);
    }
}
