package com.cathy.customer.api;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cathy.customer.api.mapper.CustomerMapper;
import com.cathy.customer.api.model.CreateOrUpdateCustomerPayload;
import com.cathy.customer.api.model.QueryCustomerResult;
import com.cathy.customer.domain.Customer;
import com.cathy.customer.service.CustomerService;

@Component
@Path("customers")
public class CustomerResource {

	@Context
	private UriInfo uriInfo;
	
	@Autowired
	private CustomerMapper customerMapper;
	
	@Autowired
	private CustomerService customerService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCustomers() {
		List<Customer> customers = customerService.getCustomers();
		List<QueryCustomerResult> queryResults = customers.stream().map(customerMapper::toQueryCustomerResult).collect(Collectors.toList());
		return Response.ok(queryResults).build();
	}
	
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
	public Response createCustomer(@Valid @NotNull CreateOrUpdateCustomerPayload customerPayload) {
		Customer customer = customerMapper.toCustomer(customerPayload);
		String id = customerService.createCustomer(customer);
		return Response.created(uriInfo.getAbsolutePathBuilder().path(id).build()).build();
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCustomer(@PathParam("id") String id) {
		Customer customer = customerService.getCustomer(id);
		QueryCustomerResult queryResult = customerMapper.toQueryCustomerResult(customer);
		return Response.ok(queryResult).build();
	}
	
	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateCustomer(@PathParam("id") String id, @Valid @NotNull CreateOrUpdateCustomerPayload payload) {
		Customer customer = customerService.getCustomer(id);
		customerMapper.updateCustomer(payload, customer);
		customerService.updateCustomer(customer);
		return Response.noContent().build();
	}
	
	@DELETE
	@Path("{id}")
	public Response deleteCustomer(@PathParam("id") String id) {
		customerService.deleteCustomer(id);
		return Response.noContent().build();
	}
}
