package com.automic.hpqc.filter;

import java.util.List;

import com.automic.hpqc.model.AuthenticationToken;
import com.sun.jersey.api.client.ClientRequest;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.filter.ClientFilter;

import javax.ws.rs.core.NewCookie;

/**
 * This class acts as a filter and intercept the request to validate its provided token id. If token id expire then
 * generate new token id by {@code AuthenticationTokenSevice} service and update request token id.
 * 
 */

public final class AuthenticationFilter extends ClientFilter {

    private AuthenticationToken atToken;

    public AuthenticationFilter(AuthenticationToken atToken) {
        this.atToken = atToken;
    }

    /**
     * {@inheritDoc} This method validate token id from request and validate its. If token id expire generate new token
     * token id and update request
     */
    @Override
    public ClientResponse handle(ClientRequest request) {
        // add cookies to request
    	List<NewCookie> cookies = atToken.getNewCookies();
    	if(cookies.size() != 0 ) {
    		StringBuilder cookiesBuilder = new StringBuilder();
    		for(int i=0;i<cookies.size();i++) {
    			if("XSRF-TOKEN".equals(cookies.get(i).getName())) {
    				request.getHeaders().add("X-XSRF-TOKEN", cookies.get(i).getValue());
    			} else {
    				if(cookiesBuilder.length() != 0) {
    					cookiesBuilder.append("; ");
    				}
    				cookiesBuilder.append(cookies.get(i).getName()).append("=").append(cookies.get(i).getValue());
    			}    			
    		}
    		request.getHeaders().add("Cookie", cookiesBuilder);    				
    	}    	
        ClientResponse response = getNext().handle(request);
        atToken.putNewCookies(response.getCookies());
        return response;
    }

}
