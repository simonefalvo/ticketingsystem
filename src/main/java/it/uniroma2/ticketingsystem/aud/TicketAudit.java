package it.uniroma2.ticketingsystem.aud;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import it.uniroma2.ticketingsystem.entity.Ticket;
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

    private int operation;

    @ManyToOne
    @JsonManagedReference(value="autore") // to avoid infinite recursion in serialization
    private UtenteAudit autore;
    @ManyToOne
    @JsonManagedReference(value="team_member") // to avoid infinite recursion in serialization
    private UtenteAudit teamMember;
    @ManyToOne
    @JsonManagedReference(value="oggetto") // to avoid infinite recursion in serialization
    private OggettoAudit oggetto;

    //private Integer teamid;  //attributo opzionale

    public TicketAudit(@NotNull Ticket ticket,  @NotNull Timestamp timestamp, @NotNull OggettoAudit oggettoAudit, @NotNull int operation){
        this.idTicket = ticket.getId();
        this.titolo = ticket.getTitolo();
        this.categoria = ticket.getCategoria();
        this.descrizione = ticket.getDescrizione();
        this.stato = ticket.getStato();
        this.prioritaAutore = ticket.getPrioritaAutore();
        this.prioritaTeam = ticket.getPrioritaTeam();
        this.oggetto = oggettoAudit;
        System.out.println("\n\n\n Oggetto inserito= "+this.oggetto.toString());
        this.timestamp = timestamp;
        this.operation = operation;
        //todo aggiungere autore e team member
    }
    public TicketAudit(@NotNull Ticket ticket,  @NotNull Timestamp timestamp, @NotNull int operation){
        this.idTicket = ticket.getId();
        this.titolo = ticket.getTitolo();
        this.categoria = ticket.getCategoria();
        this.descrizione = ticket.getDescrizione();
        this.stato = ticket.getStato();
        this.prioritaAutore = ticket.getPrioritaAutore();
        this.prioritaTeam = ticket.getPrioritaTeam();
        //this.oggetto = oggettoAudit;
        this.timestamp = timestamp;
        this.operation = operation;
        //todo aggiungere autore e team member
    }

    public TicketAudit(Integer idTicket, String titolo, String categoria, String descrizione, String stato, Timestamp timestamp, Integer prioritaAutore, Integer prioritaTeam, int operation, UtenteAudit autore, UtenteAudit teamMember, OggettoAudit oggetto) {
        this.idTicket = idTicket;
        this.titolo = titolo;
        this.categoria = categoria;
        this.descrizione = descrizione;
        this.stato = stato;
        this.timestamp = timestamp;
        this.prioritaAutore = prioritaAutore;
        this.prioritaTeam = prioritaTeam;
        this.operation = operation;
        this.autore = autore;
        this.teamMember = teamMember;
        this.oggetto = oggetto;
    }

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
