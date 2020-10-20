package com.automic.hpqc.filter;

import java.util.List;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.automic.hpqc.constants.Constants;
import com.automic.hpqc.exception.AutomicRuntimeException;
import com.automic.hpqc.model.QCRestError;
import com.sun.jersey.api.client.ClientRequest;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.filter.ClientFilter;

/**
 * This class acts as a filter and intercept the response to validate it.
 * 
 */

public class GenericResponseFilter extends ClientFilter {

    private static final int HTTP_SUCCESS_START = 200;
    private static final int HTTP_SUCCESS_END = 299;

    private static final Logger LOGGER = LogManager.getLogger(GenericResponseFilter.class);

    @Override
    public ClientResponse handle(ClientRequest request) {
        // cookie addition/updation goes here

        ClientResponse response = getNext().handle(request);
        if (!(response.getStatus() >= HTTP_SUCCESS_START && response.getStatus() <= HTTP_SUCCESS_END)) {
            LOGGER.error("Response code for " + request.getURI() + " is " + response.getStatus());
            String errorMsg = getHttpErrorMsg(response);
            LOGGER.error(errorMsg);
            throw new AutomicRuntimeException(errorMsg);

        }
        return response;
    }

    public String getHttpErrorMsg(ClientResponse response) {

        QCRestError errorMsg = null;
        int httpcode = response.getStatus();
        String msg = httpcode + " : ";
        switch (httpcode) {

            case Constants.HTTP_BAD_REQUEST:
                msg += " Bad request. Syntax or format error";
                break;
            case Constants.HTTP_UNAUTHORIZED:
                msg += " User not authenticated";
                break;
            case Constants.HTTP_FORBIDDEN:
                msg += " User not authorized to perform requested operation";
                break;
            case Constants.HTTP_INTERNAL_SERVER_ERROR:
                MultivaluedMap<String, String> headers = response.getHeaders();

                List<String> contentType = headers.get(HttpHeaders.CONTENT_TYPE);

                if (contentType != null && MediaType.APPLICATION_XML.equalsIgnoreCase(contentType.get(0))) {

                    errorMsg = response.getEntity(QCRestError.class);
                    msg += errorMsg.getTitle();
                } else {
                    msg += response.getEntity(String.class);
                }
                break;
            case Constants.HTTP_NOT_FOUND:
                msg += " Resource not found";
                break;
            case Constants.HTTP_METHOD_NOT_ALLOWED:
                msg += " Method not supported by resource";
                break;
            case Constants.HTTP_UNSUPPORTED_MEDIA_TYPE:
                msg += " Unsupported request content type";
                break;
            case Constants.HTTP_NOT_ACCEPTABLE:
                msg += " Unsupported ACCEPT type";
                break;
            default:
        }
        return msg;
    }

}
