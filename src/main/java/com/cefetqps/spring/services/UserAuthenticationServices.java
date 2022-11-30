package com.cefetqps.spring.services;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.cefetqps.spring.models.User;

@Service
@Scope("singleton")
public class UserAuthenticationServices {

    private UserSecretServices userSecretServices;
    private UserServices userServices;

    public UserAuthenticationServices(
        UserSecretServices userSecretServices,
        UserServices userServices) {
        this.userSecretServices = userSecretServices;
        this.userServices = userServices;
    }

    public boolean login(User user) {
        User userStoredData = userServices.getByEmail(user.getEmail());

        if (userStoredData == null) {
            return false;
        }

        return user.getPassword().equals(
                userSecretServices.decodeUserSecret(userStoredData.getPassword()));
    }
    
    public boolean register(User user) {
        if (user.getEmail() == "" || user.getPassword() == "") {
            throw new IllegalArgumentException("Email and password must be provided");
        }

        User userStoredData = userServices.getByEmail(user.getEmail());

        if (userStoredData != null) {
            return false;
        }
        
        user.setPassword(userSecretServices.encodeUserSecret(user.getPassword()));
        userServices.saveUserData(user);
        
        return true;
    }
}
