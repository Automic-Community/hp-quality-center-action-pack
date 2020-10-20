/**
 * 
 */
package com.automic.hpqc.actions.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ws.rs.core.MediaType;

import com.automic.hpqc.constants.Constants;
import com.automic.hpqc.model.AuthenticationInfo;
import com.automic.hpqc.model.Entities;
import com.automic.hpqc.model.Entity;
import com.automic.hpqc.model.Field;
import com.automic.hpqc.model.Users;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * This class is used for user provided services
 * 
 * @author sumitsamson
 * 
 */
public class HPQCUserService {

    /**
     * This method is used to get available users list
     * 
     * @param baseUrl
     * @param domain
     * @param project
     * @param client
     * @return users {@code Users}
     */
    public Users getUserList(String baseUrl, String domain, String project, Client client) {

        ClientResponse response = null;

        WebResource webResource = client.resource(baseUrl).path("qcbin").path("rest").path("domains").path(domain)
                .path("projects").path(project).path("customization").path("users");

        response = webResource.accept(MediaType.APPLICATION_XML).get(ClientResponse.class);

        Users users = response.getEntity(Users.class);

        return users;
    }

    /**
     * This method is used to get available resources
     * 
     * @param baseUrl
     * @param domain
     * @param project
     * @param client
     * @return resources list
     * @throws ClientHandlerException
     */
    public List<String> getResources(String baseUrl, String domain, String project, Client client)
            throws ClientHandlerException {

        ClientResponse response = null;
        List<String> resourceVal = Collections.emptyList();
        WebResource webResource = client.resource(baseUrl).path("qcbin").path("rest").path("domains").path(domain)
                .path("projects").path(project).path("resources");

        response = webResource.accept(MediaType.APPLICATION_XML).get(ClientResponse.class);

        Entities entities = response.getEntity(Entities.class);
        if (entities.getTotalResults() > Constants.ZERO) {
            for (Entity entity : entities.getEntity()) {
                if ("defect".equalsIgnoreCase(entity.getType()) && entity.getFields() != null) {
                    resourceVal = new ArrayList<String>();
                    for (Field field : entity.getFields().getFieldList()) {
                        resourceVal.add(field.getValue());
                    }

                }
            }
        }

        return resourceVal;
    }

    /**
     * This method is used to read defect
     * 
     * @param client
     * @param baseUrl
     * @param domain
     * @param project
     * @param defectId
     * @return entity {@code Entity}
     * @throws ClientHandlerException
     */
    public Entity readDefect(Client client, String baseUrl, String domain, String project, String defectId)
            throws ClientHandlerException {

        Entity entity = null;

        WebResource webResource = client.resource(baseUrl).path("qcbin").path("rest").path("domains").path(domain)
                .path("projects").path(project).path("defects").path(defectId);

        ClientResponse response = webResource.accept(MediaType.APPLICATION_XML).get(ClientResponse.class);

        entity = response.getEntity(Entity.class);

        return entity;

    }

    /**
     * This method is used to check is user authorized.
     * 
     * @param client
     * @param baseUrl
     * @return username {@code String}
     */
    public String getLoginUser(Client client, String baseUrl) {
        String username = "";

        WebResource webResource = client.resource(baseUrl).path("qcbin").path("rest").path("is-authenticated");

        ClientResponse response = webResource.accept(MediaType.APPLICATION_XML).get(ClientResponse.class);

        AuthenticationInfo authInfo = response.getEntity(AuthenticationInfo.class);
        username = authInfo.getUsername();

        return username;
    }

}
