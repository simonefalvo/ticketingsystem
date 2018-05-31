package it.uniroma2.ticketingsystem.aud;

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
public class TicketAudit {

    @Id
    @GeneratedValue
    private Integer id;

    private Integer idTicket;
    private String titolo;
    private String categoria;
    private String descrizione;
    private String stato;
    private Timestamp timestamp;
    private Integer prioritaAutore;
    private Integer prioritaTeam;

    @ManyToOne
    @JsonBackReference(value="autore") // to avoid infinite recursion in serialization
    private UtenteAudit autore;
    @ManyToOne
    @JsonBackReference(value="team_member") // to avoid infinite recursion in serialization
    private UtenteAudit teamMember;
    @ManyToOne
    @JsonBackReference(value="oggetto") // to avoid infinite recursion in serialization
    private OggettoAudit oggetto;

    //private Integer teamid;  //attributo opzionale


    public TicketAudit(@NotNull Integer idTicket, @NotNull String categoria, @NotNull String descrizione,
                       @NotNull Integer prioritaAutore, @NotNull Integer prioritaTeam, @NotNull String titolo,
                       @NotNull String stato, @NotNull Timestamp timestamp, @NotNull UtenteAudit autore,
                       @NotNull UtenteAudit teamMember, @NotNull OggettoAudit oggetto) {

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

}
