package it.uniroma2.ticketingsystem.controller;

import it.uniroma2.ticketingsystem.dao.RuoloDao;
import it.uniroma2.ticketingsystem.entity.Ruolo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class RuoloController {

    @Autowired
    private RuoloDao ruoloDao;

    @Transactional
    public @NotNull Ruolo creaRuolo(@NotNull Ruolo ruolo) {
        Ruolo ruoloSalvato = ruoloDao.save(ruolo);
        return ruoloSalvato;
    }

    public List<Ruolo> prelevaRuoli() {
        return ruoloDao.findAll();
    }

    public Ruolo cercaPerNome (String nome){
        return ruoloDao.findByName(nome);
    }
}
