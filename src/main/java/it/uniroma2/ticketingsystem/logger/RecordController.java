package it.uniroma2.ticketingsystem.logger;

import it.uniroma2.ticketingsystem.logger.aspect.KeyId;
import it.uniroma2.ticketingsystem.logger.entity.Record;
import it.uniroma2.ticketingsystem.logger.utils.AspectUtils;
import it.uniroma2.ticketingsystem.logger.utils.ObjSer;
import it.uniroma2.ticketingsystem.logger.utils.ReflectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

@Service
public class RecordController {

    @Autowired
    private RecordDao recordDao;

    @Transactional
    public @NotNull Record createRecord(@NotNull Record record) {
        return recordDao.save(record);
    }

    public boolean deleteRecord(@NotNull Integer id) {
        if (!recordDao.existsById(id)) {
            return false;
        }
        recordDao.deleteById(id);
        return true;
    }

    public List<Record> getAllRecords() {
        return recordDao.findAll();
    }

    public Record findRecordById(@NotNull Integer id) {
        if (!recordDao.existsById(id)) {
            return null;
        }
        return recordDao.getOne(id);
    }

    public List<Record> getRecordsByTag(@NotNull String tag) {
        return recordDao.getRecordsByTag(tag);
    }

    public List<Record> getRecordsByAuthor(@NotNull String author) {
        return recordDao.getRecordsByAuthor(author);
    }

    public List<Record> getRecordsByObjectId(Object object) {
        System.err.println(object.toString());
        String[] idParams = ReflectUtils.getIDParameters(object);
        System.err.println(idParams);
        String objectId = null;
        if (AspectUtils.hasAnnotation(object, KeyId.class) != null)
            System.err.println("HA L'ANNOTAZIONE @KEYID");
        else
            System.err.println("NON HA L'ANNOTAZIONE @KEYID");

        try {
            objectId = ObjSer.buildIDJson(object, idParams);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.err.println(objectId);

        return recordDao.getRecordsByObjectId(objectId);
    }
}