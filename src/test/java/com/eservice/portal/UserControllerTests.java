package com.eservice.portal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.eservice.portal.controller.UserController;
import com.eservice.portal.modals.UserRequest;
import com.eservice.portal.modals.UserResponse;
import com.eservice.portal.service.UserService;
import com.eservice.portal.utility.Response;
import com.fasterxml.jackson.core.JsonProcessingException;

@SpringBootTest
public class UserControllerTests {

	@InjectMocks
	private UserController UserController;

	@Mock
	private UserService userService;

	@Test
	public void login() throws JsonProcessingException {
		UserRequest user = getMockUser("Admin", "admin123");
		UserResponse expectedResponseModal = getExpectedModel();

		when(userService.login(user))
				.thenReturn(ResponseEntity.ok(Response.ok(expectedResponseModal, "Logged in successfully.")));

		ResponseEntity<Response<UserResponse>> resposeModelActual = UserController.login(user);
		UserResponse data = resposeModelActual.getBody().getData();
		String token = data.getToken();
		assertEquals(token, expectedResponseModal.getToken());
		assertEquals(200, resposeModelActual.getStatusCodeValue());

	}

	@Test
	public void loginWithBadCreds() throws JsonProcessingException {
		UserRequest user = getMockUser("Admin", "admin");
		
		when(userService.login(user)).thenReturn(
				new ResponseEntity<>(new Response<>("Wrong username or password"), HttpStatus.UNAUTHORIZED));
		
		ResponseEntity<Response<UserResponse>> resposeModelActual = UserController.login(user);
		assertEquals(401, resposeModelActual.getStatusCodeValue());

	}

	private UserResponse getExpectedModel() {
		UserResponse expectedUser = new UserResponse();
		expectedUser.setToken("abcvhcghgdfds6729hfxkys3232oensldkjdfs4232fbalwildds242l");
		expectedUser.setRole(1l);
		return expectedUser;
	}

	private UserRequest getMockUser(String Username, String password) {
		UserRequest user = new UserRequest();
		user.setUsername(Username);
		user.setPassword(password);
		return user;
	}

}
