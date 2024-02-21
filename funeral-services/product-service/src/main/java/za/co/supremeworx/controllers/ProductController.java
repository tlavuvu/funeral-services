package za.co.supremeworx.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import za.co.supremeworx.dto.ProductRequest;
import za.co.supremeworx.dto.ProductResponse;
import za.co.supremeworx.services.ProductService;

@RestController
@RequestMapping("/api/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@PostMapping
	public ResponseEntity<String> createProduct(@RequestBody ProductRequest productRequest) {
		String id = productService.createProduct(productRequest);
		return new ResponseEntity<String>(id, HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<ProductResponse>> getAllUsers(){
		return new ResponseEntity<List<ProductResponse>>(productService.getAllProducts(),HttpStatus.OK);
	}

}//---end of class
