package com.cathy.customer.profile.domain;

import java.time.ZonedDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jersey.repackaged.com.google.common.base.Objects;

@Document
public class CustomerProfile {

	@Id
	private String id;
	
	@CreatedDate
	private ZonedDateTime createdDate;
	
	private Customer customer;

	public String getId() {
		return id;
	}

	public CustomerProfile setId(String id) {
		this.id = id;
		return this;
	}

	public ZonedDateTime getCreatedDate() {
		return createdDate;
	}

	public CustomerProfile setCreatedDate(ZonedDateTime createdDate) {
		this.createdDate = createdDate;
		return this;
	}

	public Customer getCustomer() {
		return customer;
	}

	public CustomerProfile setCustomer(Customer customer) {
		this.customer = customer;
		return this;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		CustomerProfile that = (CustomerProfile) obj;
		return Objects.equal(id, that.id);
	}
	
	@Override
	public int hashCode() {
		return Objects.hashCode(id);
	}
}
