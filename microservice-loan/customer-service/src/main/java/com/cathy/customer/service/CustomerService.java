package com.cathy.customer.service;

import java.util.List;

import javax.ws.rs.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Service;

import com.cathy.customer.domain.Customer;
import com.cathy.customer.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	@Qualifier(CustomerOutputChannel.CUSTOMER_DELETED_OUTPUT)
	private MessageChannel customerDeletedMessageChannel;
	
	@Autowired
	@Qualifier(CustomerOutputChannel.CUSTOMER_UPDATED_OUTPUT)
	private MessageChannel customerUpdatedMessageChannel;
	
	public List<Customer> getCustomers() {
		return customerRepository.findAll();
	}
	
	public String createCustomer(Customer customer) {
		customer = customerRepository.save(customer);
		return customer.getId();
	}
	
	public void updateCustomer(Customer customer) {
		customer = customerRepository.save(customer);
		customerUpdatedMessageChannel.send(MessageBuilder.withPayload(customer).build());
	}
	
	public Customer getCustomer(String id) {
		Customer customer = customerRepository.findOne(id);
		if (customer == null) {
			throw new NotFoundException();
		}
		return customer;
	}
	
	public void deleteCustomer(String id) {
		Customer customer = customerRepository.findOne(id);
		if (customer == null) {
			throw new NotFoundException();
		} else {
			customerRepository.delete(id);
			customerDeletedMessageChannel.send(MessageBuilder.withPayload(customer).build());
		}
	}
}
