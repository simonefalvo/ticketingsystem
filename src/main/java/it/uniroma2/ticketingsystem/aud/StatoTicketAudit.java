package it.uniroma2.ticketingsystem.aud;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
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

    private Integer idTicket;

    @Column(name = "edit_time")
    private Timestamp time_stamp;
    private String stato;


    public StatoTicketAudit(@NotNull Integer id, @NotNull Integer idTicket, @NotNull String stato,
                            @NotNull Timestamp time_stamp) {

        this.id = id;
        this.idTicket = idTicket;
        this.stato = stato;
        this.time_stamp =  time_stamp;

    }

}
