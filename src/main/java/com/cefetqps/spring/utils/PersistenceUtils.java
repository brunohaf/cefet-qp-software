package com.cefetqps.spring.utils;

import java.util.ArrayList;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.cefetqps.spring.models.User;

@Service
@Scope("singleton")
public class PersistenceUtils {

    public ArrayList<User> userDataBaseMockupList = getStaticMockupUsers();

    public static final String RANDOM_USER_EMAIL_TEMPLATE = "%s@email.com.br";

    private ArrayList<User> getStaticMockupUsers(){
        ArrayList<User> staticMockupUsers = new ArrayList<User>();
        staticMockupUsers.add(getStaticUser("bruno"));
        staticMockupUsers.add(getStaticUser("giulio"));
        staticMockupUsers.add(getStaticUser("heitor"));
        return staticMockupUsers;
    }

    private User getStaticUser(String name){
        String email = String.format(RANDOM_USER_EMAIL_TEMPLATE, name);
        return new User(email, "12345");
    }
}
