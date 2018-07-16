package it.uniroma2.ticketingsystem.controller;

import it.uniroma2.ticketingsystem.dao.OggettoDao;
import it.uniroma2.ticketingsystem.entity.Oggetto;
import it.uniroma2.ticketingsystem.exception.EntitaNonTrovataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class OggettoController {

    @Autowired
    private OggettoDao oggettoDao;

    @Transactional
    public @NotNull Oggetto creaOggetto(@NotNull Oggetto oggetto){

        Oggetto oggettoSalvato = oggettoDao.save(oggetto);
        return oggettoSalvato;

    }

    @Transactional
    public @NotNull Oggetto aggiornaOggetto(@NotNull Integer id, @NotNull Oggetto datiAggiornati) throws EntitaNonTrovataException {
        Oggetto oggettoDaAggiornare = oggettoDao.getOne(id);
        if (oggettoDaAggiornare == null)
            throw new EntitaNonTrovataException();

        oggettoDaAggiornare.aggiorna(datiAggiornati);

        Oggetto oggettoAggiornato = oggettoDao.save(oggettoDaAggiornare);
        return oggettoAggiornato;
    }
    @Transactional
    public boolean eliminaOggetto(@NotNull Integer id){
        if(!oggettoDao.existsById(id)){
            return false;
        }

        oggettoDao.deleteById(id);
        return true;
    }

    public Oggetto cercaOggettoById(@NotNull Integer id){
        Oggetto oggettoTrovato = oggettoDao.getOne(id);
        return oggettoTrovato;
    }

    public List<Oggetto> prelevaOggetti() {
        return oggettoDao.findAll();
    }

}
