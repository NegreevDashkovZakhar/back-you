package it.me.backyou.service;

import it.me.backyou.repository.EntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EntryService {
    private final EntryRepository entryRepository;

    @Autowired
    public EntryService(final EntryRepository entryRepository) {
        this.entryRepository = entryRepository;
    }

    public Object getAllEntries(final String apiKey, final String tableName) {
        return entryRepository.getAllEntries(ServiceUtils.getTableName(apiKey, tableName));
    }

    public void addEntry(final String apiKey, final String tableName, final String[] columns, final String[] values) {
        entryRepository.addEntry(ServiceUtils.getTableName(apiKey, tableName), columns, values);
    }

    public void deleteEntries(final String apiKey, final String tableName, final String columnName,
                              final String value) {
        entryRepository.removeEntries(ServiceUtils.getTableName(apiKey, tableName), columnName, value);
    }
}
