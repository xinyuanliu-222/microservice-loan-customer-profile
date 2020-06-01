package com.cathy.customer.profile.service;

import java.net.URI;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.ws.rs.ServiceUnavailableException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Component;

import com.cathy.customer.profile.config.CachingConfiguration;
import com.cathy.customer.profile.domain.Customer;

@Component
public class CustomerApiClient {

	private Client client;
	
	@Autowired
	private LoadBalancerClient loadBalancer;
	
	private static final String CUSTOMER_SERVICE = "customer-service";
	
	@PostConstruct
	private void init() {
		this.client = ClientBuilder.newClient();
	}
	
	@Cacheable(cacheNames = CachingConfiguration.CUSTOMER_CACHE, key="#customerId")
	public Optional<Customer> getCustomer(String customerId) {
		URI customerServiceUri = getCustomerServiceUri();
		Response response = client.target(customerServiceUri).path("api").path("customer").path(customerId).request().get();
		if (Response.Status.Family.SUCCESSFUL == response.getStatusInfo().getFamily()) {
			return Optional.ofNullable(response.readEntity(Customer.class));
		} else {
			return Optional.empty();
		}
	}
	
	public boolean checkIfCustomerExists(String customerId) {
		URI customerServiceUri = getCustomerServiceUri();
		Response response = client.target(customerServiceUri).path("api").path("customer").path(customerId).request().head();
		return Response.Status.OK.getStatusCode() == response.getStatus();
	}
	
	public URI getCustomerServiceUri() {
		ServiceInstance serviceInstance = loadBalancer.choose(CUSTOMER_SERVICE);
		if (serviceInstance == null) {
			throw new ServiceUnavailableException("Service unavaliable");
		}
		return serviceInstance.getUri();
	}
}
