/**
 * 
 */
package com.automic.hpqc.actions.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import com.automic.hpqc.model.Entities;
import com.automic.hpqc.model.Entity;
import com.automic.hpqc.model.Field;
import com.automic.hpqc.model.Fields;
import com.automic.hpqc.model.Items;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.automic.hpqc.model.Lists;

/**
 * This class used to provide entity related services
 * 
 * @author sumitsamson
 * 
 */
public class EntityService {

    private Client client;
    private String domain;
    private String project;
    private String baseUrl;

    public EntityService(Client client, String baseUrl, String domain, String project) {
        this.client = client;
        this.domain = domain;
        this.project = project;
        this.baseUrl = baseUrl;
    }

    /**
     * This method is used get entity related fields
     * 
     * @param entity
     * @param isRequired
     * @return fields {@code Fields}
     */
    public Fields getFieldsForEntity(String entity, boolean isRequired) {

        ClientResponse response = null;
        Fields fields = null;

        WebResource webResource = client.resource(this.baseUrl).path("qcbin").path("rest").path("domains").path(domain)
                .path("projects").path(project).path("customization").path("entities").path(entity).path("fields");

        if (isRequired) {
            webResource = webResource.queryParam("required", "true");
        }

        response = webResource.accept(MediaType.APPLICATION_XML).get(ClientResponse.class);
        fields = response.getEntity(Fields.class);

        return fields;

    }

    /**
     * This method is used to get entity associated list
     * 
     * @param entity
     * @return lists {@code Lists}
     */
    public Lists getListAssociatedWithEntity(String entity) {

        ClientResponse response = null;
        WebResource webResource = client.resource(this.baseUrl).path("qcbin").path("rest").path("domains").path(domain)
                .path("projects").path(project).path("customization").path("entities").path(entity).path("lists");

        response = webResource.accept(MediaType.APPLICATION_XML).get(ClientResponse.class);
        com.automic.hpqc.model.Lists lists = response.getEntity(com.automic.hpqc.model.Lists.class);

        return lists;
    }

    /**
     * This method is provided entity list as Map {@code Map}
     * 
     * @param lists
     * @return entity list map {@code Map<Integer, List<String>>}
     */
    public Map<Integer, List<String>> getEntityListMap(com.automic.hpqc.model.Lists lists) {

        Map<Integer, List<String>> entityListMap = new HashMap<Integer, List<String>>();

        for (com.automic.hpqc.model.List list : lists.getList()) {

            Items items = list.getItems();

            if (items.getItemList() != null) {
                entityListMap.put(list.getId(), list.getItems().getItemValues());
            }

        }
        return entityListMap;
    }

    /**
     * This method is used to get resources as list
     * 
     * @return resources as list {@code List<String>}
     */
    public List<String> getResources() {

        ClientResponse response = null;

        List<String> resourceVal = new ArrayList<String>();

        WebResource webResource = client.resource(this.baseUrl).path("qcbin").path("rest").path("domains").path(domain)
                .path("projects").path(project).path("resources");

        response = webResource.accept(MediaType.APPLICATION_XML).get(ClientResponse.class);

        Entities entities = response.getEntity(Entities.class);
        if (entities.getTotalResults() > 0) {
            for (Entity entity : entities.getEntity()) {
                if (entity.getFields() != null) {

                    for (Field field : entity.getFields().getFieldList()) {
                        if ("id".equals(field.getName())) {
                            resourceVal.add(field.getValue());
                        }

                    }

                }
            }

        }
        return resourceVal;
    }

    /**
     * This method used to get defect list
     * 
     * @param domainName
     * @param projectName
     * @param listOfItems
     * @return defect list {@code Lists}
     */
    public Lists getListAssociatedWithDefect(String listOfItems) {

        ClientResponse response = null;
        com.automic.hpqc.model.Lists lists = null;

        WebResource webResource = client.resource(this.baseUrl).path("qcbin").path("rest").path("domains").path(domain)
                .path("projects").path(project).path("customization").path("used-lists");

        if ( listOfItems != null && !listOfItems.isEmpty()) {
            webResource = webResource.queryParam("name", listOfItems);

        }

        response = webResource.accept(MediaType.APPLICATION_XML).get(ClientResponse.class);

        lists = response.getEntity(com.automic.hpqc.model.Lists.class);

        return lists;
    }

}
