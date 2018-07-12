package it.uniroma2.ticketingsystem.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.annotation.JsonIgnore;
import it.uniroma2.ticketingsystem.logger.aspect.LogClass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@NoArgsConstructor
@LogClass
@Getter
@Setter
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Oggetto {

    @Id
    @GeneratedValue
    private Integer id;

    @NotNull
    private String nome;
    @NotNull
    private String versione;

    @JsonIgnore
    @OneToMany(mappedBy = "oggetto", cascade = CascadeType.ALL)
    private Set<Ticket> tickets;


    public Oggetto(@NotNull String nome, @NotNull String versione, @NotNull Set<Ticket> tickets) {
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

    @Override
    public String toString() {
        return "Oggetto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", versione='" + versione + '\'' +
                '}';
    }

}