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
public class UtenteAudit {

    @Id
    @GeneratedValue
    private Integer id;

    private Integer id_utente;
    private String nome;
    private String cognome;
    private String username;
    private String password;
    private String email;
    private int tipo;

    private Timestamp time_stamp;

    public UtenteAudit(@NotNull Integer id, @NotNull Integer id_utente, @NotNull String nome, @NotNull String cognome,
                       @NotNull String username, @NotNull String password, @NotNull String email, @NotNull int tipo, @NotNull Timestamp time_stamp){
        this.id = id;
        this.id_utente=id_utente;
        this.nome = nome;
        this.cognome = cognome;
        this.username = username;
        this.password = password;
        this.email = email;
        this.tipo = tipo;
        this.time_stamp = time_stamp;

    }


}
