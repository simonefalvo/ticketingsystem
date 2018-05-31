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
    private Timestamp timestamp;
    private Integer prioritaAutore;
    private Integer prioritaTeam;

    @ManyToOne
    @JsonBackReference(value="autore") // to avoid infinite recursion in serialization
    private Utente autore;
    @ManyToOne
    @JsonBackReference(value="team_member") // to avoid infinite recursion in serialization
    private Utente teamMember;
    @ManyToOne
    @JsonBackReference(value="oggetto") // to avoid infinite recursion in serialization
    private Oggetto oggetto;

    //private Integer teamid;  //attributo opzionale


    public Ticket(@NotNull String categoria, @NotNull String descrizione, @NotNull Integer prioritaAutore,
                  @NotNull Integer prioritaTeam, @NotNull String titolo, @NotNull String stato,
                  @NotNull Timestamp timestamp, @NotNull Utente autore, @NotNull Utente teamMember,
                  @NotNull Oggetto oggetto) {

        this.categoria = categoria;
        this.descrizione = descrizione;
        this.prioritaAutore = prioritaAutore;
        this.prioritaTeam = prioritaTeam;
        this.titolo = titolo;
        this.stato = stato;
        this.timestamp = timestamp;
        this.autore = autore;
        this.teamMember = teamMember;
        this.oggetto = oggetto;

    }

    public void aggiorna(@NotNull Ticket nuovoTicket) {

        this.titolo = nuovoTicket.titolo;
        this.categoria = nuovoTicket.categoria;
        this.descrizione= nuovoTicket.descrizione;
        this.stato = nuovoTicket.stato;
        this.prioritaAutore = nuovoTicket.prioritaAutore;
        this.prioritaTeam = nuovoTicket.prioritaTeam;
        this.timestamp = nuovoTicket.timestamp;
        this.autore = nuovoTicket.autore;
        this.teamMember = nuovoTicket.teamMember;
        this.oggetto = nuovoTicket.oggetto;
        //this.teamid = nuovoTicket.teamid;

    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", titolo='" + titolo + '\'' +
                ", categoria='" + categoria + '\'' +
                ", descrizione='" + descrizione + '\'' +
                ", stato='" + stato + '\'' +
                ", timestamp=" + timestamp +
                ", prioritaAutore=" + prioritaAutore +
                ", prioritaTeam=" + prioritaTeam +
                ", autore=" + autore +
                ", teamMember=" + teamMember +
                ", oggetto=" + oggetto +
                '}';
    }
}
