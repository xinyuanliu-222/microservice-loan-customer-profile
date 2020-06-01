package com.cathy.customer.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.cathy.customer.api.model.CreateOrUpdateCustomerPayload;
import com.cathy.customer.api.model.QueryCustomerResult;
import com.cathy.customer.domain.Customer;

/**
 * Component that maps a domain model to an API model and vice-versa.
 *
 * @author Cathy
 */

@Mapper
public interface CustomerMapper {

	Customer toCustomer(CreateOrUpdateCustomerPayload payload);
	
	QueryCustomerResult toQueryCustomerResult(Customer customer);
	
	void updateCustomer(CreateOrUpdateCustomerPayload payload, @MappingTarget Customer customer);
}
