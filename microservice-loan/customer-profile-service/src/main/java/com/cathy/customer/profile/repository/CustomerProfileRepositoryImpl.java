package com.cathy.customer.profile.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.cathy.customer.profile.domain.CustomerProfile;
import com.mongodb.BasicDBObject;

public class CustomerProfileRepositoryImpl implements CustomerProfileRepositoryCustom {

	@Autowired
	private MongoOperations mongoOperations;
	
	@Override
	public void deleteCustomerById(String id) {
		mongoOperations.updateMulti(new Query(), new Update().pull("customer", new BasicDBObject("id", id)), CustomerProfile.class);
	}
}
