package it.me.backyou.controller;

import it.me.backyou.service.ITableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class.
 * Mapped to respond to requests with tables( table creation, renaming etc.)
 */
@RestController
@RequestMapping(path = "table")
public class TableController {
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
        return tableService.getTableHeaderData(apiKey, tableName);
    }
}
