package za.co.supremeworx.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import za.co.supremeworx.dto.ProductRequest;
import za.co.supremeworx.dto.ProductResponse;
import za.co.supremeworx.model.Product;
import za.co.supremeworx.repositories.ProductRepository;

@Service
//@RequiredArgsConstructor
@Slf4j
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
//	private final ProductRepository productRepository;
	
	public String createProduct(ProductRequest productRequest) {
		Product product = Product.builder()
				.name(productRequest.getName())
				.description(productRequest.getDescription())
				.price(productRequest.getPrice())
				.build(); 
		
		productRepository.save(product);
		log.info("Product {} is saved", product.toString());
		return product.getId();
	}

	public List<ProductResponse> getAllProducts() {
		// TODO Auto-generated method stub
		List<Product> products = productRepository.findAll();
		return products.stream().map(this::mapToProductResponse).toList();
		 
	}
	
	public ProductResponse mapToProductResponse(Product product) {
//		mapping product to productresponse
		return ProductResponse.builder()
				.id(product.getId())
				.name(product.getName())
				.description(product.getDescription())
				.price(product.getPrice())
				.build();
	}

}//----end of class
