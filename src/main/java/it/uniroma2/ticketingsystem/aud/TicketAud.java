package it.uniroma2.ticketingsystem.aud;

import com.fasterxml.jackson.annotation.JsonBackReference;
import it.uniroma2.ticketingsystem.entity.Oggetto;
import it.uniroma2.ticketingsystem.entity.Utente;
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
public class TicketAud {

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
    @JsonBackReference // to avoid infinite recursion in serialization
    private Utente creatore;
    @ManyToOne
    @JsonBackReference // to avoid infinite recursion in serialization
    private Utente teamMember;
    @ManyToOne
    @JsonBackReference // to avoid infinite recursion in serialization
    private Oggetto oggetto;

    //private Integer teamid;  //attributo opzionale


    public TicketAud(@NotNull String categoria, @NotNull String descrizione, @NotNull Integer prioritaCreatore,
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

}
