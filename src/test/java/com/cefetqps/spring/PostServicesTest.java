package com.cefetqps.spring;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.cefetqps.spring.services.PostServices;
import com.cefetqps.spring.services.clients.PostDatabaseClient;

public class PostServicesTest {
  
  PostServices postServices = new PostServices(new PostDatabaseClient());
  
  @Test
  void itShouldGetPostById() {
    assertEquals(1, postServices.getById(1).get().getId());
  }

  @Test
  void itShouldGetAllPosts() {
    assertEquals(2, postServices.getAll().size());
  }

  @Test
  void itShouldGetAllPostsByUserId() {
    assertEquals(1, postServices.getByUserId(1).size());
  }
}
