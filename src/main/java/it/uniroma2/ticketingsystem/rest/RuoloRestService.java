package it.uniroma2.ticketingsystem.rest;

import it.uniroma2.ticketingsystem.controller.RuoloController;
import it.uniroma2.ticketingsystem.entity.Ruolo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "ruolo")
public class RuoloRestService {

    @Autowired
    private RuoloController ruoloController;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public ResponseEntity<List<Ruolo>> prelevaRuoli() {
        List<Ruolo> ruoli = ruoloController.prelevaRuoli();
        return new ResponseEntity<>(ruoli, HttpStatus.OK);
    }

}
