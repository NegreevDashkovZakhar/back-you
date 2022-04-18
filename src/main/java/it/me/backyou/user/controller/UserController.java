package it.me.backyou.user.controller;

import it.me.backyou.user.response.ApiKeysResponse;
import it.me.backyou.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RestController
@RequestMapping(path = "user")
public class UserController {
    private final IUserService userService;

    /**
     * Default constructor setting user service
     *
     * @param userService service that will be used
     */
    @Autowired
    public UserController(final IUserService userService) {
        this.userService = userService;
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
        return userService.registerUser(email, password);
    }

    /**
     * Method returning all api keys hold by user
     *
     * @param userId id of the specified user
     * @return List of user api keys
     */
    @GetMapping(path = "/apiKey")
    public ApiKeysResponse getUserApiKeys(@RequestBody final Long userId) {
        return new ApiKeysResponse(userService.getUserApiKeys(userId));
    }

    /**
     * Method adding new api key for user
     *
     * @param userId specified user id
     * @return new api key as string
     */
    @PostMapping(path = "/apiKey")
    public String addApiKey(@RequestBody final Long userId) {
        return userService.addNewApiKey(userId);
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
        return userService.getUserId(email, password);
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

    }
}
