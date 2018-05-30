package it.uniroma2.ticketingsystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
    private String titolo;
    private String categoria;
    private String descrizione;
    private String stato;
    private Timestamp time_stamp;
    private Integer prioritaCreatore;
    private Integer prioritaTeam;

    @ManyToOne
    @JsonBackReference(value="creatore") // to avoid infinite recursion in serialization
    private Utente creatore;
    @ManyToOne
    @JsonBackReference(value="team_member") // to avoid infinite recursion in serialization
    private Utente teamMember;
    @ManyToOne
    @JsonBackReference(value="oggetto") // to avoid infinite recursion in serialization
    private Oggetto oggetto;
    
    //private Integer teamid;  //attributo opzionale

    
    public Ticket(@NotNull String categoria, @NotNull String descrizione, @NotNull Integer prioritaCreatore,
                  @NotNull Integer prioritaTeam, @NotNull String titolo, @NotNull String stato, 
                  @NotNull Timestamp time_stamp, @NotNull Utente creatore, @NotNull Utente teamMember,
                  @NotNull Oggetto oggetto) {
        
        this.categoria = categoria;
        this.descrizione = descrizione;
        this.prioritaCreatore = prioritaCreatore;
        this.prioritaTeam = prioritaTeam;
        this.titolo = titolo;
        this.stato = stato;
        this.time_stamp = time_stamp;
        this.creatore = creatore;
        this.teamMember = teamMember;
        this.oggetto = oggetto;
    }

    public void aggiorna(@NotNull Ticket nuovoTicket){
        this.titolo = nuovoTicket.titolo;
        this.categoria = nuovoTicket.categoria;
        this.descrizione= nuovoTicket.descrizione;
        this.stato = nuovoTicket.stato;
        this.prioritaCreatore = nuovoTicket.prioritaCreatore;
        this.prioritaTeam = nuovoTicket.prioritaTeam;
        this.time_stamp = nuovoTicket.time_stamp;
        this.creatore = nuovoTicket.creatore;
        this.teamMember = nuovoTicket.teamMember;
        this.oggetto = nuovoTicket.oggetto;
        //this.teamid = nuovoTicket.teamid;
    }

}
