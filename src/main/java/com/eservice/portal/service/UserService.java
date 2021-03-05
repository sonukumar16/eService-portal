package com.eservice.portal.service;

import org.springframework.http.ResponseEntity;

import com.eservice.portal.entity.UserEntity;
import com.eservice.portal.modals.UserRequest;
import com.eservice.portal.modals.UserResponse;
import com.eservice.portal.utility.Response;

public interface UserService {

	ResponseEntity<Response<UserResponse>> login(UserRequest user);

	UserEntity saveUser(UserRequest user);

}
