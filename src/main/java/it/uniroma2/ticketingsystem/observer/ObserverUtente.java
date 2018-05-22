package it.uniroma2.ticketingsystem.observer;

import it.uniroma2.ticketingsystem.dao.UtenteAuditDao;
import it.uniroma2.ticketingsystem.entity.Utente;
import it.uniroma2.ticketingsystem.entity.UtenteAudit;

import javax.validation.constraints.NotNull;


public class ObserverUtente extends Observer {

    public ObserverUtente(Utente obj){
        this.utente = obj;
        this.utente.setObserver(this);
    }

    private UtenteAuditDao utenteAuditDao;

    @Override
    public void update(@NotNull UtenteAudit ua) {

        System.out.println("lalalalalalalalalalalal due " + ua.toString());



     /*   System.out.println("xoxo gossip girl " + utenteAuditDao.toString());

        UtenteAudit uaSalvato = utenteAuditDao.save(ua);
*/

    }
}
