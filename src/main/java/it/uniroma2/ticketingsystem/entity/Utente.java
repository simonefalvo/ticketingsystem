package it.uniroma2.ticketingsystem.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import it.uniroma2.ticketingsystem.observer.ObserverUtente;

import java.sql.Timestamp;

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

    @Transient
    private ObserverUtente observer;
    @Transient
    public UtenteAudit ua;


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

    public void notifyObserver(UtenteAudit ua){
        this.observer.update(ua);
    }

    public UtenteAudit newUtenteAudit(){

        UtenteAudit ua = new UtenteAudit(this.id, "crezione", this.nome,
                "nome old", this.cognome, "cognome_old",
                this.username, "bbb",this.password,"ffff",
                this.email,"aa@a",this.tipo,1,new Timestamp(System.currentTimeMillis()));

        return ua;

    }

    public UtenteAudit aggiornaUtenteAudit(Utente utente_new){

        UtenteAudit ua = new UtenteAudit(this.id, "modifica", utente_new.nome,
                this.nome, utente_new.cognome, this.cognome, utente_new.username,
                this.username, utente_new.password,this.password, utente_new.email,
                this.email,utente_new.tipo,this.tipo, new Timestamp(System.currentTimeMillis()));

        return ua;

    }

    public void setObserver(ObserverUtente observer) {
        this.observer = observer;
    }
}
