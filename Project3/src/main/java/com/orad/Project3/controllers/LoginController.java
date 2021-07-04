package com.orad.Project3.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.orad.Project3.login.LoginManager;
import com.orad.Project3.login.LoginManager.ClientType;
import com.orad.Project3.models.User;
import com.orad.Project3.service.ClientService;
import com.orad.Project3.session.Session;
import com.orad.Project3.session.SessionContext;

@RestController
@RequestMapping("/login")
@CrossOrigin
public class LoginController {

	@Autowired
	private LoginManager loginManager;
	@Autowired
	private SessionContext sessionContext;

	@PostMapping
	public String login(@RequestBody User user) {
		System.out.println(user);
		try {
			ClientService service = loginManager.login(user.getEmail(), user.getPassword(),
					ClientType.valueOf(user.getClientType()));
			if (service != null) {
				Session session = sessionContext.createSession();
				session.setClientType(ClientType.valueOf(user.getClientType()));
				session.setService(service);// use this service in controllers
				String token = session.token;
				user.setToken(token);
				System.out.println(user);
				HttpHeaders headers = new HttpHeaders();
				headers.add("token", token);
				return "{\"token\": \"" + session.token + "\" }";

			} else {
				System.out.println("Login Failed");
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Login Failed");
			}
		} catch (Exception e) {
			System.out.println("Exception-Login Controller Failed" + e);
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Login Controller Failed", e);
		}
	}

}
