package it.uniroma2.ticketingsystem.logger.dao;

import it.uniroma2.ticketingsystem.logger.entity.mongo.Record;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository

@Configuration
@Profile("mongo")
public interface MongoRecordDao extends MongoRepository<Record,String> {
}

