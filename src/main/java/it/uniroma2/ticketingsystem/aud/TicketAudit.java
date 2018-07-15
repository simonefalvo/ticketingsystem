package it.uniroma2.ticketingsystem.aud;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import it.uniroma2.ticketingsystem.entity.Ticket;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
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

    //@Column(name = "edit_time")
    private Date timestamp;
    private Integer prioritaAutore;
    private Integer prioritaTeam;

    private int operazione;

    @ManyToOne
    private UtenteAudit autore;
    @ManyToOne
    private UtenteAudit teamMember;
    @ManyToOne
    private OggettoAudit oggetto;

    //private Integer teamid;  //attributo opzionale

    public TicketAudit(@NotNull Ticket ticket,  @NotNull Date timestamp, @NotNull OggettoAudit oggettoAudit,@NotNull UtenteAudit utenteAudit ,@NotNull int operation){
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
        this.autore = utenteAudit;
        this.operazione = operation;
        //todo aggiungere autore e team member
    }
    public TicketAudit(@NotNull Ticket ticket,  @NotNull Date timestamp, @NotNull int operation){
        this.idTicket = ticket.getId();
        this.titolo = ticket.getTitolo();
        this.categoria = ticket.getCategoria();
        this.descrizione = ticket.getDescrizione();
        this.stato = ticket.getStato();
        this.prioritaAutore = ticket.getPrioritaAutore();
        this.prioritaTeam = ticket.getPrioritaTeam();
        //this.oggetto = oggettoAudit;
        this.timestamp = timestamp;
        this.operazione = operation;
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
        this.operazione = operation;
        this.autore = autore;
        this.teamMember = teamMember;
        this.oggetto = oggetto;
    }



}
