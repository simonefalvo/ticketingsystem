package it.uniroma2.ticketingsystem.aud;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import it.uniroma2.ticketingsystem.controller.ReflactionController;
import it.uniroma2.ticketingsystem.entity.Ruolo;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
import it.uniroma2.ticketingsystem.entity.Utente;
import org.apache.commons.lang3.reflect.FieldUtils;


@Entity
@NoArgsConstructor
@Getter
@Setter
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class UtenteAudit {
    @Id
    @GeneratedValue
    private Integer id;

    private Integer idUtente;
    private String nome;
    private String cognome;
    private String username;
    private String password;
    private String email;

    @OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    private List<Ruolo> ruoli;

    //@Column(name = "edit_time")
    private Timestamp timestamp;

    private int operazione;

    @OneToMany(mappedBy = "autore", cascade = CascadeType.ALL)
    private Set<TicketAudit> ticketAperti;

    @OneToMany(mappedBy = "teamMember", cascade = CascadeType.ALL)
    private Set<TicketAudit> ticketAssegnati;


    public UtenteAudit(@NotNull Integer id, @NotNull Integer idUtente, @NotNull String nome, @NotNull String cognome,
                       @NotNull String username, @NotNull String password, @NotNull String email, @NotNull List<Ruolo> ruoli,
                       @NotNull Timestamp timestamp, @NotNull int operazione) {

        this.id = id;
        this.idUtente = idUtente;
        this.nome = nome;
        this.cognome = cognome;
        this.username = username;
        this.password = password;
        this.email = email;
        this.ruoli = ruoli;
        this.timestamp = timestamp;
        this.operazione = operazione;

    }

    public UtenteAudit(@NotNull Integer id, @NotNull Integer idUtente, @NotNull String nome, @NotNull String cognome,
                       @NotNull String username, @NotNull String password, @NotNull String email, @NotNull List<Ruolo> ruoli,
                       @NotNull Timestamp timestamp, @NotNull Set<TicketAudit> ticketAperti,
                       @NotNull Set<TicketAudit> ticketAssegnati) {

        this.id = id;
        this.idUtente = idUtente;
        this.nome = nome;
        this.cognome = cognome;
        this.username = username;
        this.password = password;
        this.email = email;
        this.ruoli = ruoli;
        this.timestamp = timestamp;
        this.ticketAperti = ticketAperti;
        this.ticketAssegnati = ticketAssegnati;
    }


    public UtenteAudit(Utente utente, Timestamp timestamp, int operazione){


        this.idUtente = utente.getId();
        this.nome = utente.getNome();
        //test funzionamento reflaction
        this.nome = ReflactionController.getField((Object) utente,"nome");
        this.cognome = utente.getCognome();
        this.username = utente.getUsername();
        this.password = utente.getPassword();
        this.email = utente.getEmail();
        this.ruoli = utente.getRuoli();
        this.timestamp = timestamp;
        this.operazione = operazione;

    }


}
