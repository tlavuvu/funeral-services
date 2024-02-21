package za.co.supremeworx.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import za.co.supremeworx.dto.OrderLineItemsDto;
import za.co.supremeworx.dto.OrderRequest;
import za.co.supremeworx.model.Order;
import za.co.supremeworx.model.OrderLineItems;
import za.co.supremeworx.repository.OrderRepository;
import za.co.supremeworx.utils.CustomProperties;


@Slf4j
@Service
@Transactional
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CustomProperties customProperties;
	
	@Autowired
	private WebClient webClient;
	
	public String placeOrder(OrderRequest orderRequest) {
		Order order = new Order();
		order.setOrderNumber(UUID.randomUUID().toString());
		
		List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
		.stream()
		.map(this::mapToDto)
		.toList();
		
		log.info("Created order number is {}", order.getOrderNumber());
		
		order.setOrderLineItemsList(orderLineItems);
		
		
		List<String> skuCodes = order.getOrderLineItemsList()
		.stream()
		.map(OrderLineItems::getSkuCode)
		.toList();
		
		log.info("Items {}",skuCodes);
		
//		Call inventory service to check if we have stock
		Boolean result = webClient.get()
			.uri(customProperties.getInventoryservicesuri())
			.retrieve()
			.bodyToMono(Boolean.class)
			.block();
		
		if(result) {
			orderRepository.save(order);
		}else {
			log.warn("Product {} not in stock");
			throw new IllegalArgumentException("Product not in stock");
		}
//		-----------------------------------------
		
		return "Order placed successfully";
	}
	
	private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
		OrderLineItems orderLineItems = new OrderLineItems();
		orderLineItems.setPrice(orderLineItemsDto.getPrice());
		orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
		orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
		return orderLineItems;
	}

}
