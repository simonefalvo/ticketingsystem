package it.uniroma2.ticketingsystem.logger.dao;

import it.uniroma2.ticketingsystem.logger.entity.mongo.Record;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;

@Repository

@Configuration
@Profile("mongo")
public interface MongoRecordDao extends MongoRepository<Record,String> {

    List<Record> getRecordsByAuthor(@NotNull String author);
    List<Record> getRecordsByTag(@NotNull String tag);
    List<Record> getRecordsByOperationName(@NotNull String opname);

}

