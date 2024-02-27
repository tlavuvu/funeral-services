package za.co.supremeworx.services;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import za.co.supremeworx.model.Email;
import za.co.supremeworx.model.EmailTransaction;
import za.co.supremeworx.model.Recipient;
import za.co.supremeworx.repositories.EmailTransactionRepository;

@Service
@Slf4j
public class EmailService {
	
	
	@Autowired 
	private JavaMailSender javaMailSender;
	
	@Autowired
	private EmailTransactionRepository emailTransactionRepository;
	
	
	public String sendEmail(Email email) {
		
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setFrom(email.getFrom());
        mailMessage.setTo(convertListToStringArray(email.getRecipients()));
        mailMessage.setText(email.getMessage());
        mailMessage.setSubject(email.getSubject());
		
        // Sending the mail
        javaMailSender.send(mailMessage);
        recordEmailTransaction(email);
		return "Email sent successfully";
	}
	
	
	public void recordEmailTransaction(Email email) {
		log.info("Email sent successfully to {}",email.getRecipients());
		UUID uuid = UUID.randomUUID();
		EmailTransaction emailTransaction = new EmailTransaction();
		emailTransaction.setTransaction_id(uuid.toString());
		emailTransaction.setEmailRecipients(convertListToStringArray(email.getRecipients()));
		long now = System.currentTimeMillis();
//		emailTransaction.setDate(new Date(now));
		emailTransaction.setTimeStamp(new Timestamp(now));
		emailTransactionRepository.save(emailTransaction);
		
	}
	
	
	public String[] convertListToStringArray(List<Recipient> recipients) {
		List<String> myList = recipients.stream().map(recipient -> recipient.getEmailAddress()).collect(Collectors.toList());
				return myList.toArray(new String[0]);
	}
	
	public List<EmailTransaction> convertIterableToList( Iterable<EmailTransaction> transactions) {
		return StreamSupport.stream(transactions.spliterator(), false).collect(Collectors.toList());
	}
	
	public List<EmailTransaction> getAllEmailTransactions() {
		return convertIterableToList(emailTransactionRepository.findAll());
	}

}
