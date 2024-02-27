package za.co.supremeworx.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "t_poliy")
@AllArgsConstructor
@NoArgsConstructor
public class Policy {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(unique=true)
	private String uuid;
	
	@Column(unique=true)
	private String policyNumber;
	
	private Member member;
	
	private ProductResponse product;

}
