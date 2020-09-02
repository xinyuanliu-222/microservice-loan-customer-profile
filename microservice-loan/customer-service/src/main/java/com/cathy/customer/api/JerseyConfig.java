package com.cathy.customer.api;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.cathy.common.api.provider.*;

@Component
@ApplicationPath("api")
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
		registerResources();
		registerProviders();
	}
	
	private void registerResources() {
		register(CustomerResource.class);
	}
	
	private void registerProviders() {
		register(ObjectMapperContextResolver.class);
        	register(ConstraintViolationExceptionMapper.class);
        	register(GenericExceptionMapper.class);
        	register(JsonMappingExceptionMapper.class, 1);
        	register(JsonParseExceptionMapper.class, 1);
	}
}
