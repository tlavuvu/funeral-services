package za.co.supremeworx.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import za.co.supremeworx.model.Email;
import za.co.supremeworx.services.EmailService;


@RestController
@Slf4j
@RequestMapping("/api/notification")
public class EmailController {
	
	@Autowired
	private EmailService emailService;
	
	@PostMapping("/email")
	public ResponseEntity<String> sendSimpleEmail(@RequestBody Email email) {
		return new ResponseEntity<String>(emailService.sendEmail(email),HttpStatus.OK);
	}
	

}
