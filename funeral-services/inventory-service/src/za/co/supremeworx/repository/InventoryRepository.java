package za.co.supremeworx.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import za.co.supremeworx.model.Inventory;

public interface InventoryRepository extends CrudRepository<Inventory, Long> {

	Optional<Inventory> findBySkuCode(String skuCode);
	List<Inventory> findBySkuCodeIn(List<String> skuCode);

}