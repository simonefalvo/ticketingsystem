package it.uniroma2.ticketingsystem.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@NoArgsConstructor
@Getter
@Setter

public class UtenteAudit {

    @Id
    private Integer id;

    private String tipologia;

    private String nome;
    private String nome_old;

    private String cognome;
    private String cognome_old;

    private String username;
    private String username_old;

    private String password;
    private String password_old;

    private String email;
    private String email_old;

    private int tipo;
    private int tipo_old;

    private Timestamp time_stamp;


    public UtenteAudit(@NotNull Integer id,@NotNull String tipologia, @NotNull String nome,@NotNull String nome_old,@NotNull String cognome,
                       @NotNull String cognome_old, @NotNull String username, @NotNull String username_old,@NotNull String password,
                       @NotNull String password_old, @NotNull String email,@NotNull String email_old, @NotNull int tipo, @NotNull int tipo_old,
                       @NotNull Timestamp time_stamp) {
        this.id = id;
        this.tipologia = tipologia;
        this.nome = nome;
        this.nome_old = nome_old;
        this.cognome = cognome;
        this.cognome_old = cognome_old;
        this.username = username;
        this.username_old = username_old;
        this.password = password;
        this.password_old = password_old;
        this.email = email;
        this.email_old = email_old;
        this.tipo = tipo;
        this.tipo_old = tipo_old;
        this.time_stamp = time_stamp;
    }

    @Override
    public String toString() {
        return "UtenteAudit{" +
                "id=" + id +
                ", tipologia=" + tipologia +
                ", nome='" + nome + '\'' +
                ", nome_old='" + nome_old + '\'' +
                ", cognome='" + cognome + '\'' +
                ", cognome_old='" + cognome_old + '\'' +
                ", username='" + username + '\'' +
                ", username_old='" + username_old + '\'' +
                ", password='" + password + '\'' +
                ", password_old='" + password_old + '\'' +
                ", email='" + email + '\'' +
                ", email_old='" + email_old + '\'' +
                ", tipo=" + tipo +
                ", tipo_old=" + tipo_old +
                ", time_stamp=" + time_stamp +
                '}';
    }
}
