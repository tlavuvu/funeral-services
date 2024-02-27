package za.co.supremeworx.responses;

import lombok.Data;

@Data
public class ItemsResponse {
	
	private String name;
	private String description;
	private double price;
	private int quantity;

}
