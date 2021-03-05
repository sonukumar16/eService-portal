package com.eservice.portal.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordUtility {

	@Autowired
	private PasswordEncoder passwordEncoder;

	public boolean verifyHash(String rawPassword, String hashPassword) {
		if (passwordEncoder.matches(rawPassword, hashPassword)) {
			return true;
		}
		return false;

	}

	public String hash(String password) {
		return passwordEncoder.encode(password);

	}
}
