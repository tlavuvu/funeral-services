package za.co.supremeworx.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import za.co.supremeworx.model.Policy;

@Repository
public interface PolicyRepository extends CrudRepository<Policy, Integer> {

}
