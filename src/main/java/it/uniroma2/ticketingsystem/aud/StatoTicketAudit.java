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

public class StatoTicketAudit {

    @Id
    @GeneratedValue
    private Integer id;

    private Integer id_ticket;
    private Timestamp time_stamp;

    private String stato;

    public StatoTicketAudit(@NotNull Integer id, @NotNull Integer id_ticket, @NotNull String stato, @NotNull Timestamp time_stamp){
        this.id = id;
        this.id_ticket = id_ticket;
        this.stato = stato;
        this.time_stamp =  time_stamp;
    }



}
