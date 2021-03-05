package com.eservice.portal.modals;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {
	@NotEmpty(message = "username can't be blank")
	private String username;
	@NotEmpty(message = "password can't be blank")
	private String password;
}
