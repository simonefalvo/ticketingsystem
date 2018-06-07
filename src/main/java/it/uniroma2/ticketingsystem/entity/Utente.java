package it.uniroma2.ticketingsystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.List;
import java.util.Set;


@Entity
@NoArgsConstructor
@Getter
@Setter
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Utente {

    @Id
    @GeneratedValue // Autoincrement
    private Integer id;

    private String nome;
    private String cognome;
    private String username;
    private String password;
    private String email;

    @OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    private List<Ruolo> ruoli;

    @OneToMany(mappedBy = "autore", cascade = CascadeType.ALL)
    private Set<Ticket> ticketAperti;

    @OneToMany(mappedBy = "teamMember", cascade = CascadeType.ALL)
    private Set<Ticket> ticketAssegnati;


    public Utente(@NotNull String nome, @NotNull String cognome, @NotNull String username, @NotNull String password,
                  @NotNull String email, @NotNull List<Ruolo> ruoli, @NotNull Set<Ticket> ticketAperti,
                  @NotNull Set<Ticket> ticketAssegnati) {

        this.nome = nome;
        this.cognome = cognome;
        this.username = username;
        this.password = password;
        this.email = email;
        this.ruoli = ruoli;
        this.ticketAperti = ticketAperti;
        this.ticketAssegnati = ticketAssegnati;

    }

    public void aggiorna(@NotNull Utente datiAggiornati) {

        this.nome = datiAggiornati.nome;
        this.cognome = datiAggiornati.cognome;
        this.username = datiAggiornati.username;
        this.password = datiAggiornati.password;
        this.email = datiAggiornati.email;
        this.ruoli = datiAggiornati.ruoli;
        this.ticketAperti = datiAggiornati.ticketAperti;
        this.ticketAssegnati = datiAggiornati.ticketAssegnati;

    }

}
