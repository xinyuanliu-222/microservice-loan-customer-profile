package com.cathy.customer.profile.api.model;

import java.time.ZonedDateTime;
import java.util.Date;

public class QueryCustomerProfileResult {

	private String id;
	
	private ZonedDateTime createdDate;
	
	private Customer customer;
	
	public String getId() {
		return id;
	}

	public QueryCustomerProfileResult setId(String id) {
		this.id = id;
		return this;
	}

	public ZonedDateTime getCreatedDate() {
		return createdDate;
	}

	public QueryCustomerProfileResult setCreatedDate(ZonedDateTime createdDate) {
		this.createdDate = createdDate;
		return this;
	}

	public Customer getItem() {
		return customer;
	}

	public QueryCustomerProfileResult setItem(Customer item) {
		this.customer = item;
		return this;
	}


	public static class Customer {
		
		private String id;

		private String firstname;

		private String lastname;

		private String ssn;

		private String address;

		private String phone;

		private String email;

		private int amount;

		private Date duedate;

		private Date applydate;

		private boolean approve;

		public String getId() {
			return id;
		}

		public Customer setId(String id) {
			this.id = id;
			return this;
		}

		public String getFirstname() {
			return firstname;
		}

		public Customer setFirstname(String firstname) {
			this.firstname = firstname;
			return this;
		}

		public String getLastname() {
			return lastname;
		}

		public Customer setLastname(String lastname) {
			this.lastname = lastname;
			return this;
		}

		public String getSsn() {
			return ssn;
		}

		public Customer setSsn(String ssn) {
			this.ssn = ssn;
			return this;
		}

		public String getAddress() {
			return address;
		}

		public Customer setAddress(String address) {
			this.address = address;
			return this;
		}

		public String getPhone() {
			return phone;
		}

		public Customer setPhone(String phone) {
			this.phone = phone;
			return this;
		}

		public String getEmail() {
			return email;
		}

		public Customer setEmail(String email) {
			this.email = email;
			return this;
		}

		public int getAmount() {
			return amount;
		}

		public Customer setAmount(int amount) {
			this.amount = amount;
			return this;
		}

		public Date getDuedate() {
			return duedate;
		}

		public Customer setDuedate(Date duedate) {
			this.duedate = duedate;
			return this;
		}

		public Date getApplydate() {
			return applydate;
		}

		public Customer setApplydate(Date applydate) {
			this.applydate = applydate;
			return this;
		}

		public boolean isApprove() {
			return approve;
		}

		public Customer setApprove(boolean approve) {
			this.approve = approve;
			return this;
		}
	}
}
