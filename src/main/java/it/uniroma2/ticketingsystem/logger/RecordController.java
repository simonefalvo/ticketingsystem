package it.uniroma2.ticketingsystem.logger;

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
}