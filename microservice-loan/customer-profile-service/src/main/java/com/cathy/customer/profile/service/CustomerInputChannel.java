package com.cathy.customer.profile.service;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * Messaging channel definition.
 *
 * @author Cathy
 */

public interface CustomerInputChannel {

	String CUSTOMER_DELETED_INPUT = "customerDeletedInput";

    String CUSTOMER_UPDATED_INPUT = "customerUpdatedInput";

    @Input(CUSTOMER_DELETED_INPUT)
    SubscribableChannel productDeletedInput();

    @Input(CUSTOMER_UPDATED_INPUT)
    SubscribableChannel productUpdatedInput();
}
