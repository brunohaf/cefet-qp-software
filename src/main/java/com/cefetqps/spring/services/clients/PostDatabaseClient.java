package com.cefetqps.spring.services.clients;

import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.cefetqps.spring.Constants;
import com.cefetqps.spring.models.Post;
import com.cefetqps.spring.services.interfaces.DatabaseClient;

@Service
@Scope("singleton")
public class PostDatabaseClient implements DatabaseClient<Post> {

    private ArrayList<Post> postDataBaseMockupList;
    private AtomicInteger postIdIncrementer = new AtomicInteger(Constants.ID_INCREMENTER_START_OFFSET);

    public PostDatabaseClient() {
        this.postDataBaseMockupList = getStaticMockupPosts();
    }

    public boolean getConnetion(){
        return true;
    }

    public ArrayList<Post> getAll(){
        return postDataBaseMockupList;
    }

    public Optional<Post> getById(int id){
        return Optional.ofNullable(postDataBaseMockupList.get(id));
    }

    public boolean save(Post post){
        try{
            post.setId(postIdIncrementer.incrementAndGet());
            return postDataBaseMockupList.add(post);
        }
        catch(Exception ex){
            return false;
        }
    }

    private ArrayList<Post> getStaticMockupPosts(){
        ArrayList<Post> staticMockupPosts = new ArrayList<Post>();

        staticMockupPosts.add(getStaticPost("Boldovar \"the Mad\"", 
        "This childe of men, lette his bodie nourishe this tree."+
        "\nThe tree of this bodie, lette it growe as it nourishes. "+
        "The spirit of this tree, to them lette it return as it grewe."+
        "\n\nThus the havoc bearers sleepe, the sleepe of no rests."+
        "Thus the sorrow bringers sow, the seeds of their ruine." +
        "\n\nThus the death makers kille, the sons of their sons" +
        "\nHere come ye, Mad Kang Boldovar, and lie among these rootes",
         0));

        staticMockupPosts.add(getStaticPost("Wellerman", 
        "There once was a ship that put to sea\n"+
        "And the name of that ship was the Billy of Tea" +
        "\nThe winds blew hard, her bow dipped down\n"+
        "Blow, my bully boys, blow. Huh!",
        1));

        return staticMockupPosts;
    }

    private Post getStaticPost(String title, String content, int userId){
        return new Post(postIdIncrementer.incrementAndGet(), userId, title, content);
    }
}
