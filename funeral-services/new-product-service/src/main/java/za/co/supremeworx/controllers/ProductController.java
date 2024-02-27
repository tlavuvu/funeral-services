package za.co.supremeworx.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import za.co.supremeworx.requests.ProductRequest;
import za.co.supremeworx.responses.ProductResponse;
import za.co.supremeworx.services.ProductService;

@RestController
@RequestMapping("/api/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@PostMapping
	public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest productRequest) {
		ProductResponse productResponse = productService.saveProduct(productRequest);
		return new ResponseEntity<ProductResponse>(productResponse, HttpStatus.CREATED);
	}
	
	@GetMapping("/{name}")
	public ResponseEntity<ProductResponse> findProductByName(@PathVariable String name){
		return new ResponseEntity<ProductResponse>(productService.getProduct(name),HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<ProductResponse>> getProducts(){
		return new ResponseEntity<List<ProductResponse>>(productService.getProducts(),HttpStatus.OK);
	}

}
