package it.me.backyou.controller;

import it.me.backyou.controller.request.AddEntryRequest;
import it.me.backyou.service.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "entry")
public class EntryController {
    private final EntryService entryService;

    @Autowired
    public EntryController(final EntryService entryService) {
        this.entryService = entryService;
    }

    @GetMapping(path = "/{apiKey}/{tableName}")
    public Object getAllEntries(@PathVariable final String apiKey, @PathVariable final String tableName) {
        return entryService.getAllEntries(apiKey, tableName);
    }

    @PostMapping(path = "/{apiKey}/{tableName}")
    public void addEntry(@PathVariable final String apiKey, @PathVariable final String tableName,
                         @RequestBody final AddEntryRequest addEntryRequest) {
        entryService.addEntry(apiKey, tableName, addEntryRequest.getColumns(), addEntryRequest.getValues());
    }

    @DeleteMapping(path = "/{apiKey}/{tableName}/{columnName}/{value}")
    public void deleteEntries(@PathVariable final String apiKey, @PathVariable final String tableName,
                              @PathVariable final String columnName, @PathVariable final String value) {
        entryService.deleteEntries(apiKey, tableName, columnName, value);
    }
}
