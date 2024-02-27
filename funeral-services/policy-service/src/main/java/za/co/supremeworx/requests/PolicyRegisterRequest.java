package za.co.supremeworx.requests;

import lombok.Data;

@Data
public class PolicyRegisterRequest {
	
	private int id;
	
	private String memberNumber;
	private String productName;

}
