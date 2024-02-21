package za.co.supremeworx.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import za.co.supremeworx.model.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {

}
