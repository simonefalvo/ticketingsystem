package it.uniroma2.ticketingsystem.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
//@Audited
public class Oggetto {

    @Id
    @GeneratedValue
    private Integer id;
    private String nome;
    private String versione;
    @OneToMany(mappedBy = "oggetto", cascade = CascadeType.ALL)
    @JsonManagedReference   // to avoid infinite recursion in serialization
    private Set<Ticket> tickets;

    public Oggetto(@NotNull Integer id, @NotNull String nome, @NotNull String versione, @NotNull Set<Ticket> tickets) {
        this.id = id;
        this.nome = nome;
        this.versione = versione;
        this.tickets = tickets;
    }

    public void aggiorna(@NotNull Oggetto nuovoOggetto){
        this.id = nuovoOggetto.id;
        this.nome = nuovoOggetto.nome;
        this.versione = nuovoOggetto.versione;
        this.tickets = nuovoOggetto.tickets;
    }
}