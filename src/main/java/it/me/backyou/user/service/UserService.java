package it.me.backyou.user.service;

import it.me.backyou.apikey.ApiKey;
import it.me.backyou.apikey.repository.ApiKeyRepository;
import it.me.backyou.user.User;
import it.me.backyou.user.exception.ApiKeyOrUserNotFoundException;
import it.me.backyou.user.exception.UserAlreadyExistException;
import it.me.backyou.user.exception.UserNotFoundException;
import it.me.backyou.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Service working with users logic such as registering users, api keys management etc.
 */
@Service
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final ApiKeyRepository apiKeyRepository;

    /**
     * Default service constructor initializing needed repositories
     *
     * @param userRepository   user repository for working with users table
     * @param apiKeyRepository api key repository for working with api keys table
     */
    @Autowired
    public UserService(final UserRepository userRepository, final ApiKeyRepository apiKeyRepository) {
        this.userRepository = userRepository;
        this.apiKeyRepository = apiKeyRepository;
    }

    @Override
    public long registerUser(final String email, final String password) {
        if (userExist(email)) {
            throw new UserAlreadyExistException();
        }
        User user = new User(email, password);
        userRepository.save(user);
        return user.getUserId();
    }

    @Override
    public Set<ApiKey> getUserApiKeys(final long userId) {
        try {
            User user = userRepository.getById(userId);
            return user.getApiKeys();
        } catch (Exception e) {
            throw new UserNotFoundException();
        }
    }

    @Override
    public String addNewApiKey(final long userId) {
        try {
            ApiKey apiKey = new ApiKey();
            User user = userRepository.getById(userId);
            apiKey.setUser(user);
            apiKeyRepository.save(apiKey);
            return apiKey.getApiKey();
        } catch (Exception e) {
            throw new UserNotFoundException();
        }
    }

    @Override
    public long getUserId(final String email, final String password) {
        try {
            return userRepository.getUserId(email, password);
        } catch (Exception e) {
            throw new UserNotFoundException();
        }
    }

    @Override
    public void deleteUserApiKey(final long userId, final String apiKey) {
        try {
            ApiKey apiKeyInstance = apiKeyRepository.getApiKeyByValue(apiKey);
            if (apiKeyInstance.getUser().getUserId() != userId) {
                throw new ApiKeyOrUserNotFoundException();
            }
            apiKeyRepository.delete(apiKeyInstance);
        } catch (Exception e) {
            throw new ApiKeyOrUserNotFoundException();
        }
    }

    private boolean userExist(final String email) {
        try {
            return userRepository.userExist(email) == 1;
        } catch (Exception e) {
            return false;
        }
    }
}
