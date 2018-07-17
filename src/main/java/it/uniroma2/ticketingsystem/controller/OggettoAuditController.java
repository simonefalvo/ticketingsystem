package it.uniroma2.ticketingsystem.controller;

import it.uniroma2.ticketingsystem.aud.OggettoAudit;
import it.uniroma2.ticketingsystem.dao.OggettoAuditDao;
import it.uniroma2.ticketingsystem.entity.Oggetto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

@Service
public class OggettoAuditController {

    @Autowired
    private OggettoAuditDao oggettoAuditDao;

    @Transactional
    public @NotNull OggettoAudit creaOggettoAudit(@NotNull OggettoAudit oggettoAudit){

        OggettoAudit oggettoAuditSalvato = oggettoAuditDao.save(oggettoAudit);
        return oggettoAuditSalvato;

    }
    @Transactional
    public void registraOggettoInsert(OggettoAudit oggettoAudit){
        oggettoAuditDao.save(oggettoAudit);
    }
    
    
    @Transactional
    public @NotNull OggettoAudit getMostRecentOggettoAudit(@NotNull Oggetto oggetto){
        System.out.println("\n\n\n\n getMostRecentOggettoAudit oggetto ID = "+oggetto.getId());
        Integer audit_id = oggettoAuditDao.getIdOfMostRecentOggettoAuditByOggetto(oggetto.getId());
        System.out.println("\n audit_id = "+audit_id);
        OggettoAudit oggettoAuditMostRecent = oggettoAuditDao.getOne(audit_id.intValue());
        if(oggettoAuditMostRecent == null)
            System.out.println("\n\n\n\n\n\n\n oggettoAuditMostRecent is null \n\n\n\n");
        //OggettoAudit oggettoAuditMostRecent = oggettoAuditDao.getMostRecentOggettoAudit(oggetto.getId());
        return oggettoAuditMostRecent;
    }
    @Transactional
    public boolean eliminaOggettoAudit(@NotNull Integer id){
        if(!oggettoAuditDao.existsById(id)){
            return false;
        }

        oggettoAuditDao.deleteById(id);
        return true;
    }
}
