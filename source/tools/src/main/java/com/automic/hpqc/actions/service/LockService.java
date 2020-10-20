/**
 * 
 */
package com.automic.hpqc.actions.service;

import javax.ws.rs.core.MediaType;

import com.automic.hpqc.exception.AutomicException;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * This class gets the lock on a defect
 * 
 * @author sumitsamson
 * 
 */

public class LockService {

    /**
     * This method is used to put lock on defect
     * 
     * @param client
     * @param baseUrl
     * @param domain
     * @param project
     * @param defectId
     * @return true
     * @throws AutomicException
     */
    public boolean putLockOnDefect(Client client, String baseUrl, String domain, String project, int defectId)
            throws AutomicException {

        WebResource webResource = client.resource(baseUrl).path("qcbin").path("rest").path("domains").path(domain)
                .path("projects").path(project).path("defects").path(String.valueOf(defectId)).path("/lock");

        webResource.accept(MediaType.APPLICATION_XML).post(ClientResponse.class);

        return true;

    }

}
