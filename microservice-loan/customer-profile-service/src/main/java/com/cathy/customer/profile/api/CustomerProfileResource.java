package com.cathy.customer.profile.api;

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

import com.cathy.customer.profile.api.mapper.CustomerProfileMapper;
import com.cathy.customer.profile.api.model.CreateOrUpdateCustomerProfilePayload;
import com.cathy.customer.profile.api.model.QueryCustomerProfileResult;
import com.cathy.customer.profile.domain.CustomerProfile;
import com.cathy.customer.profile.service.CustomerProfileService;

@Component
@Path("profiles")
public class CustomerProfileResource {

	@Context
	private UriInfo uriInfo;
	
	@Autowired
	private CustomerProfileMapper customerProfileMapper;
	
	@Autowired
	private CustomerProfileService customerProfileService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCustomerProfiles() {
		List<CustomerProfile> customerProfiles = customerProfileService.getCustomerProfiles();
		List<QueryCustomerProfileResult> results = customerProfiles.stream().map(customerProfileMapper::toQueryCustomerProfileResult).collect(Collectors.toList());
		return Response.ok(results).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createCustomerProfile(@Valid @NotNull CreateOrUpdateCustomerProfilePayload payload) {
		CustomerProfile customerProfile = customerProfileMapper.toCustomerProfiel(payload);
		String id = customerProfileService.createCustomerProfile(customerProfile);
		return Response.created(uriInfo.getAbsolutePathBuilder().path(id).build()).build();
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCustomerProfile(@PathParam("id") String id) {
		CustomerProfile customerProfile = customerProfileService.getCustomerProfile(id);
		QueryCustomerProfileResult result = customerProfileMapper.toQueryCustomerProfileResult(customerProfile);
		return Response.ok(result).build();
	}
	
	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateCustomerProfile(@PathParam("id") String id, @Valid @NotNull CreateOrUpdateCustomerProfilePayload payload) {
		CustomerProfile customerProfile = customerProfileService.getCustomerProfile(id);
		customerProfileMapper.updateCustomerProfile(payload, customerProfile);
		customerProfileService.updateCustomerProfile(customerProfile);
		return Response.noContent().build();
	}
	
	@DELETE
	@Path("{id}")
	public Response deleteCustomerProfile(@PathParam("id") String id) {
		customerProfileService.deleteCustomerProfile(id);
		return Response.noContent().build();
	}
}
