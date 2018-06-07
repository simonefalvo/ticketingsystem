package it.uniroma2.ticketingsystem;


import it.uniroma2.ticketingsystem.configuration.CustomUserDetails;
import it.uniroma2.ticketingsystem.controller.UtenteController;
import it.uniroma2.ticketingsystem.dao.UtenteDao;
import it.uniroma2.ticketingsystem.entity.Ruolo;
import it.uniroma2.ticketingsystem.entity.Utente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

@SpringBootApplication
public class TicketingsystemApplication {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(TicketingsystemApplication.class, args);
    }

    @Autowired
    public void authenticationManager(AuthenticationManagerBuilder builder, UtenteDao repository, UtenteController service) throws Exception {
        if (repository.count()==0)
            service.creaUtente(new Utente("Andrea", "Ranfone", "Ranfo","prova","mail@mail" ,Arrays.asList(new Ruolo("ADMIN")),null,null));
        builder.userDetailsService(userDetailsService(repository)).passwordEncoder(passwordEncoder);
    }

    /**
     * We return an istance of our CustomUserDetails.
     * @param repository
     * @return
     */

    private UserDetailsService userDetailsService(final UtenteDao repository) {
        return username -> new CustomUserDetails(repository.findByUsername(username));
    }
}
