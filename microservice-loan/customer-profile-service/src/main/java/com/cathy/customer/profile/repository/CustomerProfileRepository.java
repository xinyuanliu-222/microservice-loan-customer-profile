package com.cathy.customer.profile.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cathy.customer.profile.domain.CustomerProfile;

public interface CustomerProfileRepository extends MongoRepository<CustomerProfile, String>, CustomerProfileRepositoryCustom {

}
