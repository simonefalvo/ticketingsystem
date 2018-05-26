package it.uniroma2.ticketingsystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Audited
public class Ticket {

    @Id
    @GeneratedValue
    private Integer id;
    private String categoria;
    private String descrizione;
    private Integer prioritaCustomer;
    private Integer prioritaTeam;
    //private Integer customerid;
    // private String prodotto;
    private String titolo;
    private String stato;


    @ManyToOne
    @JsonBackReference // to avoid infinite recursion in serialization
    private Oggetto oggetto;
    //private Timestamp time_stamp;
    //private Integer teamid;
    //private Integer assistenteid;

    /*
    public Ticket(@NotNull Integer id, @NotNull String stato, @NotNull String titolo, @NotNull String categoria,
                  @NotNull Integer prioritaCustomer, @NotNull Integer prioritaTeam, @NotNull Integer teamid,
                  @NotNull Integer assistente, @NotNull Oggetto oggetto, @NotNull Timestamp time_stamp,
                  @NotNull Integer customerid, @NotNull String descrizione){
    */
    public Ticket(@NotNull String categoria, @NotNull String descrizione,
                  @NotNull Integer prioritaCustomer, @NotNull Integer prioritaTeam,
                  @NotNull Oggetto oggetto, @NotNull String titolo, @NotNull String stato){
        this.categoria = categoria;
        this.descrizione= descrizione;
        this.prioritaCustomer = prioritaCustomer;
        this.prioritaTeam = prioritaTeam;
        this.titolo = titolo;
        this.stato = stato;


        this.oggetto = oggetto;
        /*
        //this.prodotto = prodotto;
        this.teamid = teamid;
        this.assistenteid = assistente;
        this.time_stamp = time_stamp;
        this.customerid = customerid;
        this.oggetto = oggetto;
        */
    }

    public void aggiorna(@NotNull Ticket nuovoTicket){
        this.stato = nuovoTicket.stato;
        this.titolo = nuovoTicket.titolo;
        this.categoria = nuovoTicket.categoria;
        this.prioritaCustomer = nuovoTicket.prioritaCustomer;
        this.prioritaTeam = nuovoTicket.prioritaTeam;
        //this.teamid = nuovoTicket.teamid;
        //this.assistenteid = nuovoTicket.assistenteid;
        //this.time_stamp = nuovoTicket.time_stamp;
        //this.customerid = nuovoTicket.customerid;
        this.descrizione= nuovoTicket.descrizione;
        this.oggetto = nuovoTicket.oggetto;
    }

}
