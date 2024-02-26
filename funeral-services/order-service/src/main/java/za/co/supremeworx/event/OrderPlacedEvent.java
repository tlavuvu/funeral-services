package za.co.supremeworx.event;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderPlacedEvent {

	private String orderNumber;
	
}//
