package it.me.backyou.user.service;

import it.me.backyou.apikey.ApiKey;

import java.util.Set;

/**
 * Service interface for working with users database
 */
public interface IUserService {
    /**
     * Method registering new user
     *
     * @return new user id
     */
    long registerUser(String email, String password);

    /**
     * Method for getting all api keys of the user with specified id
     *
     * @param userId id of the needed user
     * @return set of specified user api keys
     */
    Set<ApiKey> getUserApiKeys(long userId);

    /**
     * Method adding new api key for the specified user
     *
     * @param userId id of the user asking for new apikey
     * @return new api key
     */
    String addNewApiKey(long userId);

    /**
     * Method for getting user with specified email id
     *
     * @param email users email
     * @return specified user id
     */
    long getUserId(String email, String password);

    /**
     * Method deleting users api key if it exists and belongs to specified user
     *
     * @param userId specified user id
     * @param apiKey specified api key
     */
    void deleteUserApiKey(long userId, String apiKey);
}
