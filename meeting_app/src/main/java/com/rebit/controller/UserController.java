package com.rebit.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rebit.model.User;
import com.rebit.services.EmailService;
import com.rebit.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	   private final EmailService emailService;

	    public UserController(EmailService emailService) {
	        this.emailService = emailService;
	        }
	    

	@PostMapping("/")
	public ResponseEntity<User> createPatient(@RequestBody User user) throws Exception {

		Random random = new Random();
		int randomNumber = random.nextInt(900) + 100;
		String companyName = user.getCompany();
		String generatedUsername = companyName + Integer.toString(user.getId()) + Integer.toString(randomNumber);
		user.setUsername(generatedUsername);
		System.out.println(generatedUsername);
		User newUser = userService.createUser(user);

		String emailBody = "Dear " + user.getFirstName() + user.getLastName() + ",\n\n"
				+ "Your registration was successful, and we're excited to have you on board. Your account is now active, and you can start using Meeting App right away."
				+ " Enjoy all the features, connect with others, and stay updated using your new account."+"\n\n\n"+"Your username for logging  is: "
				+ generatedUsername +"\n\n"+ " Best regards,"+"\n\n"+"ReBIT Team" + "";

		emailService.sendEmail(user.getEmail(), "Registration Successful", emailBody);

		return new ResponseEntity<>(newUser, HttpStatus.CREATED);

	}

	@GetMapping("/{username}")
	public ResponseEntity<User> getUser(@PathVariable("username") String username) {

		return ResponseEntity.ok(this.userService.getUser(username));
	}

	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable("id") long id) {
		this.userService.deleteUser(id);
	}

}
