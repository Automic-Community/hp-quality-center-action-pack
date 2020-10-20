package com.automic.hpqc.constants;

/**
 * @author sumitsamson
 * 
 */

/**
 * Action enum is used to define the valid user action
 */

public enum Action {

    LOGOUT, LOGIN, READ_DEFECT, CREATE_DEFECT, DELETE_DEFECT, GET_DOMAIN_INFO, DEFECT_ASSOICATED_LIST, DEFECT_ASSOICATED_USERS_LIST, GET_DEFECT_FIELDS, QUERY_EDITABLE_FIELDS, UPDATE_DEFECT, QUERY_DEFECT_FILTERABLE_FIELDS, ADD_ATTACHMENT, ADD_COMMENT, TESTSET_EXECUTION, JOB_LAUNCHER, GENERATE_REPORT_TESTSET, QUERY_ENTITY;

    public static String getActionNames() {
        Action[] actions = Action.values();
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < actions.length; i++) {
            sb.append(actions[i].name());
            sb.append(" ");
        }
        sb.append("]");
        return sb.toString();
    }
}
