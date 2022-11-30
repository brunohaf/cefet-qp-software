package com.cefetqps.spring;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.cefetqps.spring.models.User;
import com.cefetqps.spring.services.UserSecretServices;
import com.cefetqps.spring.services.UserServices;
import com.cefetqps.spring.services.clients.UserDatabaseClient;

@SpringBootTest
class UserServicesTest {

	UserServices userServices = new UserServices(new UserSecretServices(), new UserDatabaseClient());

	@Test
	void itShouldGetUserById() {
		assertEquals(1, userServices.getById(1).get().getId());
	}

	@Test
	void itShouldGetUserByEmail() {
		assertEquals("heitor@email.com.br", userServices.getByEmail("heitor@email.com.br").getEmail());
	}

	@Test
	void itShouldGetAllUsers() {
		assertEquals(3, userServices.getAll().size());
	}

	@Test
	void itShouldSaveUser() {
		assertEquals(true, userServices.saveUserData(new User(3, "teste", "teste")));
	}

}
