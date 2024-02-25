package za.co.supremeworx.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
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
	@CircuitBreaker(name = "inventory",fallbackMethod = "fallBackMethod")
	@Retry(name = "inventory")
	public ResponseEntity<String> placeOrder(@RequestBody OrderRequest orderRequest){
		log.info("request {}",orderRequest.toString());
		return new ResponseEntity<String>(orderService.placeOrder(orderRequest), HttpStatus.CREATED);
	}
	
	public ResponseEntity<String> fallBackMethod( OrderRequest orderRequest, RuntimeException runtimeException){ 
		return new ResponseEntity<String>("Oops! Something went wrong. Please try again later", HttpStatus.FAILED_DEPENDENCY);
	}
	
//	@PostMapping
//	@CircuitBreaker(name = "inventory",fallbackMethod = "fallBackMethod")
//	@TimeLimiter(name = "inventory")
//	public ResponseEntity<CompletableFuture<String>> placeOrder1(@RequestBody OrderRequest orderRequest){
//		log.info("request {}",orderRequest.toString());
//		return new ResponseEntity<CompletableFuture<String>>(CompletableFuture.supplyAsync(() -> orderService.placeOrder(orderRequest)), HttpStatus.CREATED);
//	}

}//-----------end of class
