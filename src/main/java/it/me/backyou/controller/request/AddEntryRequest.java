package it.me.backyou.controller.request;

public class AddEntryRequest {
    private String[] columns;
    private String[] values;

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
