package neo.ehsanodyssey.java.spring.data.repository;

import neo.ehsanodyssey.java.spring.data.entity.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, String>{

}
