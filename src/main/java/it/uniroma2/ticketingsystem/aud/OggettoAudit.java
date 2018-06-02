package it.uniroma2.ticketingsystem.aud;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import it.uniroma2.ticketingsystem.entity.Ticket;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class OggettoAudit {

    @Id
    @GeneratedValue
    private Integer id;

    private Integer idOggetto;
    private String nome;
    private String versione;
    private Timestamp timestamp;

    @OneToMany(mappedBy = "oggetto", cascade = CascadeType.ALL)
    private Set<TicketAudit> tickets;

    public OggettoAudit(@NotNull Integer id, @NotNull Integer idOggetto, @NotNull String nome,
                        @NotNull String versione, @NotNull Timestamp timestamp,
                        @NotNull Set<TicketAudit> tickets){

        this.id = id;
        this.idOggetto = idOggetto;
        this.nome = nome;
        this.versione = versione;
        this.timestamp = timestamp;
        this.tickets = tickets;
    }
}
