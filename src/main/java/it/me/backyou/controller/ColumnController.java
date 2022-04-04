package it.me.backyou.controller;

import it.me.backyou.service.IColumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class.
 * Mapped to respond to requests connected with columns (creation, deletion etc.)
 */
@RestController
@RequestMapping(path = "column")
public class ColumnController {
    private final IColumnService columnService;

    /**
     * Default constructor setting column service
     *
     * @param columnService column service for controller
     */
    @Autowired
    public ColumnController(final IColumnService columnService) {
        this.columnService = columnService;
    }

    /**
     * Endpoint for columns creation
     *
     * @param apiKey     user api key
     * @param tableName  name of the table, which will be extended with column
     * @param columnName name of the column
     * @param columnType type of the column
     */
    @PostMapping(path = "/{apiKey}/{tableName}/{columnName}/{columnType}")
    public void addColumn(@PathVariable final String apiKey, @PathVariable final String tableName,
                          @PathVariable final String columnName, @PathVariable final String columnType) {
        columnService.addColumn(apiKey, tableName, columnName, columnType);
    }

    /**
     * Endpoint for renaming columns
     *
     * @param apiKey    user api key
     * @param tableName name of the table with column
     * @param oldName   old name of the column
     * @param newName   new name for the column
     */
    @PutMapping(path = "/{apiKey}/{tableName}/{oldName}/rename/{newName}")
    public void renameColumn(@PathVariable final String apiKey, @PathVariable final String tableName,
                             @PathVariable final String oldName, @PathVariable final String newName) {
        columnService.renameColumn(apiKey, tableName, oldName, newName);
    }

    /**
     * Endpoint for column deletion
     *
     * @param apiKey     user api key
     * @param tableName  name of the table with column
     * @param columnName name of the column to delete
     */
    @DeleteMapping(path = "/{apiKey}/{tableName}/{columnName}")
    public void deleteColumn(@PathVariable final String apiKey, @PathVariable final String tableName,
                             @PathVariable final String columnName) {
        columnService.deleteColumn(apiKey, tableName, columnName);
    }

    /**
     * Endpoint to change column type
     *
     * @param apiKey     user api key
     * @param tableName  name of the table with column
     * @param columnName name of the column, which type will be changed
     * @param newType    new type of the specified column
     */
    @PutMapping(path = "/{apiKey}/{tableName}/{columnName}/type/{newType}")
    public void changeColumnType(@PathVariable final String apiKey, @PathVariable final String tableName,
                                 @PathVariable final String columnName, @PathVariable final String newType) {
        columnService.changeColumnType(apiKey, tableName, columnName, newType);
    }

    /**
     * Endpoint for setting default value of the column
     *
     * @param apiKey       user api key
     * @param tableName    name of the table with column
     * @param columnName   name of the column
     * @param defaultValue value that will be set as default for specified column
     */
    @PutMapping(path = "/{apiKey}/{tableName}/{columnName}/default/{defaultValue}")
    public void changeColumnDefault(@PathVariable final String apiKey, @PathVariable final String tableName,
                                    @PathVariable final String columnName, @PathVariable final String defaultValue) {
        columnService.changeColumnDefault(apiKey, tableName, columnName, defaultValue);
    }
}
