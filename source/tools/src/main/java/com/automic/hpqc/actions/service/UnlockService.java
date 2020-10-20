/**
 * 
 */
package com.automic.hpqc.actions.service;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * This class used to remove lock on defect
 * 
 * @author sumitsamson
 * 
 */
public class UnlockService {

    /**
     * This method remove lock on defect
     * 
     * @param client
     * @param baseUrl
     * @param domain
     * @param project
     * @param defectId
     * @return
     */
    public boolean removeLockOnDefect(Client client, String baseUrl, String domain, String project, int defectId) {

        WebResource webResource = client.resource(baseUrl).path("qcbin").path("rest").path("domains").path(domain)
                .path("projects").path(project).path("defects").path(String.valueOf(defectId)).path("lock");

        webResource.accept(MediaType.APPLICATION_XML).delete(ClientResponse.class);

        return true;

    }

}
