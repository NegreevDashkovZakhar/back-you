package it.me.backyou.service.entry;

import it.me.backyou.repository.entry.IEntryRepository;
import it.me.backyou.service.ServiceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class
 * Holds logic for working with entries in database
 */
@Service
public class EntryService implements IEntryService {
    private final IEntryRepository entryRepository;

    /**
     * Default constructor setting entry repository for working with database
     *
     * @param entryRepository entry repository for working with database
     */
    @Autowired
    public EntryService(final IEntryRepository entryRepository) {
        this.entryRepository = entryRepository;
    }

    @Override
    public Object getAllEntries(final String apiKey, final String tableName) {
        return entryRepository.getAllEntries(ServiceUtils.getTableName(apiKey, tableName));
    }

    @Override
    public void addEntry(final String apiKey, final String tableName, final String[] columns, final String[] values) {
        entryRepository.addEntry(ServiceUtils.getTableName(apiKey, tableName), columns, values);
    }

    @Override
    public void deleteEntries(final String apiKey, final String tableName, final String columnName,
                              final String value) {
        entryRepository.removeEntries(ServiceUtils.getTableName(apiKey, tableName), columnName, value);
    }
}
