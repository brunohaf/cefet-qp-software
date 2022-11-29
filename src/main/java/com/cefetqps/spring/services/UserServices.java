package com.cefetqps.spring.services;

import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.cefetqps.spring.Constants;
import com.cefetqps.spring.models.User;
import com.cefetqps.spring.services.interfaces.DatabaseClient;

@Service
@Scope("singleton")
public class UserServices {

    private DatabaseClient<User> userDatabaseClient;

    private final String randomUserNameTemplate = "user%d";
    private final Random randomNumberGenerator;
    private UserSecretServices userSecretServices;

    @Autowired
    public UserServices(
        UserSecretServices userSecretServices,
        DatabaseClient<User> userDatabaseClient) {
        this.randomNumberGenerator = new Random();
        this.userSecretServices = userSecretServices;
        this.userDatabaseClient = userDatabaseClient;
    }

    public boolean saveUserData(User user){
       return userDatabaseClient.save(user);
    }

    public Optional<User> getById(int id){
        try{
            return userDatabaseClient.getById(id);
        }
        catch(IndexOutOfBoundsException indexOutOfBoundsException){
            return Optional.of(generateRandomUser());
        }
    }

    public User getByEmail(String email){
        HashMap<String, User> userHashMap = indexUserDataBaseByEmail();
        User fetchedUser = userHashMap.get(email);
        return  fetchedUser != null ? fetchedUser : generateRandomUser();

    }

    public Collection<User> getAll(){
        return userDatabaseClient.getAll();
    }

    private HashMap<String, User> indexUserDataBaseByEmail(){
        HashMap<String, User> userHashMap = new HashMap<String, User>();
        for (User user : this.getAll()) {
            userHashMap.put(user.getEmail(), user);
        }
        return userHashMap;
    }

    public User generateRandomUser(){
        User user = new User();
        String randomUserName = String.format(
            randomUserNameTemplate, 
            randomNumberGenerator.nextInt() & Integer.MAX_VALUE
            );

        user.setPassword(userSecretServices
            .encodeUserSecret(randomUserName)
            );

        user.setEmail(String.format(
            Constants.RANDOM_USER_EMAIL_TEMPLATE,
                randomUserName)
            );
        return user;
    }
}
