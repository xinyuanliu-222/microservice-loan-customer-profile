package com.cathy.customer.service;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface CustomerOutputChannel {

	String CUSTOMER_DELETED_OUTPUT = "customerDeletedOutput";
	
	String CUSTOMER_UPDATED_OUTPUT = "customerUpdatedOutput";
	
	@Output(CUSTOMER_DELETED_OUTPUT)
	MessageChannel customerDeletedOutput();
	
	@Output(CUSTOMER_UPDATED_OUTPUT)
	MessageChannel customerUpdatedOutput();
}
