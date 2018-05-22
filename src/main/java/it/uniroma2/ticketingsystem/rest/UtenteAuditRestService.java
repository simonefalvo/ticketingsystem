package it.uniroma2.ticketingsystem.rest;

import it.uniroma2.ticketingsystem.controller.UtenteAuditController;
import it.uniroma2.ticketingsystem.entity.UtenteAudit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "utenteaudit")
public class UtenteAuditRestService {

    @Autowired
    private UtenteAuditController utenteAuditController;

    @RequestMapping(path = "", method = RequestMethod.POST)
    public ResponseEntity<UtenteAudit> creaUtenteAudit(@RequestBody UtenteAudit utente) {
        UtenteAudit utenteAuditCreato = utenteAuditController.creaUtenteAudit(utente);
        return new ResponseEntity<>(utenteAuditCreato, HttpStatus.CREATED);
    }
}
