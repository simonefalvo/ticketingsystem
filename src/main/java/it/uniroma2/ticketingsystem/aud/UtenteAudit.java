package it.uniroma2.ticketingsystem.aud;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Set;
import it.uniroma2.ticketingsystem.entity.Utente;


@Entity
@NoArgsConstructor
@Getter
@Setter
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
    private int tipo;
    private Timestamp timestamp;

    private int operazione;

    @OneToMany(mappedBy = "autore", cascade = CascadeType.ALL)
    @JsonManagedReference(value="autore")   // to avoid infinite recursion in serialization
    private Set<TicketAudit> ticketAperti;

    @OneToMany(mappedBy = "teamMember", cascade = CascadeType.ALL)
    @JsonManagedReference(value="team_member")   // to avoid infinite recursion in serialization
    private Set<TicketAudit> ticketAssegnati;
    
    
    public UtenteAudit(@NotNull Integer id, @NotNull Integer idUtente, @NotNull String nome, @NotNull String cognome,
                       @NotNull String username, @NotNull String password, @NotNull String email, @NotNull int tipo,
                       @NotNull Timestamp timestamp, @NotNull int operazione) {

        this.id = id;
        this.idUtente = idUtente;
        this.nome = nome;
        this.cognome = cognome;
        this.username = username;
        this.password = password;
        this.email = email;
        this.tipo = tipo;
        this.timestamp = timestamp;
        this.operazione = operazione;

    }

    public UtenteAudit(Utente utente, Timestamp timestamp, int operazione){

        this.idUtente = utente.getId();
        this.nome = utente.getNome();
        this.cognome = utente.getCognome();
        this.username = utente.getUsername();
        this.password = utente.getPassword();
        this.email = utente.getEmail();
        this.tipo = utente.getTipo();
        this.timestamp = timestamp;
        this.operazione = operazione;


    }

}
