package com.cathy.customer.profile.domain;

import java.util.Date;
import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

@Document
public class Customer {

	@Id
	private String id;

	@NotNull
	@Transient
	private String firstname;

	@NotNull
	@Transient
	private String lastname;

	@NotNull
	@NumberFormat
	@Transient
	private String ssn;

	@Transient
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

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Customer product = (Customer) o;
		return Objects.equals(id, product.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
