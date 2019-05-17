package com.rwt.cache.springdata.mongodb.dao;

import java.util.List;

import com.rwt.cache.springdata.mongodb.entity.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {

    public Customer findByFirstName(String firstName);
    public List<Customer> findByLastName(String lastName);

}