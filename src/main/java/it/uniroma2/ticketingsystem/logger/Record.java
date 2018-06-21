package it.uniroma2.ticketingsystem.logger;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Record {

    @Id
    @GeneratedValue
    private Integer id;

    private String typology;
    private String author;
    private String objectType;
    private String tag;

    @Column(columnDefinition="TEXT")
    private String payload;
    @Column(columnDefinition="TEXT")
    private String returnObj;


    private Timestamp recordTimeStamp;

    public Record(@NotNull String typology, String author, String objectType, String payload, String returnObj, String tag){

        this.typology = typology;
        this.author = author;
        this.payload = payload;
        this.objectType = objectType;
        this.recordTimeStamp = new Timestamp(System.currentTimeMillis());
        this.returnObj = returnObj;
        this.tag = tag;

    }

    public Record(String typology, String author, String tag) {
        this.typology = typology;
        this.author = author;
        this.tag = tag;
        this.recordTimeStamp = new Timestamp(System.currentTimeMillis());
    }

    public Record(String typology, String author, String returnObj, String tag){
        this.typology = typology;
        this.author = author;
        this.returnObj = returnObj;
        this.tag = tag;
    }


}
