package it.me.backyou.controller;

import it.me.backyou.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "table")
public class TableController {
    private final TableService tableService;

    @Autowired
    public TableController(final TableService tableService) {
        this.tableService = tableService;
    }

    @PostMapping(path = "/{apiKey}/{tableName}")
    public void createTable(@PathVariable final String apiKey, @PathVariable final String tableName) {
        tableService.createTable(apiKey, tableName);
    }

    @DeleteMapping(path = "/{apiKey}/{tableName}")
    public void dropTable(@PathVariable final String apiKey, @PathVariable final String tableName) {
        tableService.dropTable(apiKey, tableName);
    }

    @PutMapping(path = "/{apiKey}/{oldName}/{newName}")
    public void renameTable(@PathVariable final String apiKey, @PathVariable final String oldName,
                            @PathVariable final String newName) {
        tableService.renameTable(apiKey, oldName, newName);
    }

    @GetMapping(path = "/{apiKey}/{tableName}")
    public Object getTableHeaderData(@PathVariable final String apiKey, @PathVariable final String tableName) {
        return tableService.getTableHeaderData(apiKey, tableName);
    }
}
