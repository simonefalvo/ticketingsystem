package it.uniroma2.ticketingsystem.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Ruolo {

    @Id
    @GeneratedValue
    private Long id;
    String name;

    Ruolo() {}

    public Ruolo(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
