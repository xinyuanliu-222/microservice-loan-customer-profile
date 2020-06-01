package com.cathy.customer.api.model;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;


/**
 * API model with details of a product query result.
 *
 * @author Cathy
 */

public class QueryCustomerResult {

	private String id;
	
	private String firstname;
	
	private String lastname;
	
	@NotNull
	@NumberFormat
	private String ssn;
	
	private String address;
	
	@NumberFormat
	private String phone;
	
	@Email
	private String email;
	
	@NotNull
	@NumberFormat
	private int amount;
	
	
	@DateTimeFormat
	private Date duedate;
	
	@DateTimeFormat
	private Date applydate;
	
	private boolean approve;

	public String getId() {
		return id;
	}

	public QueryCustomerResult setId(String id) {
		this.id = id;
		return this;
	}
	
	public String getFirstname() {
		return firstname;
	}

	public QueryCustomerResult setFirstname(String firstname) {
		this.firstname = firstname;
		return this;
	}

	public String getLastname() {
		return lastname;
	}

	public QueryCustomerResult setLastname(String lastname) {
		this.lastname = lastname;
		return this;
	}

	public String getSsn() {
		return ssn;
	}

	public QueryCustomerResult setSsn(String ssn) {
		this.ssn = ssn;
		return this;
	}

	public String getAddress() {
		return address;
	}

	public QueryCustomerResult setAddress(String address) {
		this.address = address;
		return this;
	}

	public String getPhone() {
		return phone;
	}

	public QueryCustomerResult setPhone(String phone) {
		this.phone = phone;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public QueryCustomerResult setEmail(String email) {
		this.email = email;
		return this;
	}

	public int getAmount() {
		return amount;
	}

	public QueryCustomerResult setAmount(int amount) {
		this.amount = amount;
		return this;
	}

	public Date getDuedate() {
		return duedate;
	}

	public QueryCustomerResult setDuedate(Date duedate) {
		this.duedate = duedate;
		return this;
	}

	public Date getApplydate() {
		return applydate;
	}

	public QueryCustomerResult setApplydate(Date applydate) {
		this.applydate = applydate;
		return this;
	}

	public boolean isApprove() {
		return approve;
	}

	public QueryCustomerResult setApprove(boolean approve) {
		this.approve = approve;
		return this;
	}
	
	
}
