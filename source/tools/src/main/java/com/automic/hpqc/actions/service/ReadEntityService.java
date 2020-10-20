/**
 * 
 */
package com.automic.hpqc.actions.service;

import javax.ws.rs.core.MediaType;

import com.automic.hpqc.model.Entity;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * This class is used to get entity
 * 
 * @author sumitsamson
 * 
 */
public class ReadEntityService {

    /**
     * This method is used to get entity     * 
     * @param client
     * @param baseUrl
     * @param domain
     * @param project
     * @param entityType
     * @param defectId
     * @return entity {@code Entity}
     */
    public Entity readEntity(Client client, String baseUrl, String domain, String project, String entityType,
            int defectId) {

        WebResource webResource = client.resource(baseUrl).path("qcbin").path("rest").path("domains").path(domain)
                .path("projects").path(project).path(entityType).path(String.valueOf(defectId));

        ClientResponse response = webResource.accept(MediaType.APPLICATION_XML).get(ClientResponse.class);

        Entity entity = response.getEntity(Entity.class);

        return entity;

    }

}
