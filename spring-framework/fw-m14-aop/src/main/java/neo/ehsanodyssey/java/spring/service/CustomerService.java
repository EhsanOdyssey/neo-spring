package neo.ehsanodyssey.java.spring.service;

import neo.ehsanodyssey.java.spring.aspect.Loggable;
import neo.ehsanodyssey.java.spring.data.entity.Customer;
import neo.ehsanodyssey.java.spring.data.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Loggable
    public List<Customer> getAll(){
        return (List<Customer>) this.customerRepository.findAll();
    }

    @Loggable
    public Customer findOne(String id){
        return this.customerRepository.findById(id).orElse(new Customer());
    }
}
