package it.uniroma2.ticketingsystem.logger.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
public class Record {

    @Id
    @GeneratedValue
    private Integer id;

    private String typology;
    private String author;
    private String tag;


    @OneToMany(mappedBy = "record", cascade = CascadeType.ALL)
    private Set<Payload> payloads;

    private Timestamp recordTimeStamp;

    public Record(@NotNull String typology, String author,  String tag){

        this.typology = typology;
        this.author = author;
        this.recordTimeStamp = new Timestamp(System.currentTimeMillis());
        this.tag = tag;

    }

    public Record(String typology, String author, String tag, Set<Payload> payloads, Timestamp recordTimeStamp) {
        this.typology = typology;
        this.author = author;
        this.tag = tag;
        this.payloads = payloads;
        this.recordTimeStamp = recordTimeStamp;
    }
}
