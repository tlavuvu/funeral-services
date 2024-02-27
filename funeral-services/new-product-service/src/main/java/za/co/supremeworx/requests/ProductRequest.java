package za.co.supremeworx.requests;

import lombok.Data;

@Data
public class ProductRequest {
	
	private String name;
	private String description;
	private double monthlyContribution;
	private double productPayOut;

}
