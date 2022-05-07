package it.me.backyou.storage.controller;

import it.me.backyou.storage.controller.request.AddEntryRequest;
import it.me.backyou.storage.service.entry.IEntryService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class.
 * Mapped to respond to requests with entries (insertion, getting etc.)
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "entry")
public class EntryController {
    private static final Log LOG = LogFactory.getLog(EntryController.class);
    private final IEntryService entryService;

    /**
     * Default constructor setting entry service for controller
     *
     * @param entryService entry service for controller
     */
    @Autowired
    public EntryController(final IEntryService entryService) {
        this.entryService = entryService;
        LOG.info("Created entry controller with service:" + entryService);
    }

    /**
     * Endpoint for getting all entries in table
     *
     * @param apiKey    user api key
     * @param tableName name of the table with needed entries
     * @return object containing all entries of the table
     */
    @GetMapping(path = "/{apiKey}/{tableName}")
    public Object getAllEntries(@PathVariable final String apiKey, @PathVariable final String tableName) {
        LOG.info("Getting all entries for api key:" + apiKey + " table name:" + tableName);
        return entryService.getAllEntries(apiKey, tableName);
    }

    /**
     * Endpoint for adding entry to table
     *
     * @param apiKey          user api key
     * @param tableName       name of the table, to which entry will be added
     * @param addEntryRequest body of the request holding values and columns for entry
     */
    @PostMapping(path = "/{apiKey}/{tableName}")
    public void addEntry(@PathVariable final String apiKey, @PathVariable final String tableName,
                         @RequestBody final AddEntryRequest addEntryRequest) {
        LOG.info("adding entry with api key:" + apiKey + " table name:" + tableName + " request:" + addEntryRequest);
        entryService.addEntry(apiKey, tableName, addEntryRequest.getColumns(), addEntryRequest.getValues());
    }

    /**
     * Endpoint for deletion of entries. Deletes all entries which specified column holds specified value
     *
     * @param apiKey     user api key
     * @param tableName  name of the table with entries
     * @param columnName name of the column. Entries with specified value in this column will be deleted
     * @param value      value for specified. If column holds this column, then it will be deleted
     */
    @DeleteMapping(path = "/{apiKey}/{tableName}/{columnName}/{value}")
    public void deleteEntries(@PathVariable final String apiKey, @PathVariable final String tableName,
                              @PathVariable final String columnName, @PathVariable final String value) {
        LOG.info("deleting entries with api key:" + apiKey + " table name:" + tableName +
                " condition column:" + columnName + " condition value:" + value);
        entryService.deleteEntries(apiKey, tableName, columnName, value);
    }
}
