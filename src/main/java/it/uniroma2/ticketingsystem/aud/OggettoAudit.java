package it.uniroma2.ticketingsystem.aud;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class OggettoAudit {

    @Id
    @GeneratedValue
    private Integer id;

    private Integer id_oggetto;
    private String nome;
    private String versione;

    private Timestamp time_stamp;

    public OggettoAudit(@NotNull Integer id, @NotNull Integer id_oggetto, @NotNull String nome,
                        @NotNull String versione,@NotNull Timestamp time_stamp){
        this.id=id;
        this.id_oggetto=id_oggetto;
        this.nome = nome;
        this.versione = versione;
        this.time_stamp = time_stamp;
    }
}
