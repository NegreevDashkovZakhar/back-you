package it.me.backyou.controller;

import it.me.backyou.service.ColumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "column")
public class ColumnController {
    private final ColumnService columnService;

    @Autowired
    public ColumnController(final ColumnService columnService) {
        this.columnService = columnService;
    }

    @PostMapping(path = "/{apiKey}/{tableName}/{columnName}/{columnType}")
    public void addColumn(@PathVariable final String apiKey, @PathVariable final String tableName,
                          @PathVariable final String columnName, @PathVariable final String columnType) {
        columnService.addColumn(apiKey, tableName, columnName, columnType);
    }

    @PutMapping(path = "/{apiKey}/{tableName}/{oldName}/rename/{newName}")
    public void renameColumn(@PathVariable final String apiKey, @PathVariable final String tableName,
                             @PathVariable final String oldName, @PathVariable final String newName) {
        columnService.renameColumn(apiKey, tableName, oldName, newName);
    }

    @DeleteMapping(path = "/{apiKey}/{tableName}/{columnName}")
    public void deleteColumn(@PathVariable final String apiKey, @PathVariable final String tableName,
                             @PathVariable final String columnName) {
        columnService.deleteColumn(apiKey, tableName, columnName);
    }
}
