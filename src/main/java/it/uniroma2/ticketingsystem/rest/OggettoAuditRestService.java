package it.uniroma2.ticketingsystem.rest;

import it.uniroma2.ticketingsystem.aud.OggettoAudit;
import it.uniroma2.ticketingsystem.controller.OggettoAuditController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "oggettoaudit")
public class OggettoAuditRestService {

    @Autowired
    private OggettoAuditController oac;

    @RequestMapping(path = "", method = RequestMethod.POST)
    public ResponseEntity<OggettoAudit> creaOggetto(@RequestBody OggettoAudit oa) {
        OggettoAudit oaCreato = oac.creaOggettoAudit(oa);
        return new ResponseEntity<>(oaCreato, HttpStatus.CREATED);
    }

    @RequestMapping(path = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> eliminaStatoTicketAudit(@PathVariable Integer id) {
        boolean oaEliminato = oac.eliminaOggettoAudit(id);
        return new ResponseEntity<>(oaEliminato, oaEliminato ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
}
