package it.uniroma2.ticketingsystem.aud;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
public class OggettoAudit {

    @Id
    @GeneratedValue
    private Integer id;

    private Integer idOggetto;
    private String nome;
    private String versione;
    private Timestamp timestamp;

    @OneToMany(mappedBy = "oggetto", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "oggetto")   // to avoid infinite recursion in serialization
    private Set<TicketAudit> tickets;

    public OggettoAudit(@NotNull Integer id, @NotNull Integer idOggetto, @NotNull String nome,
                        @NotNull String versione, @NotNull Timestamp timestamp){

        this.id = id;
        this.idOggetto = idOggetto;
        this.nome = nome;
        this.versione = versione;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "OggettoAudit{" +
                "id=" + id +
                ", idOggetto=" + idOggetto +
                ", nome='" + nome + '\'' +
                ", versione='" + versione + '\'' +
                ", timestamp=" + timestamp +
                ", tickets=" + tickets +
                '}';
    }
}
