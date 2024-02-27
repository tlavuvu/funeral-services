package za.co.supremeworx.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import za.co.supremeworx.model.Items;

@Repository
public interface ItemsRepository extends CrudRepository<Items, Integer> {

	public Optional<Items> findByName(String name); 
}
