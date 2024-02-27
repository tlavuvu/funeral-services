package za.co.supremeworx.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import za.co.supremeworx.model.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {

	public Optional<Product> findByName(String name); 
}
