package it.uniroma2.ticketingsystem.logger.entity;

import lombok.Getter;
import lombok.Setter;
import org.json.JSONObject;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Payload {

    @Id
    @GeneratedValue
    private Integer id;

   // private String idObject;

    @Column(columnDefinition="TEXT")
    private String json;



    @Column(columnDefinition="TEXT")
    private String type;

    @ManyToOne
    private Record record;

    public Payload(String json, String type, Record record) {
        this.json = json;
        this.type = type;
        this.record = record;
    }


}
