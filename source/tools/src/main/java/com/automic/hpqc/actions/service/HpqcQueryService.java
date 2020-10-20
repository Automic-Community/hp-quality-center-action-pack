/**
 * 
 */
package com.automic.hpqc.actions.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.ws.rs.core.MediaType;

import com.automic.hpqc.constants.Constants;
import com.automic.hpqc.exception.AutomicException;
import com.automic.hpqc.model.Entities;
import com.automic.hpqc.model.Entity;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * This class is use for Query Services
 * 
 * @author sumitsamson
 * 
 */
public class HpqcQueryService {

    private static final int DEFAULT_PAGE_SIZE = 50;

    /**
     * this method is used to get Query Artifact
     * 
     * @param client
     * @param baseUrl
     * @param domain
     * @param project
     * @param artifact
     * @param filter
     * @param listOfFields
     * @param maxResultsInput
     * @return {@code Entities} object
     * @throws UnsupportedEncodingException
     * @throws AutomicException
     */
    public static Entities queryArtifact(Client client, String baseUrl, String domain, String project, String artifact,
            String filter, String listOfFields, int maxResultsInput) throws UnsupportedEncodingException,
            AutomicException {

        int maxResults = maxResultsInput;

        int pageSize = DEFAULT_PAGE_SIZE;

        if (maxResults < Constants.MINUS_ONE) {

            throw new AutomicException("Invalid Max result number");
        }

        if (maxResults < pageSize && maxResults > Constants.MINUS_ONE) {

            pageSize = (maxResults == Constants.ZERO) ? Constants.ONE : maxResults;
        }

        String encodedQuery = (filter.length() > Constants.ZERO) ? URLEncoder.encode("{" + filter + "}", "UTF-8") : "";
        WebResource webResource = client.resource(baseUrl).path("qcbin").path("rest").path("domains").path(domain)
                .path("projects").path(project).path(artifact);

        if (!encodedQuery.isEmpty()) {
            webResource = webResource.queryParam("query", encodedQuery);
        }
        if (listOfFields.length() > Constants.ZERO) {
            webResource = webResource.queryParam("fields", listOfFields);
        }

        return getEntites(webResource, pageSize, maxResultsInput);

    }

    private static Entities getEntites(WebResource webResource, int pageSize, int maxResultsInput) {

        int startIndex = Constants.ZERO;
        int actualRecords = Constants.ZERO;
        ClientResponse response = null;
        int maxResults = maxResultsInput;
        Entities finalEntities = new Entities();
        ArrayList<Entity> entityList = new ArrayList<Entity>();
        do {

            webResource = webResource.queryParam("page-size", String.valueOf(pageSize)).queryParam("start-index",
                    String.valueOf(startIndex + Constants.ONE));

            response = webResource.accept(MediaType.APPLICATION_XML).get(ClientResponse.class);

            Entities entities = response.getEntity(Entities.class);

            if (entities.getEntity() != null && !entities.getEntity().isEmpty()) {
                if (entityList.isEmpty()) {

                    actualRecords = entities.getTotalResults();
                    maxResults = (maxResults == Constants.MINUS_ONE) ? actualRecords : maxResults;

                }

                entityList.addAll(entities.getEntity());

                if (entities.getEntity().size() < pageSize) {
                    break;
                }
                startIndex = entityList.size();
                if (entityList.size() == maxResults) {
                    break;
                }
                if (maxResults - entityList.size() < pageSize) {
                    pageSize = maxResults - entityList.size();
                    if (pageSize < Constants.ZERO) {
                        break;
                    }
                }
            } else {
                break;
            }
        } while (true);

        finalEntities.setTotalResults(actualRecords);
        if (maxResults != Constants.ZERO) {
            finalEntities.setEntity(entityList);
        }
        return finalEntities;
    }

}
