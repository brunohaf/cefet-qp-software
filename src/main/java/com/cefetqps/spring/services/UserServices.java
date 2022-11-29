package com.cefetqps.spring.services;

import java.util.ArrayList;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.cefetqps.spring.models.User;
import com.cefetqps.spring.utils.PersistenceUtils;

@Service
@Scope("singleton")
public class UserServices {

    private ArrayList<User> userDataBase;

    private final String randomUserNameTemplate = "user%d";
    private final Random randomNumberGenerator;
    private UserSecretServices userSecretServices;

    @Autowired
    public UserServices(UserSecretServices userSecretServices, PersistenceUtils persistenceUtils) {
        this.randomNumberGenerator = new Random();
        this.userSecretServices = userSecretServices;
        this.userDataBase = persistenceUtils.userDataBaseMockupList;

    }

    public boolean saveUserData(User user){
        try{
            return userDataBase.add(user);
        }
        catch(Exception ex){
            return false;
        }
    }

    public User getById(int id){
        try{
            return userDataBase.get(id);
        }
        catch(IndexOutOfBoundsException indexOutOfBoundsException){
            return generateRandomUser();
        }
    }

    public ArrayList<User> getAll(){
        return userDataBase;
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
            PersistenceUtils.RANDOM_USER_EMAIL_TEMPLATE,
                randomUserName)
            );
        return user;
    }
}
