package it.uniroma2.ticketingsystem.aud;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@NoArgsConstructor
@Getter
@Setter
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class TicketAudit {

    @Id
    @GeneratedValue
    private Integer id;

    private Integer idTicket;
    private String titolo;
    private String categoria;
    private String descrizione;
    private String stato;
    private Date timestamp;
    private Integer prioritaAutore;
    private Integer prioritaTeam;

    @ManyToOne
    private UtenteAudit autore;
    @ManyToOne
    private UtenteAudit teamMember;
    @ManyToOne
    private OggettoAudit oggetto;

    //private Integer teamid;  //attributo opzionale


    public TicketAudit(@NotNull Integer idTicket, @NotNull String categoria, @NotNull String descrizione,
                       @NotNull Integer prioritaAutore, @NotNull Integer prioritaTeam, @NotNull String titolo,
                       @NotNull String stato, @NotNull Date timestamp, @NotNull UtenteAudit autore,
                       @NotNull UtenteAudit teamMember, @NotNull OggettoAudit oggetto) {

        this.idTicket = idTicket;
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
