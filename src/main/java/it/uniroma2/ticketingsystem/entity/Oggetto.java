package it.uniroma2.ticketingsystem.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Oggetto {

    @Id
    @GeneratedValue
    private Integer id;

    private String nome;
    private String versione;

    @OneToMany(mappedBy = "oggetto", cascade = CascadeType.ALL)
    private Set<Ticket> tickets;


    public Oggetto(@NotNull String nome, @NotNull String versione, @NotNull Set<Ticket> tickets) {

        this.nome = nome;
        this.versione = versione;
        this.tickets = tickets;

    }

    public void aggiorna(@NotNull Oggetto nuovoOggetto){

        this.nome = nuovoOggetto.nome;
        this.versione = nuovoOggetto.versione;
        this.tickets = nuovoOggetto.tickets;

    }
}