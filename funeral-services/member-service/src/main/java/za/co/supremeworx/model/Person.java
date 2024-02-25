package za.co.supremeworx.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToOne;
import lombok.Data;

@MappedSuperclass
@Data
public class Person {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String firstname;
	private String surname;
	private String idnumber;
	private String gender;
	private String title;
	private String birthdate;
	
	@OneToOne
	private Address address;

}
