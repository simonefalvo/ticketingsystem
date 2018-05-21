package it.uniroma2.ticketingsystem.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor
@Getter
@Setter


public class Utente {

    @Id
    @GeneratedValue // Autoincrement
    private Integer id;
    private String nome;
    private String cognome;
    private String username;
    private String password;
    private String email;
    private int tipo;


    public Utente(@NotNull String nome, @NotNull String cognome, @NotNull String username, @NotNull String password, @NotNull String email, @NotNull int tipo) {
        this.nome = nome;
        this.cognome = cognome;
        this.username = username;
        this.password = password;
        this.email = email;
        this.tipo = tipo;
    }

    public void aggiorna(@NotNull Utente datiAggiornati) {
        this.nome = datiAggiornati.nome;
        this.cognome = datiAggiornati.cognome;
        this.username = datiAggiornati.username;
        this.password = datiAggiornati.password;
        this.email = datiAggiornati.email;
        this.tipo = datiAggiornati.tipo;

    }
}
