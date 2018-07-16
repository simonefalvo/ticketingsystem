package it.uniroma2.ticketingsystem.logger.entity.mongo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Set;

@Document
@NoArgsConstructor
@Getter
@Setter
public class Record {

    private @Id String id;
    private String operationName;
    private String author;
    private String tag;
    private LocalDateTime timestamp;
    private Set<Payload> payloads;


    public Record(@NotNull String operationName, String author, String tag){
        this.operationName = operationName;
        this.author = author;
        this.timestamp = LocalDateTime.now();
        this.tag = tag;
    }

    public Record(@NotNull String operationName, String author, String tag, Set<Payload> payloads) {
        this.operationName = operationName;
        this.author = author;
        this.tag = tag;
        this.payloads = payloads;
        this.timestamp = LocalDateTime.now();
    }

}
