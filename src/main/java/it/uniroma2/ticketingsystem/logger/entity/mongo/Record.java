package it.uniroma2.ticketingsystem.logger.entity.mongo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Set;

@Document
@NoArgsConstructor
@Getter
@Setter
public class Record {

    @Id
    private String id;

    private String operationName;
    private String author;
    private String tag;
    private Timestamp timestamp;

    private Set<Payload> payloads;

    public Record(@NotNull String operationName, String author, String tag){

        this.operationName = operationName;
        this.author = author;
        this.timestamp = new Timestamp(System.currentTimeMillis());
        this.tag = tag;

    }

    public Record(String operationName, String author, String tag, Set<Payload> payloads) {
        this.operationName = operationName;
        this.author = author;
        this.tag = tag;
        this.payloads = payloads;
        this.timestamp = new Timestamp(System.currentTimeMillis());
    }

    public void addPayload(Payload payload){
        this.payloads.add(payload);
    }
}
