package za.co.supremeworx.model;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToOne;
import lombok.Data;

@MappedSuperclass
@Data
public class Person {
	
	private String firstname;
	private String surname;
	private String idnumber;
	private String gender;
	private String title;
	private String birthdate;
	
	@OneToOne
	private Address address;

}
