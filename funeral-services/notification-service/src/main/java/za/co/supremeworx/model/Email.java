package za.co.supremeworx.model;

import java.util.List;

import lombok.Data;


@Data
public class Email {
	
	private String from;
	private List<Recipient> recipients;
	private String message;
	private String subject;

}
