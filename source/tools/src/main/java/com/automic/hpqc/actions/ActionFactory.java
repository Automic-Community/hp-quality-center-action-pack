package com.automic.hpqc.actions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.automic.hpqc.constants.Action;
import com.automic.hpqc.exception.AutomicException;

/**
 * @author sumitsamson
 *
 */

/**
 * Action Factory class for initializing the user action. This class will create the UserAction instances based on user
 * action Exception: Exception will be thrown when user action is not valid ,i.e,LOGIN,LOGOUT,READ,CREATE,DELETE etc
 */
public final class ActionFactory {

    private static final Logger LOGGER = LogManager.getLogger(ActionFactory.class);

    private ActionFactory() {
    }

    public static AbstractAction getAction(Action inputAction) throws AutomicException {

        AbstractAction action = null;

        switch (inputAction) {
            case LOGOUT:
                action = new LogoutAction();
                break;
            case LOGIN:
                action = new LoginAction();
                break;
            case READ_DEFECT:
                action = new ReadDefectAction();
                break;
            case CREATE_DEFECT:
                action = new CreateDefectAction();
                break;
            case DELETE_DEFECT:
                action = new DeleteDefectAction();
                break;
            case GET_DOMAIN_INFO:
                action = new ReadDomainAction();
                break;
            case DEFECT_ASSOICATED_LIST:
                action = new DefectAssociatedListAction();
                break;
            case DEFECT_ASSOICATED_USERS_LIST:
                action = new DefectAssociatedUserListAction();
                break;
            case GET_DEFECT_FIELDS:
                action = new QueryDefectFieldsActions();
                break;
            case QUERY_EDITABLE_FIELDS:
                action = new QueryEditableFieldsAction();
                break;
            case UPDATE_DEFECT:
                action = new UpdateDefectAction();
                break;
            case QUERY_DEFECT_FILTERABLE_FIELDS:
                action = new QueryFilterableFieldsAction();
                break;
            case ADD_ATTACHMENT:
                action = new AttachmentAction();
                break;
            case ADD_COMMENT:
                action = new AddCommentAction();
                break;
            case TESTSET_EXECUTION:
                action = new QTPTestsetExecutionAction();
                break;
            case JOB_LAUNCHER:
                action = new JobLauncherAction();
                break;
            case GENERATE_REPORT_TESTSET:
                action = new QTPGenerateReportTestsetAction();
                break;
            case QUERY_ENTITY:
                action = new QueryEntitiesAction();
                break;
            default:
                String msg = "Invalid Action.. Please enter valid action " + Action.getActionNames();
                LOGGER.error(msg);
                throw new AutomicException(msg);
        }
        return action;
    }

}
