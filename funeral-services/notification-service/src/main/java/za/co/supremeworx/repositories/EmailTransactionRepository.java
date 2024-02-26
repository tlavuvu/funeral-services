package za.co.supremeworx.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import za.co.supremeworx.model.EmailTransaction;

@Repository
public interface EmailTransactionRepository extends CrudRepository<EmailTransaction, Integer> {

}
