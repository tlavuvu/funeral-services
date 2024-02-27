package za.co.supremeworx.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.supremeworx.model.Email;
import za.co.supremeworx.model.EmailTransaction;
import za.co.supremeworx.services.EmailService;


@RestController
@RequestMapping("/api/notification")
public class EmailController {
	
	@Autowired
	private EmailService emailService;
	
	@PostMapping("/sendemail")
	public ResponseEntity<String> sendSimpleEmail(@RequestBody Email email) {
		return new ResponseEntity<String>(emailService.sendEmail(email),HttpStatus.OK);
	}
	
	@GetMapping("/getemailtransactions")
	public ResponseEntity<List<EmailTransaction>> getAllEmailTransactions(){
		return new ResponseEntity<List<EmailTransaction>>(emailService.getAllEmailTransactions(),HttpStatus.OK);
	}
	
}
