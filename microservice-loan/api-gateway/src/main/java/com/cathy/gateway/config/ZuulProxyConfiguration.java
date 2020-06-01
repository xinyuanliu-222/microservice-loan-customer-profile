package com.cathy.gateway.config;

import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cathy.gateway.filter.CustomLocationRewriteFilter;

@Configuration
@EnableZuulProxy
public class ZuulProxyConfiguration {

	@Bean
	public CustomLocationRewriteFilter locationRewriteFilter() {
		return new CustomLocationRewriteFilter();
	}
}
