package com.cathy.customer.api.model;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import com.cathy.customer.domain.Customer;

/**
 * API model with details required to create a product.
 *
 * @author Cathy
 */

public class CreateOrUpdateCustomerPayload {
	
	@NotNull
	@NotBlank
	private String firstname;
	
	@NotNull
	@NotBlank
	private String lastname;
	
	@NotNull
	@NotBlank
	@NumberFormat
	private String ssn;
	
	@NotNull
	@NotBlank
	private String address;
	
	@NumberFormat
	private String phone;
	
	@Email
	private String email;
	
	@NotNull
	@NotBlank
	@NumberFormat
	private int amount;
	
	
	@DateTimeFormat
	private Date duedate;
	
	@DateTimeFormat
	private Date applydate;
	
	private boolean approve;

	
	public String getFirstname() {
		return firstname;
	}

	public CreateOrUpdateCustomerPayload setFirstname(String firstname) {
		this.firstname = firstname;
		return this;
	}

	public String getLastname() {
		return lastname;
	}

	public CreateOrUpdateCustomerPayload setLastname(String lastname) {
		this.lastname = lastname;
		return this;
	}

	public String getSsn() {
		return ssn;
	}

	public CreateOrUpdateCustomerPayload setSsn(String ssn) {
		this.ssn = ssn;
		return this;
	}

	public String getAddress() {
		return address;
	}

	public CreateOrUpdateCustomerPayload setAddress(String address) {
		this.address = address;
		return this;
	}

	public String getPhone() {
		return phone;
	}

	public CreateOrUpdateCustomerPayload setPhone(String phone) {
		this.phone = phone;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public CreateOrUpdateCustomerPayload setEmail(String email) {
		this.email = email;
		return this;
	}

	public int getAmount() {
		return amount;
	}

	public CreateOrUpdateCustomerPayload setAmount(int amount) {
		this.amount = amount;
		return this;
	}

	public Date getDuedate() {
		return duedate;
	}

	public CreateOrUpdateCustomerPayload setDuedate(Date duedate) {
		this.duedate = duedate;
		return this;
	}

	public Date getApplydate() {
		return applydate;
	}

	public CreateOrUpdateCustomerPayload setApplydate(Date applydate) {
		this.applydate = applydate;
		return this;
	}

	public boolean isApprove() {
		return approve;
	}

	public CreateOrUpdateCustomerPayload setApprove(boolean approve) {
		this.approve = approve;
		return this;
	}
	
}
