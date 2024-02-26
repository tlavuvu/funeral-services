package za.co.supremeworx.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity(name = "t_email_transactions")
@Data
public class EmailTransaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String transaction_id;
	private Date date;
	private String[] emailRecipients;

}
