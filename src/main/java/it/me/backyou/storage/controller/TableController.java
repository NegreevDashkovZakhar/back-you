package it.me.backyou.storage.controller;

import it.me.backyou.storage.service.table.ITableService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class.
 * Mapped to respond to requests with tables( table creation, renaming etc.)
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "table")
public class TableController {
    private final static Log LOG = LogFactory.getLog(TableController.class);
    private final ITableService tableService;

    /**
     * Default constructor setting table service
     *
     * @param tableService table service for controller
     */
    @Autowired
    public TableController(final ITableService tableService) {
        this.tableService = tableService;
    }

    /**
     * Endpoint for table creation
     *
     * @param apiKey    user api key
     * @param tableName name of the table that will be created
     */
    @PostMapping(path = "/{apiKey}/{tableName}")
    public void createTable(@PathVariable final String apiKey, @PathVariable final String tableName) {
        LOG.info("Received create table request with api key:" + apiKey + " table name:" + tableName);
        tableService.createTable(apiKey, tableName);
    }

    /**
     * Endpoint for table creation. Deletes table with specified name
     *
     * @param apiKey    user api key
     * @param tableName name of the table that will be deleted
     */
    @DeleteMapping(path = "/{apiKey}/{tableName}")
    public void dropTable(@PathVariable final String apiKey, @PathVariable final String tableName) {
        LOG.info("Received delete table request with api key:" + apiKey + " table name:" + tableName);
        tableService.dropTable(apiKey, tableName);
    }

    /**
     * Endpoint for renaming table
     *
     * @param apiKey  user api key
     * @param oldName old name of the table that will be renamed
     * @param newName new name for the specified table
     */
    @PutMapping(path = "/{apiKey}/{oldName}/{newName}")
    public void renameTable(@PathVariable final String apiKey, @PathVariable final String oldName,
                            @PathVariable final String newName) {
        LOG.info("Received rename table request with api key:" + apiKey + " old name:" + oldName + " new name:" + newName);
        tableService.renameTable(apiKey, oldName, newName);
    }

    /**
     * Endpoint for getting table header data
     *
     * @param apiKey    user api key
     * @param tableName name of the needed table
     * @return object with data about table header
     */
    @GetMapping(path = "/{apiKey}/{tableName}")
    public Object getTableHeaderData(@PathVariable final String apiKey, @PathVariable final String tableName) {
        LOG.info("Received get table header data request with api key:" + apiKey + " table name:" + tableName);
        return tableService.getTableHeaderData(apiKey, tableName);
    }
}
