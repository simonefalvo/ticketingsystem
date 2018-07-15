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

    @Transactional
    public @NotNull UtenteAudit getMostRecentUtenteAudit(@NotNull Utente utente){
        System.out.println("\n\n\n\n getMostRecentUtenteAudit utente ID = "+utente.getId());
        Integer audit_id = utenteAuditDao.getIdOfMostRecentUtenteAuditByUtente(utente.getId());
        System.out.println("\n audit_id = "+audit_id);
        UtenteAudit utenteAuditMostRecent = utenteAuditDao.getOne(audit_id.intValue());
        if(utenteAuditMostRecent == null)
            System.out.println("\n\n\n\n\n\n\n oggettoAuditMostRecent is null \n\n\n\n");
        //OggettoAudit oggettoAuditMostRecent = oggettoAuditDao.getMostRecentOggettoAudit(oggetto.getId());
        return utenteAuditMostRecent;
    }
}
