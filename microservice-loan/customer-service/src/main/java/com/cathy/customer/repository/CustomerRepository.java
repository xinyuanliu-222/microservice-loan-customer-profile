package com.cathy.customer.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cathy.customer.domain.Customer;

public interface CustomerRepository extends MongoRepository<Customer, String> {

}
