package com.cathy.customer.profile.api.model;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class CreateOrUpdateCustomerProfilePayload {

	@Valid
	private Customer customer;
	
	public Customer getItem() {
		return customer;
	}

	public CreateOrUpdateCustomerProfilePayload setItem(Customer customer) {
		this.customer = customer;
		return this;
	}



	public static class Customer {
		
		@NotNull
		private String id;

		public String getId() {
			return id;
		}

		public Customer setId(String id) {
			this.id = id;
			return this;
		}
		
	}
}
