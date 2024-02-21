package za.co.supremeworx.model;

import java.math.BigDecimal;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "t_order_line_items")
@Data
public class OrderLineItems {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String skuCode;
	private BigDecimal price;
	private Integer quantity;
//	
//	@ManyToOne(cascade = CascadeType.PERSIST)
//	private Order order;
	

}
