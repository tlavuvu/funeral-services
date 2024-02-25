package za.co.supremeworx.services;

import java.util.UUID;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import za.co.supremeworx.dto.InventoryResponse;
import za.co.supremeworx.dto.OrderLineItemsDto;
import za.co.supremeworx.dto.OrderRequest;
import za.co.supremeworx.model.Order;
import za.co.supremeworx.model.OrderLineItems;
import za.co.supremeworx.repository.OrderRepository;
//import za.co.supremeworx.utils.CustomProperties;


@Slf4j
@Service
@Transactional
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
//	@Autowired
//	private CustomProperties customProperties;
	
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
		.map(t -> t.getSkuCode())
		.toList();
		
//		adding skuCodes into query params
		MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
		for(String sk:skuCodes) {
			queryParams.add("skuCode", sk);
		}
		
		
		log.info("skuCodes {}",skuCodes);
		
//		Call inventory service to check if we have stock
		InventoryResponse[] inventoryResponseArray = webClient.get()
			.uri("http://inventory-service/api/inventory",uriBuilder -> uriBuilder.queryParams(queryParams).build())
			.retrieve()
			.bodyToMono(InventoryResponse[].class)
			.block();
		
//		Predicate<InventoryResponse[]> pr = t -> t.length < 1;
		
		boolean productsInStock = Arrays.stream(inventoryResponseArray).allMatch(t -> t.isInStock());
		
		if(productsInStock) {
			orderRepository.save(order);
		}else {
			log.info("Product not in stock");
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
