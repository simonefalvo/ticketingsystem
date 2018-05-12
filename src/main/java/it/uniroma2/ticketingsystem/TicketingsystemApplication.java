package it.uniroma2.ticketingsystem;

import it.uniroma2.ticketingsystem.controller.UtenteController;
import it.uniroma2.ticketingsystem.entity.Utente;
import it.uniroma2.ticketingsystem.rest.UtenteRestService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TicketingsystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(TicketingsystemApplication.class, args);
    }
}
