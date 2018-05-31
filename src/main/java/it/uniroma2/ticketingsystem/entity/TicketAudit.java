package it.uniroma2.ticketingsystem.entity;
/*
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

import java.sql.Timestamp;

@Entity
@Getter
@Setter

@IdClass(TicketAudit.TicketAuditID.class)
public class TicketAudit implements Serializable {

    @Id
    private int id;
    @Id
    private Timestamp time;
    private int operation;

    public static class TicketAuditID implements Serializable{
        private int id;
        private Timestamp time;

        @Override
        public int hashCode() {
            //todo
            return super.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            //todo
            return super.equals(obj);
        }

        public TicketAuditID() {}

        public TicketAuditID(int id, Timestamp time){
            this.id=id;
            this.time=time;
        }
    }

    public TicketAudit(@NotNull int id, @NotNull Timestamp time, @NotNull int operation){
        this.id = id;
        this.time = time;
        this.operation = operation;
    }

    public TicketAudit() {
    }
}
*/