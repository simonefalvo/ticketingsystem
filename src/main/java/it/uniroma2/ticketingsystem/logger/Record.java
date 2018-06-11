package it.uniroma2.ticketingsystem.logger;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Record {

    @Id
    @GeneratedValue
    private Integer id;

    private String typology;

    private Integer userId; // reflection? what if userId is a string? or smth else?

    private String objectType; //

    private Integer objectId; //

    private String payload;

    private Timestamp recordTimeStamp;

    public Record(@NotNull String typology, Integer userId, String object, Integer objectId, String payload){

        this.typology = typology;
        this.userId = userId;
        this.payload = payload;
        this.objectId = objectId;
        this.objectType = object;
        this.recordTimeStamp = new Timestamp(System.currentTimeMillis());

    }

    public Record(@NotNull String typology, Integer userId) {
        this.typology = typology;
        this.userId = userId;
        this.recordTimeStamp = new Timestamp(System.currentTimeMillis());
    }
}
