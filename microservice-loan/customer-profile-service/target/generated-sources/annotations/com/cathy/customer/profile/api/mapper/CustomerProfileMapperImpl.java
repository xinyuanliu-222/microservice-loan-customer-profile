package com.cathy.customer.profile.api.mapper;

import com.cathy.customer.profile.api.model.CreateOrUpdateCustomerProfilePayload;
import com.cathy.customer.profile.api.model.QueryCustomerProfileResult;
import com.cathy.customer.profile.domain.CustomerProfile;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class CustomerProfileMapperImpl implements CustomerProfileMapper {

    @Override
    public CustomerProfile toCustomerProfiel(CreateOrUpdateCustomerProfilePayload payLoad) {
        if ( payLoad == null ) {
            return null;
        }

        CustomerProfile customerProfile = new CustomerProfile();

        return customerProfile;
    }

    @Override
    public QueryCustomerProfileResult toQueryCustomerProfileResult(CustomerProfile customerProfile) {
        if ( customerProfile == null ) {
            return null;
        }

        QueryCustomerProfileResult queryCustomerProfileResult = new QueryCustomerProfileResult();

        queryCustomerProfileResult.setId( customerProfile.getId() );
        queryCustomerProfileResult.setCreatedDate( customerProfile.getCreatedDate() );

        return queryCustomerProfileResult;
    }

    @Override
    public void updateCustomerProfile(CreateOrUpdateCustomerProfilePayload payLoad, CustomerProfile customerProfile) {
        if ( payLoad == null ) {
            return;
        }
    }
}
