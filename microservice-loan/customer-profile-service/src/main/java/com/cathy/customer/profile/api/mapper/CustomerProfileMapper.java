package com.cathy.customer.profile.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.cathy.customer.profile.api.model.CreateOrUpdateCustomerProfilePayload;
import com.cathy.customer.profile.api.model.QueryCustomerProfileResult;
import com.cathy.customer.profile.domain.CustomerProfile;

@Mapper
public interface CustomerProfileMapper {

	CustomerProfile toCustomerProfiel(CreateOrUpdateCustomerProfilePayload payLoad);
	
	QueryCustomerProfileResult toQueryCustomerProfileResult(CustomerProfile customerProfile);
	
	void updateCustomerProfile(CreateOrUpdateCustomerProfilePayload payLoad, @MappingTarget CustomerProfile customerProfile);
}
