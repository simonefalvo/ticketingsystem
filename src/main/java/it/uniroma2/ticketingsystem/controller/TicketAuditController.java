package it.uniroma2.ticketingsystem.controller;

import it.uniroma2.ticketingsystem.aud.TicketAudit;
import it.uniroma2.ticketingsystem.dao.TicketAuditDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Service
@Repository
public class TicketAuditController {

    @Autowired
    private TicketAuditDao ticketAuditDao;

    @Transactional
    public @NotNull TicketAudit creaTicketAud(@NotNull TicketAudit ticketAudit){

        TicketAudit ticketAuditSalvato = ticketAuditDao.save(ticketAudit);
        return ticketAuditSalvato;

    }

    @Transactional
    public void registraTicketInsert(TicketAudit ticketAudit) {
        ticketAuditDao.save(ticketAudit);
    }

    @Transactional
    public void registraTicketDelete(TicketAudit ticketAudit){
        ticketAuditDao.save(ticketAudit);
    }

    @Transactional
    public void registraTicketEdit(TicketAudit ticketAudit){
        ticketAuditDao.save(ticketAudit);
    }

    public List<TicketAudit> prelevaTicketAuds() {
        return ticketAuditDao.findAll();
    }


    public Integer numberOfStatusTickets(String status){
        return ticketAuditDao.numberOfStatusTickets(status);
    }

    public Integer dailyTickets(String t, String stato) throws ParseException {
        SimpleDateFormat datetimeFormatter1 = new SimpleDateFormat("yyyy-MM-dd");
        Date d3 = datetimeFormatter1.parse(t); //parso la stringa e la trasformo in data
        //System.out.println("gpsdate :" + d3);
        Timestamp ts = new Timestamp(d3.getTime());
        //System.out.println(ts);
        return ticketAuditDao.dailyTickets(ts,stato);
    }

    public Integer ticketInIntervallo(String t1, String t2, String stato) throws ParseException {
        SimpleDateFormat datetimeFormatter1 = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = datetimeFormatter1.parse(t1); //parso la stringa e la trasformo in data
        Date d2 = datetimeFormatter1.parse(t2);
        //System.out.println("gpsdate :" + d3);
        Timestamp ts1 = new Timestamp(d1.getTime());
        Timestamp ts2 = new Timestamp(d2.getTime());
        //System.out.println(ts);
        return ticketAuditDao.ticketInIntervallo(ts1,ts2,stato);
    }

    public List<TicketAudit> getTicketAudsByTicketId(Integer ticketId) {
        return  ticketAuditDao.getTicketAudsByTicketId(ticketId);
    }

    public TicketAudit getTicketAudById(Integer id) {
        return ticketAuditDao.getOne(id);
    }
}
