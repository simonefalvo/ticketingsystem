package it.uniroma2.ticketingsystem.controller;

import it.uniroma2.ticketingsystem.aud.UtenteAudit;
import it.uniroma2.ticketingsystem.dao.UtenteAuditDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

@Service
public class UtenteAuditController {

    @Autowired
    private UtenteAuditDao uad;

    @Transactional
    public @NotNull UtenteAudit creaUtenteAud(@NotNull UtenteAudit utenteAudit){

        UtenteAudit utenteAuditSalvato = uad.save(utenteAudit);
        return utenteAuditSalvato;

    }

    public boolean eliminaUtenteAudit(@NotNull Integer id){
        if(!uad.existsById(id)){
            return false;
        }

        uad.deleteById(id);
        return true;
    }
}
