package it.me.backyou.service;

import it.me.backyou.repository.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TableService {
    private final TableRepository tableRepository;

    @Autowired
    public TableService(final TableRepository tableRepository) {
        this.tableRepository = tableRepository;
    }

    public void createTable(final String apiKey, final String tableName) {
        tableRepository.createTable(ServiceUtils.getTableName(apiKey, tableName));
    }

    public void dropTable(final String apiKey, final String tableName) {
        tableRepository.dropTable(ServiceUtils.getTableName(apiKey, tableName));
    }

    public void renameTable(final String apiKey, final String oldName, final String newName) {
        String oldResult = ServiceUtils.getTableName(apiKey, oldName);
        String newResult = ServiceUtils.getTableName(apiKey, newName);
        tableRepository.renameTable(oldResult, newResult);
    }

    public Object getTableHeaderData(final String apiKey, final String tableName) {
        return tableRepository.getTableHeaderData(ServiceUtils.getTableName(apiKey, tableName));
    }

}
