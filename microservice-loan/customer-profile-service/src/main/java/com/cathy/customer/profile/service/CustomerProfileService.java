package com.cathy.customer.profile.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import javax.lang.model.UnknownEntityException;
import javax.ws.rs.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

import com.cathy.common.api.exception.UnprocessableEntityException;
import com.cathy.customer.profile.config.CachingConfiguration;
import com.cathy.customer.profile.domain.Customer;
import com.cathy.customer.profile.domain.CustomerProfile;
import com.cathy.customer.profile.repository.CustomerProfileRepository;
import com.cathy.customer.profile.repository.CustomerProfileRepositoryImpl;

@Service
public class CustomerProfileService {

	@Autowired
	private CacheManager cacheManager;
	
	@Autowired
	private CustomerApiClient customerApiClient;
	
	@Autowired
	private CustomerProfileRepository customerProfileRepository;
	
	public List<CustomerProfile> getCustomerProfiles() {
		List<CustomerProfile> customerProfiles = customerProfileRepository.findAll();
		customerProfiles.forEach(this::fillCustomerDetails);
		return customerProfiles;
	}
	
	public String createCustomerProfile(CustomerProfile customerProfile) {
		validateItem(customerProfile);
		customerProfile = customerProfileRepository.save(customerProfile);
		return customerProfile.getId();
	}
	
	public void updateCustomerProfile(CustomerProfile customerProfile) {
		validateItem(customerProfile);
		customerProfileRepository.save(customerProfile);
	}
	
	public CustomerProfile getCustomerProfile(String id) {
		CustomerProfile customerProfile = customerProfileRepository.findOne(id);
		if (customerProfile == null) {
			throw new NotFoundException();
		} else {
			fillCustomerDetails(customerProfile);
			return customerProfile;
		}
	}
	
	public void deleteCustomerProfile(String id) {
		CustomerProfile customerProfile = customerProfileRepository.findOne(id);
		if (customerProfile == null) {
			throw new NotFoundException();
		} else {
			customerProfileRepository.delete(id);
		}
	}
	
	private void validateItem(CustomerProfile customerProfile) {
		Customer customer = customerProfile.getCustomer();
		if(!customerApiClient.checkIfCustomerExists(customer.getId())) {
			throw new UnprocessableEntityException(String.format("Item not found with id %s", customer.getId()));
		}
	}
	
	private void fillCustomerDetails(CustomerProfile customerProfile) {
		Customer customer = customerProfile.getCustomer();
		Optional<Customer> optionalCustomer = customerApiClient.getCustomer(customer.getId());
		optionalCustomer.ifPresent(new Consumer<Customer>() {

			@Override
			public void accept(Customer t) {
				customer.setFirstname(t.getFirstname());
				customer.setLastname(t.getLastname());
				customer.setAddress(t.getAddress());
				customer.setAmount(t.getAmount());
				customer.setEmail(t.getEmail());
				customer.setPhone(t.getPhone());
				customer.setSsn(t.getSsn());
				customer.setApplydate(t.getApplydate());
				customer.setDuedate(t.getDuedate());
				customer.setApprove(t.isApprove());
			}
		});
	}
	
	@CacheEvict(cacheNames = CachingConfiguration.CUSTOMER_CACHE, key="#customer.id")
	@StreamListener(CustomerInputChannel.CUSTOMER_DELETED_INPUT)
	public void handleDeletedCustomer(Customer customer) {
		customerProfileRepository.deleteCustomerById(customer.getId());
	}
	
	@StreamListener(CustomerInputChannel.CUSTOMER_UPDATED_INPUT)
	public void handleUpdatedCustomer(Customer customer) {
		cacheManager.getCache(CachingConfiguration.CUSTOMER_CACHE).put(customer.getId(), customer);
	}
}
