package com.eservice.portal.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eservice.portal.modals.UserRequest;
import com.eservice.portal.modals.UserResponse;
import com.eservice.portal.service.UserService;
import com.eservice.portal.utility.Response;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/")
@Slf4j
@Api(value = "User", description = "REST API for Users", tags = { "Users" })
public class UserController {

	@Autowired()
	private UserService userService;

	
	@PostMapping("/login")
	public ResponseEntity<Response<UserResponse> > login (@RequestBody @Valid UserRequest user) {
		log.info("Inside login method of UserController", user);
		return userService.login(user);
	}
}
