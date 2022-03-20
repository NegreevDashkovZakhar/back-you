package it.me.backyou.service;

public class ServiceUtils {
    public static String getTableName(final String apiKey, final String tableName) {
        return apiKey + "__" + tableName;
    }
}
