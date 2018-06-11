package it.uniroma2.ticketingsystem.controller;

import it.uniroma2.ticketingsystem.dao.RuoloDao;
import it.uniroma2.ticketingsystem.entity.Ruolo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RuoloController {

    @Autowired
    private RuoloDao ruoloDao;

    public List<Ruolo> prelevaRuoli() {
        return ruoloDao.findAll();
    }

}
