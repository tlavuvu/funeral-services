package za.co.supremeworx.services;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import za.co.supremeworx.model.Product;
import za.co.supremeworx.repositories.ProductRepository;
import za.co.supremeworx.requests.ProductRequest;
import za.co.supremeworx.responses.ProductResponse;

@Service
public class ProductService {
	
	@Autowired
	ProductRepository productRepository;
	
	public ProductResponse saveProduct(ProductRequest productRequest) {
		ProductResponse productResponse = mapProductToProductResponse(productRepository
																.save(mapProductRequestToProduct(productRequest)));
		return productResponse;
	}
	
	public Product mapProductRequestToProduct(ProductRequest productRequest) {
		Product product = new Product();
		product.setName(productRequest.getName());
		product.setDescription(productRequest.getDescription());
		product.setMonthlyContribution(productRequest.getMonthlyContribution());
		product.setProductPayOut(productRequest.getProductPayOut());
		return product;
	}
	
	public List<ProductResponse> getProducts(){
		List<ProductResponse> productResponses = convertIterableToList(productRepository.findAll())
				.stream()
				.map(this::mapProductToProductResponse)
				.toList();
		return productResponses;
	}
	
	public List<Product> convertIterableToList( Iterable<Product> itr) {
		return StreamSupport.stream(itr.spliterator(), false).collect(Collectors.toList());
	}
	
	public ProductResponse getProduct(String name) {
		return mapProductToProductResponse(productRepository.findByName(name).get());
	}
	
	public ProductResponse mapProductToProductResponse(Product product) {
		ProductResponse productResponse = new ProductResponse();
		productResponse.setName(product.getName());
		productResponse.setDescription(product.getDescription());
		productResponse.setMonthlyContribution(product.getMonthlyContribution());
		productResponse.setProductPayOut(product.getProductPayOut());
		return productResponse;
	}

}
