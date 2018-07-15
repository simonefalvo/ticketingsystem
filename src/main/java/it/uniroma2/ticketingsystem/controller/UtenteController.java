package it.uniroma2.ticketingsystem.controller;

import it.uniroma2.ticketingsystem.dao.UtenteDao;
import it.uniroma2.ticketingsystem.entity.Utente;
import it.uniroma2.ticketingsystem.event.UtenteEvent;
import it.uniroma2.ticketingsystem.logger.aspect.LogOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import it.uniroma2.ticketingsystem.exception.EntitaNonTrovataException;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.List;

// @Service identifica uno Spring Bean che nell'architettura MVC è un Controller
@Service
public class UtenteController {

    @Autowired
    private UtenteDao utenteDao;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Transactional
    @LogOperation
    public @NotNull Utente creaUtente(@NotNull Utente utente) {
        utente.setPassword(getPasswordEncoder().encode(utente.getPassword()));
        Utente utenteSalvato = utenteDao.save(utente);

        UtenteEvent utenteEvent =  new UtenteEvent(this,utenteSalvato,0);
        applicationEventPublisher.publishEvent(utenteEvent);
        System.out.print("aiuto");

        return utenteSalvato;

    }

    @LogOperation(inputArgs = "datiAggiornati")
    @Transactional
    public @NotNull Utente aggiornaUtente(@NotNull Integer id, @NotNull Utente datiAggiornati) throws EntitaNonTrovataException {
        Utente utenteDaAggiornare = utenteDao.getOne(id);
        if (utenteDaAggiornare == null)
            throw new EntitaNonTrovataException();

        utenteDaAggiornare.aggiorna(datiAggiornati);

        Utente utenteAggiornato = utenteDao.save(utenteDaAggiornare);
        return utenteAggiornato;
    }

    public Utente cercaUtentePerId(@NotNull Integer id) {
        Utente utenteTrovato = utenteDao.getOne(id);
        return utenteTrovato;
    }

    @LogOperation
    public boolean eliminaUtente(@NotNull Integer id) {
        if (!utenteDao.existsById(id)) {
            return false;
        }

        utenteDao.deleteById(id);
        return true;
    }

    public List<Utente> prelevaUtenti() {
        return utenteDao.findAll();
    }

    public Utente cercaPerUsername(String username){
        return utenteDao.findByUsername(username);
    }

    public Utente cercaPerEmail(String email) {return utenteDao.findByEmail(email);}

    /*
    public Utente cercaUtentePerEmail(@NotNull String email) {
        return utenteDao.findByEmailAddress(email);
    }
    */
}
