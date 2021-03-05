package com.eservice.portal.transformer;

import com.eservice.portal.entity.UserEntity;
import com.eservice.portal.modals.UserRequest;
import com.eservice.portal.modals.UserResponse;

public class UserTransform {

	public UserEntity RequestToEntity(UserRequest userRequest) {
		UserEntity user = new UserEntity();
		user.setUsername(userRequest.getUsername());
		user.setPassword(userRequest.getPassword());
		return user;
	}

	public UserResponse EntityToResponse(UserEntity user, String token) {
		UserResponse userResponse = new UserResponse();
		userResponse.setRole(user.getRole().getId());
		userResponse.setToken(token);
		return userResponse;
	}
}
