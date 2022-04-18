package it.me.backyou.storage.service;

import it.me.backyou.storage.controller.exception.InvalidArgumentException;

/**
 * Helper class.
 * Does unified service jobs
 */
public class ServiceUtils {
    /**
     * Method for creation final name of the table in database
     *
     * @param apiKey    user api key
     * @param tableName name of the table(as user sees it)
     * @return final name of the table in database
     */
    public static String getTableName(final String apiKey, final String tableName) {
        if (tableName.equals("") || apiKey.equals("")) {
            throw new InvalidArgumentException();
        }
        return apiKey + "__" + tableName;
    }
}
