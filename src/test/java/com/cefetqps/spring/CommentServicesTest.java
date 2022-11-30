package com.cefetqps.spring;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.cefetqps.spring.services.CommentServices;
import com.cefetqps.spring.services.clients.CommentDatabaseClient;

public class CommentServicesTest {

  CommentServices commentServices = new CommentServices(new CommentDatabaseClient());

  @Test
  void itShouldGetCommentById() {
    assertEquals(1, commentServices.getById(1).get().getId());
  }

  @Test
  void itShouldGetAllComments() {
    assertEquals(3, commentServices.getAll().size());
  }

  @Test
  void itShouldGetAllCommentsByPostId() {
    assertEquals(1, commentServices.getByPostId(1).size());
  }
  
}
