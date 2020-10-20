/**
 * 
 */
package com.automic.hpqc.actions.service;

import javax.ws.rs.core.MediaType;

import com.automic.hpqc.model.Domain;
import com.automic.hpqc.model.Domains;
import com.automic.hpqc.model.Projects;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * This class is used to ger projects
 * 
 * @author sumitsamson
 * 
 */
public class ProjectService {

    /**
     * This method is used to get projects
     * 
     * @param client
     * @param baseUrl
     * @param domains
     * @return domains {@code Domains}
     * @throws HpqcException
     */
    public Domains getProjects(Client client, String baseUrl, Domains domains) {

        ClientResponse response = null;

        for (Domain domain : domains.getDomain()) {

            WebResource webResource = client.resource(baseUrl).path("qcbin").path("rest").path("domains")
                    .path(domain.getName()).path("projects");

            response = webResource.accept(MediaType.APPLICATION_XML).get(ClientResponse.class);

            Projects projects = response.getEntity(Projects.class);
            projects.setDomain(domain.getName());
            domain.setProjects(projects);

        }
        return domains;

    }

}
