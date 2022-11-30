package com.cefetqps.spring;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.cefetqps.spring.models.User;
import com.cefetqps.spring.services.UserAuthenticationServices;
import com.cefetqps.spring.services.UserSecretServices;
import com.cefetqps.spring.services.UserServices;
import com.cefetqps.spring.services.clients.UserDatabaseClient;

@SpringBootTest
class UserAuthenticationServicesTest {

	UserServices userServices = new UserServices(new UserSecretServices(), new UserDatabaseClient());

	UserAuthenticationServices userAuthenticationServices = new UserAuthenticationServices(
			new UserSecretServices(), userServices);

	@Test
	void itShouldLogin() {
		assertEquals(true, userAuthenticationServices.login(new User("heitor@email.com.br", "12345")));
	}

	@Test
	void itShouldNotLogin() {
		assertEquals(false, userAuthenticationServices.login(new User("admin", "admin2")));
	}

	@Test
	void itShouldRegister() {
		assertEquals(true, userAuthenticationServices.register(new User("teste", "teste")));
	}

	@Test
	void itShouldNotRegister() {
		assertEquals(false, userAuthenticationServices.register(new User("heitor@email.com.br", "12345")));
	}


}
