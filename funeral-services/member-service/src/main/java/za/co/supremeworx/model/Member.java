package za.co.supremeworx.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity(name = "t_members")
@Data
public class Member extends Person{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	
	private String memberNumber;
	private String email;
	private String mobile;
	
	@OneToMany
	private List<Beneficiary> beneficiaries;

}
