package com.pvr;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import com.pvr.config.jwt.JwtUtils;
import com.pvr.entity.Admin;
import com.pvr.exceptions.AdminException;
import com.pvr.payloads.Message;
import com.pvr.services.AdminService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class AdminControllerTest {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@Mock
	private AdminService adminService;

	@Test
	public void registerAdminHandler_success() throws AdminException {
		Admin admin = new Admin();
		admin.setAdminName("John@gmail.com");
		admin.setPassword("password");

		Message msg = adminService.registerAdmin(admin);

		ResponseEntity<Message> response = new ResponseEntity<Message>(msg, HttpStatus.ACCEPTED);

		assertEquals(response.getStatusCode(), HttpStatus.ACCEPTED);
		assertEquals(response.getBody(), msg);
	}

	@Test
	void testDeleteAdminHandler() throws AdminException {
		String user = "testuser";
		String password = "password";
		Message msg = adminService.deleteAdmin(user, password);
		ResponseEntity<Message> response = new ResponseEntity<Message>(msg, HttpStatus.ACCEPTED);
		assertEquals(response.getStatusCode(), HttpStatus.ACCEPTED);
		assertEquals(response.getBody(), msg);
	}

}