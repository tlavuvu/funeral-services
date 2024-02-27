package za.co.supremeworx.responses;

import lombok.Data;

@Data
public class ProductResponse {

	private String name;
	private String description;
	private double monthlyContribution;
	private double productPayOut;
}
