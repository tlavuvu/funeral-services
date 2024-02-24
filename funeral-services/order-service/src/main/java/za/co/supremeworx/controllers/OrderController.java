package za.co.supremeworx.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;
import za.co.supremeworx.dto.OrderRequest;
import za.co.supremeworx.services.OrderService;

@RestController
@RequestMapping("/api/order")
@Slf4j
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@PostMapping
	public ResponseEntity<String> placeOrder(@RequestBody OrderRequest orderRequest){
		log.info("request {}",orderRequest.toString());
		return new ResponseEntity<String>(orderService.placeOrder(orderRequest), HttpStatus.CREATED);
	}

}//-----------end of class
