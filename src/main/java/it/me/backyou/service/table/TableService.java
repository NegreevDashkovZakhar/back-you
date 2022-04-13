package it.me.backyou.service.table;

import it.me.backyou.repository.table.ITableRepository;
import it.me.backyou.service.ServiceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class
 * Holds logic for working with tables in database
 */
@Service
public class TableService implements ITableService {
    private final ITableRepository tableRepository;

    /**
     * Default constructor setting table repository
     *
     * @param tableRepository table repository for working with database
     */
    @Autowired
    public TableService(final ITableRepository tableRepository) {
        this.tableRepository = tableRepository;
    }

    @Override
    public void createTable(final String apiKey, final String tableName) {
        tableRepository.createTable(ServiceUtils.getTableName(apiKey, tableName));
    }

    @Override
    public void dropTable(final String apiKey, final String tableName) {
        tableRepository.dropTable(ServiceUtils.getTableName(apiKey, tableName));
    }

    @Override
    public void renameTable(final String apiKey, final String oldName, final String newName) {
        String oldResult = ServiceUtils.getTableName(apiKey, oldName);
        String newResult = ServiceUtils.getTableName(apiKey, newName);
        tableRepository.renameTable(oldResult, newResult);
    }

    @Override
    public Object getTableHeaderData(final String apiKey, final String tableName) {
        return tableRepository.getTableHeaderData(ServiceUtils.getTableName(apiKey, tableName));
    }

}
