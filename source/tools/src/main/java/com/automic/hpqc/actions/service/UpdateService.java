/**
 * 
 */
package com.automic.hpqc.actions.service;

import javax.ws.rs.core.MediaType;

import com.automic.hpqc.exception.AutomicException;
import com.automic.hpqc.model.Entity;
import com.automic.hpqc.model.Fields;
import com.automic.hpqc.model.LockStatusEntity;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * This class used to update defect, lock on defect
 * 
 * @author sumitsamson
 * 
 */
public class UpdateService {

    private Client client;
    private String baseUrl;
    private String domain;
    private String project;

    public UpdateService(Client client, String baseUrl, String domain, String project) {
        this.client = client;
        this.baseUrl = baseUrl;
        this.domain = domain;
        this.project = project;

    }

    /**
     * This method used to update defect
     * 
     * @param defectId
     * @param fields
     * @return
     * @throws AutomicException
     */
    public Entity updateDefect(int defectId, Fields fields) throws AutomicException {

        ClientResponse response;
        Entity entity = new Entity("defect", fields);
        WebResource webResource = client.resource(baseUrl).path("qcbin").path("rest").path("domains").path(domain)
                .path("projects").path(project).path("defects").path(String.valueOf(defectId));

        response = webResource.entity(entity).type(MediaType.APPLICATION_XML).accept(MediaType.APPLICATION_XML)
                .put(ClientResponse.class);
        entity = response.getEntity(Entity.class);
        return entity;
    }

    /**
     * This method used to check lock on defect
     * 
     * @param defectId
     * @return
     */
    public LockStatusEntity checkLockOnDefect(String defectId) {

        ClientResponse response;
        LockStatusEntity lockStatusEntity = null;

        WebResource webResource = client.resource(baseUrl).path("qcbin").path("rest").path("domains").path(domain)
                .path("projects").path(project).path("defects").path(defectId).path("lock");

        response = webResource.accept(MediaType.APPLICATION_XML).get(ClientResponse.class);
        lockStatusEntity = response.getEntity(LockStatusEntity.class);

        return lockStatusEntity;

    }

}
