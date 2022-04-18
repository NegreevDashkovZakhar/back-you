package it.me.backyou.storage.controller.request;

/**
 * Class mapped to request body to add entry
 * holds columns and values, in insert query values will be added according to columns respectively
 */
public class AddEntryRequest {
    private String[] columns;
    private String[] values;

    /**
     * Constructor initializing all fields
     * Used to map JSON
     *
     * @param columns columns to which values will be mapped respectively
     * @param values  values which will be added to database
     */
    public AddEntryRequest(final String[] columns, final String[] values) {
        this.columns = columns;
        this.values = values;
    }

    public String[] getColumns() {
        return columns;
    }

    public void setColumns(final String[] columns) {
        this.columns = columns;
    }

    public String[] getValues() {
        return values;
    }

    public void setValues(final String[] values) {
        this.values = values;
    }
}
