# Customer Profile Managment System
This is POC project of **Customer Profile Managment System**. It is a web based appication using **Microservice** architecture with **Spring Boot, Spring Cloud and JMS**. Users can sumbit, manage, check their profile (personal information) through this web system.
This project provide the backend functionality of this system. There are 4 serbices created for accomplish this functionality. They are **_Netflix Eureka Service, API Gateway, Customer Service, Customer Profile Service._**

## Netflix Eureka Service
This service (**service-discovery**) is used to discover and register other services. The annotation:
```
@EnableEurekaServer
```

## Customer Service
This **customer-service** is a **RESTful** web service using **Jersey (JAX-RS)** implementation. This REST API perfomrs CRUD operation on the customer resource to retrieve and return data from database. Users can update and create their own information through **PUT** and **POST** methods and manager or employee can search specified customer based on customer id using **GET** method. This service connected to the **MongoDB** database using Spring Data for persisting the customer data - the personal information of user. Relational annotations:
```
@Component
@Path //specify the relative path of class and methods
@GET
@PUT
@POST
@DELETE
@Produce //states the HTTP response generated by web service
@Consume //states the HTTP request type
```

In order to access the JAX-RS resource from Spring Boot Application, this resource should be registered as **Jersey resource**.
```
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
```

Register for Eureka Server:
```
@EnableDiscoveryClient //I used
```
Or
```
@EnableEurekaClient
```

## Customer Profile Server
This **customer-profile-server** is also a **RESTful** web service which has same implementation as customer-service. It connected to the **MongoDB** database using Spring Data for persisting customer profile data including profile Id, customer and created date. The customer data are transformed from customer service using Spring Cloud Stream-**Rabbit MQ**, which is a message broker between these 2 services for communication.

Considering the highly demanding of customer data, I configured and enable a data **caching** for customer data to improve application performance. Thus, customer data can be read from cache instead from database which is faster. But cache has a limited memory storage, I **evict** the expired customer data which are not frequently used from cache when the cache is full. Related annotations:
```
@EnableCaching
@CacheEvict(cacheNames = CachingConfiguration.CUSTOMER_CACHE, key="#customer.id")
```

## API Gateway
It consist of **Netflix Zuul Proxy** and **Spring Security JWT**. It works as a single point of interaction for different operations. Spring Security which handles the authentication and authorization at the web request level. It uses JWT for authentication by verifying user’s credentials and generate access token. The Zuul proxy will routing the client business request by communicating to relevant Microservice endpoints. 

