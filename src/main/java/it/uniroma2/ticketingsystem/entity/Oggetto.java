package it.uniroma2.ticketingsystem.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Oggetto {

    @Id
    @GeneratedValue
    private Integer id;

    private String nome;
    private String versione;

    @OneToMany(mappedBy = "oggetto", cascade = CascadeType.ALL)
    //TODO ?
    @JsonManagedReference(value = "oggetto")   // to avoid infinite recursion in serialization
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

    @Override
    public String toString() {
        return "Oggetto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getVersione() {
        return versione;
    }
}