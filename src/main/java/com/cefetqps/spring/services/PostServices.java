package com.cefetqps.spring.services;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.cefetqps.spring.models.Post;
import com.cefetqps.spring.services.clients.UserDatabaseClient;
import com.cefetqps.spring.services.interfaces.DatabaseClient;

@Service
@Scope("singleton")
public class PostServices {

    private DatabaseClient<Post> postDatabaseClient;

    @Autowired
    public PostServices(
            DatabaseClient<Post> postDatabaseClient) {
        this.postDatabaseClient = postDatabaseClient;
    }

    public PostServices(UserDatabaseClient userDatabaseClient) {
    }

    public boolean savePostData(Post Post) {
        return postDatabaseClient.save(Post);
    }

    public Optional<Post> getById(int id) {
        return postDatabaseClient.getById(id);
    }

    public ArrayList<Post> getAll() {
        return new ArrayList<Post>(
                postDatabaseClient.getAll().stream()
                        .collect(Collectors.toList()));
    }

    public ArrayList<Post> getByUserId(int userId) {
        return new ArrayList<Post>(
                postDatabaseClient.getAll().stream()
                        .filter(n -> n.getUserId() == userId)
                        .collect(Collectors.toList()));
    }
}
