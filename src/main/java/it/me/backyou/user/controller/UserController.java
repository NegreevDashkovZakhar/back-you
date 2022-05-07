package it.me.backyou.user.controller;

import it.me.backyou.user.response.ApiKeysResponse;
import it.me.backyou.user.service.IUserService;
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
 * Controller for performing all user connected actions.
 * Such as registration, api keys management etc.
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "user")
public class UserController {
    private static final Log LOG = LogFactory.getLog(UserController.class);
    private final IUserService userService;

    /**
     * Default constructor setting user service
     *
     * @param userService service that will be used
     */
    @Autowired
    public UserController(final IUserService userService) {
        this.userService = userService;
        LOG.info("created user controller with service:" + userService);
    }

    /**
     * Method registering new user
     *
     * @param email    new user email
     * @param password new user password
     * @return id of the new user
     */
    @PostMapping(path = "/register/{email}/{password}")
    public Long registerUser(@PathVariable final String email, @PathVariable final String password) {
        LOG.info("registering user with email:" + email + " password:" + password);
        Long userId = userService.registerUser(email, password);
        LOG.info("user email:" + email + " password:" + password + " got id:" + userId);
        return userId;
    }

    /**
     * Method returning all api keys hold by user
     *
     * @param userId id of the specified user
     * @return List of user api keys
     */
    @GetMapping(path = "/apiKey/{userId}")
    public ApiKeysResponse getUserApiKeys(@PathVariable final Long userId) {
        LOG.info("get api keys with user id:" + userId);
        ApiKeysResponse apiKeysResponse = new ApiKeysResponse(userService.getUserApiKeys(userId));
        LOG.info("user with id:" + userId + " api keys are:" + apiKeysResponse);
        return apiKeysResponse;
    }

    /**
     * Method adding new api key for user
     *
     * @param userId specified user id
     * @return new api key as string
     */
    @PostMapping(path = "/apiKey")
    public String addApiKey(@RequestBody final Long userId) {
        LOG.info("adding api key to user with id:" + userId);
        String apiKey = userService.addNewApiKey(userId);
        LOG.info("user with id:" + userId + " got api key:" + apiKey);
        return apiKey;
    }

    /**
     * Method retrieving user id from its email and password
     *
     * @param email    specified user email
     * @param password specified user password
     * @return specified user id
     */
    @GetMapping(path = "/{email}/{password}")
    public Long getUserId(@PathVariable final String email, @PathVariable final String password) {
        LOG.info("getting user id from email:" + email + " password:" + password);
        Long userId = userService.getUserId(email, password);
        LOG.info("user id from email:" + email + " password:" + password + " got id:" + userId);
        return userId;
    }

    /**
     * Method deleting users api key
     *
     * @param email    user email
     * @param password user password
     * @param apiKey   api key to be deleted
     */
    @DeleteMapping(path = "/apiKey/{email}/{password}")
    public void deleteApiKey(@PathVariable final String email, @PathVariable final String password,
                             @RequestBody final String apiKey) {
        LOG.info("deleting user api key with email:" + email + " password:" + password + " api key:" + apiKey);
        long userId = userService.getUserId(email, password);
        LOG.info("searched email:" + email + " password:" + password + " to be id:" + userId);
        userService.deleteUserApiKey(userId, apiKey);
    }
}
