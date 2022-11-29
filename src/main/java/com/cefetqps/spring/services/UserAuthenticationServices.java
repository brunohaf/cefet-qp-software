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

    public boolean login(User user){
        User userStoredData = userServices.getByEmail(user.getEmail());

        return userSecretServices.decodeUserSecret(user.getPassword()) == 
            userSecretServices.decodeUserSecret(userStoredData.getPassword());
    }
}
