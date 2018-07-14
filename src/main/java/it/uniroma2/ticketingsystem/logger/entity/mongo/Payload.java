package it.uniroma2.ticketingsystem.logger.entity.mongo;


public class Payload {

    private String json;

    private String objectId;
    private String type;
    private String classType;


    public Payload(String json, String objectId, String type, String classType) {
        this.json = json;
        this.objectId = objectId;
        this.type = type;
        this.classType = classType;
    }
}
