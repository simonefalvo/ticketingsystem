package it.uniroma2.ticketingsystem.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@NoArgsConstructor
@Getter
@Setter

public class Ticket {

    @Id
    @GeneratedValue
    private Integer id;
    private String stato;
    private String titolo;
    private String categoria;
    private Integer prioritaCustomer;
    private Integer prioritaTeam;
    private Integer customerid;
    private String prodotto;
    private Timestamp time_stamp;
    private String descrizione;
    private Integer teamid;
    private Integer assistenteid;

    public Ticket(@NotNull Integer id, @NotNull String stato, @NotNull String titolo, @NotNull String categoria, @NotNull Integer prioritaCustomer, @NotNull Integer prioritaTeam, @NotNull Integer teamid,
                  @NotNull Integer assistente, @NotNull String prodotto, @NotNull Timestamp time_stamp, @NotNull Integer customerid, @NotNull String descrizione){

        this.id = id;
        this.stato = stato;
        this.titolo = titolo;
        this.categoria = categoria;
        this.prioritaCustomer = prioritaCustomer;
        this.prioritaTeam = prioritaTeam;
        this.teamid = teamid;
        this.assistenteid = assistente;
        this.time_stamp = time_stamp;
        this.customerid = customerid;
        this.descrizione= descrizione;
        this.prodotto = prodotto;

    }

    public void aggiorna(@NotNull Ticket nuovoTicket){

        this.id = nuovoTicket.id;
        this.stato = nuovoTicket.stato;
        this.titolo = nuovoTicket.titolo;
        this.categoria = nuovoTicket.categoria;
        this.prioritaCustomer = nuovoTicket.prioritaCustomer;
        this.prioritaTeam = nuovoTicket.prioritaTeam;
        this.teamid = nuovoTicket.teamid;
        this.assistenteid = nuovoTicket.assistenteid;
        this.time_stamp = nuovoTicket.time_stamp;
        this.customerid = nuovoTicket.customerid;
        this.descrizione= nuovoTicket.descrizione;
        this.prodotto = nuovoTicket.prodotto;


    }


}
