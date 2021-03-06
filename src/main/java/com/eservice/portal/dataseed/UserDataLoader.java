package com.eservice.portal.dataseed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.eservice.portal.entity.RoleEntity;
import com.eservice.portal.entity.UserEntity;
import com.eservice.portal.repository.RoleRepository;
import com.eservice.portal.repository.UserRepository;
import com.eservice.portal.utility.PasswordUtility;

@Component
public class UserDataLoader implements CommandLineRunner {

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	private PasswordUtility passwordUtility;

	@Override
	public void run(String... args) throws Exception {
		createUsers();
	}

	private void createUsers() {
		if (userRepository.count() == 0) {

			RoleEntity adminRole = new RoleEntity();
			adminRole.setName("admin");

			UserEntity admin = new UserEntity();
			admin.setUsername("admin");
			admin.setPassword(passwordUtility.hash("admin123"));
			admin.setRole(adminRole);
			userRepository.save(admin);

			RoleEntity role = new RoleEntity();
			role.setName("customer");

			UserEntity user = new UserEntity();
			user.setUsername("customer");
			user.setPassword(passwordUtility.hash("customer123"));
			user.setRole(role);
			userRepository.save(user);

		}
	}

}
