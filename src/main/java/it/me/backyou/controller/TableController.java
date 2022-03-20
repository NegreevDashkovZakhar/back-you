package it.me.backyou.controller;

import it.me.backyou.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
}
