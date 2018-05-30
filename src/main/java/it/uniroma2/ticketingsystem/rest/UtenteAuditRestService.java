package it.uniroma2.ticketingsystem.rest;


import it.uniroma2.ticketingsystem.aud.UtenteAudit;
import it.uniroma2.ticketingsystem.controller.UtenteAuditController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "utenteaudit")
public class UtenteAuditRestService {

    @Autowired
    private UtenteAuditController uac;

    @RequestMapping(path = "", method = RequestMethod.POST)
    public ResponseEntity<UtenteAudit> creaOggetto(@RequestBody UtenteAudit ua) {
        UtenteAudit uaCreato = uac.creaUtenteAud(ua);
        return new ResponseEntity<>(uaCreato, HttpStatus.CREATED);
    }

    @RequestMapping(path = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> eliminaStatoTicketAudit(@PathVariable Integer id) {
        boolean uaEliminato = uac.eliminaUtenteAudit(id);
        return new ResponseEntity<>(uaEliminato, uaEliminato ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
}
