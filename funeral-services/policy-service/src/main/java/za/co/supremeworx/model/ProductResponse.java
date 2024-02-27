package za.co.supremeworx.model;

import lombok.Data;

@Data
public class ProductResponse {

	private String name;
	private String description;
	private double monthlyContribution;
	private double productPayOut;
}
