package com.cefetqps.spring.services.clients;

import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.cefetqps.spring.Constants;
import com.cefetqps.spring.models.User;
import com.cefetqps.spring.services.interfaces.DatabaseClient;

@Service
@Scope("singleton")
public class UserDatabaseClient implements DatabaseClient<User> {

    private ArrayList<User> userDataBaseMockupList;
    private AtomicInteger userIdIncrementer;

    public UserDatabaseClient() {
        this.userDataBaseMockupList = getStaticMockupUsers();
        this.userIdIncrementer = new AtomicInteger(Constants.ID_INCREMENTER_START_OFFSET);
    }

    public boolean getConnetion(){
        return true;
    }

    public ArrayList<User> getAll(){
        return userDataBaseMockupList;
    }

    public Optional<User> getById(int id){
        return Optional.ofNullable(userDataBaseMockupList.get(id));
    }

    public boolean save(User user){
        try{
            user.setId(userIdIncrementer.incrementAndGet());
            return userDataBaseMockupList.add(user);
        }
        catch(Exception ex){
            return false;
        }
    }

    private ArrayList<User> getStaticMockupUsers(){
        ArrayList<User> staticMockupUsers = new ArrayList<User>();
        staticMockupUsers.add(getStaticUser("bruno"));
        staticMockupUsers.add(getStaticUser("giulio"));
        staticMockupUsers.add(getStaticUser("heitor"));
        return staticMockupUsers;
    }

    private User getStaticUser(String name){
        String email = String.format(Constants.RANDOM_USER_EMAIL_TEMPLATE, name);
        return new User(userIdIncrementer.incrementAndGet(), email, "12345");
    }
}
