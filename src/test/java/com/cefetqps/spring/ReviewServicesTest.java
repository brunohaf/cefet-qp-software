package com.cefetqps.spring;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.cefetqps.spring.services.ReviewServices;
import com.cefetqps.spring.services.clients.ReviewDatabaseClient;

public class ReviewServicesTest {

  ReviewServices reviewServices = new ReviewServices(new ReviewDatabaseClient());

  @Test
  void itShouldGetReviewById() {
    assertEquals(1, reviewServices.getById(1).get().getId());
  }

  @Test
  void itShouldGetAllReviews() {
    assertEquals(3, reviewServices.getAll().size());
  }

  @Test
  void itShouldGetAllReviewsByUserId() {
    assertEquals(1, reviewServices.getByUserId(1).size());
  }

  @Test
  void itShouldGetAllReviewsByPostId() {
    assertEquals(1, reviewServices.getByPostId(1).size());
  }


  
}
