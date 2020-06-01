package com.cathy.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;

import com.cathy.customer.service.CustomerOutputChannel;

/**
 * Spring Boot application entry point.
 *
 * @author Cathy
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableBinding({CustomerOutputChannel.class})
public class Application 
{
    public static void main( String[] args )
    {
        SpringApplication.run(Application.class, args);
    }
}
