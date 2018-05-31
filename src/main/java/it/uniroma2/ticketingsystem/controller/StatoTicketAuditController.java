package it.uniroma2.ticketingsystem.controller;

import it.uniroma2.ticketingsystem.dao.StatoTicketAuditDao;
import it.uniroma2.ticketingsystem.aud.StatoTicketAudit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

@Service
public class StatoTicketAuditController {

    @Autowired
    private StatoTicketAuditDao stDao;

    @Transactional
    public @NotNull StatoTicketAudit creaStatoTicketAudit(@NotNull StatoTicketAudit sta) {
        StatoTicketAudit staSalvato = stDao.save(sta);
        return staSalvato;
    }

    public boolean eliminaStatoTicketAudit(@NotNull Integer id){
        if(!stDao.existsById(id)){
            return false;
        }

        stDao.deleteById(id);
        return true;
    }



}
