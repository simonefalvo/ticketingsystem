package it.uniroma2.ticketingsystem.rest;

import it.uniroma2.ticketingsystem.controller.UtenteController;
import it.uniroma2.ticketingsystem.entity.Utente;
import it.uniroma2.ticketingsystem.event.UtenteEvent;
import it.uniroma2.ticketingsystem.exception.EntitaNonTrovataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


// @RestController e @Controller identificano uno Spring Bean che nell'architettura MVC è l'anello di congiunzione tra
    // la View e il Controller (vedere l'annotazione @Service della classe PersonaController).
// La differenzatra @Controller e @RestController è che @RestController (che estende @Controller) preconfigura tutti i
// metodi per accettare in input e restituire in output delle richieste HTTP il cui payload è in formato JSON.
// Per ottenere lo stesso comportamento del @RestController, si possono utilizzare l'annotazione @Controller e
// l'annotazione @ResponseBody; quest'ultima serve appunto a denotare che un metodo (o tutti i metodi di una classe)
// restituiscono dati in formati JSON. Gli attributi "produces" e "consumes" di @RequestMapping permettono di definire
// il MimeType dei dati restituiti e ricevuti, rispettivamente. Quando input e output sono in formato JSON, l'annotazione
// @RestController è un metodo sintetico per dichiararlo e fornire a Spring la configurazione necessaria per serialzizare
// e deserializzare il JSON.
    @RestController
    @RequestMapping(path = "utente")
    public class UtenteRestService {

        @Autowired
        private UtenteController utenteController;


        @Autowired
        private ApplicationEventPublisher applicationEventPublisher;

        @RequestMapping(path = "", method = RequestMethod.POST)
        public ResponseEntity<Utente> creaUtente(@RequestBody Utente utente) {
            Utente utenteCreato = utenteController.creaUtente(utente);

            UtenteEvent utenteEvent =  new UtenteEvent(this,utente,0);
            applicationEventPublisher.publishEvent(utenteEvent);

            return new ResponseEntity<>(utenteCreato, HttpStatus.CREATED);
        }

        @RequestMapping(path = "{id}", method = RequestMethod.PUT)
        public ResponseEntity<Utente> aggiornaUtente(@PathVariable Integer id, @RequestBody Utente utente) {
            Utente utenteAggiornato;
            try {
                utenteAggiornato = utenteController.aggiornaUtente(id, utente);

                UtenteEvent utenteEvent = new UtenteEvent(this,utente,1);
                applicationEventPublisher.publishEvent(utenteEvent);

            } catch (EntitaNonTrovataException e) {
                return new ResponseEntity<>(utente, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(utenteAggiornato, HttpStatus.OK);
        }

        @RequestMapping(path = "{id}", method = RequestMethod.GET)
        public ResponseEntity<Utente> cercaUtente(@PathVariable Integer id) {
            System.out.print("\n Sono in responseEntity");
            Utente utenteTrovato = utenteController.cercaUtentePerId(id);
            return new ResponseEntity<>(utenteTrovato, utenteTrovato == null ? HttpStatus.NOT_FOUND : HttpStatus.CREATED);
        }

        @RequestMapping(path = "{id}", method = RequestMethod.DELETE)
        public ResponseEntity<Boolean> eliminaUtente(@PathVariable Integer id) {

            UtenteEvent utenteEvent = new UtenteEvent(this, utenteController.cercaUtentePerId(id), 2);

            applicationEventPublisher.publishEvent(utenteEvent);

            boolean eliminata = utenteController.eliminaUtente(id);
            return new ResponseEntity<>(eliminata, eliminata ? HttpStatus.OK : HttpStatus.NOT_FOUND);
        }

        @RequestMapping(path = "", method = RequestMethod.GET)
        public ResponseEntity<List<Utente>> prelevaUtenti() {
            List<Utente> utenti = utenteController.prelevaUtenti();
            return new ResponseEntity<>(utenti, HttpStatus.OK);
        }
        /*
        @RequestMapping(method = RequestMethod.GET)
        public ResponseEntity<Utente> cercaUtentePerEmail(@RequestParam(value = "email") String email) {
            Utente utenteTrovato = utenteController.cercaUtentePerEmail(email);
            return new ResponseEntity<>(utenteTrovato, utenteTrovato == null ? HttpStatus.NOT_FOUND : HttpStatus.CREATED);
        }
        */
    }
