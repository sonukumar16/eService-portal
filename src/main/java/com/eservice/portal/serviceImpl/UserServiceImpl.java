package com.eservice.portal.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.eservice.portal.entity.UserEntity;
import com.eservice.portal.modals.UserRequest;
import com.eservice.portal.modals.UserResponse;
import com.eservice.portal.repository.UserRepository;
import com.eservice.portal.service.UserService;
import com.eservice.portal.transformer.UserTransform;
import com.eservice.portal.utility.JWTUtility;
import com.eservice.portal.utility.PasswordUtility;
import com.eservice.portal.utility.Response;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	JWTUtility jwtUtility;

	@Autowired
	private PasswordUtility passwordUtility;

	@Override
	public ResponseEntity<Response<UserResponse>> login(UserRequest user) {
		UserResponse userResponseModal = new UserResponse();

		try {
			UserTransform userTransform = new UserTransform();
			UserEntity userEntity = userTransform.RequestToEntity(user);
			Optional<UserEntity> userOpt = userRepository.findByUsername(userEntity.getUsername());

			if (userOpt.isPresent() && passwordUtility.verifyHash(user.getPassword(), userOpt.get().getPassword())) {
				String token = jwtUtility.generateToken(userOpt.get().getId());
				userResponseModal = userTransform.EntityToResponse(userOpt.get(), token);
			} else {
				return new ResponseEntity<>(new Response<>("Wrong username or password"), HttpStatus.UNAUTHORIZED);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new Response<>("Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return ResponseEntity.ok(Response.ok(userResponseModal, "Logged in successfully."));
	}

	@Override
	public UserEntity saveUser(UserRequest user) {
		// TODO Auto-generated method stub
		return null;
	}

}
