package com.cathy.gateway.filter;

import org.springframework.cloud.netflix.zuul.filters.post.LocationRewriteFilter;
import org.springframework.http.HttpStatus;

import com.netflix.zuul.context.RequestContext;

public class CustomLocationRewriteFilter extends LocationRewriteFilter {

	@Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        int statusCode = ctx.getResponseStatusCode();
        return HttpStatus.valueOf(statusCode).is3xxRedirection() || HttpStatus.valueOf(statusCode) == HttpStatus.CREATED;
    }
}
