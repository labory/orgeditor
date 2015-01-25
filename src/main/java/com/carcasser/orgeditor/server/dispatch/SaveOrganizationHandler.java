package com.carcasser.orgeditor.server.dispatch;

import com.carcasser.orgeditor.server.service.OrganizationService;
import com.carcasser.orgeditor.shared.Organization;
import com.carcasser.orgeditor.shared.dispatch.OrganizationsResult;
import com.carcasser.orgeditor.shared.dispatch.SaveOrganizationAction;
import com.gwtplatform.dispatch.rpc.server.ExecutionContext;
import com.gwtplatform.dispatch.rpc.server.actionhandler.AbstractActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

import javax.inject.Inject;
import java.util.Collections;

public class SaveOrganizationHandler extends AbstractActionHandler<SaveOrganizationAction, OrganizationsResult> {

    @Inject
    private OrganizationService organizationService;

    public SaveOrganizationHandler() {
        super(SaveOrganizationAction.class);
    }

    @Override
    public OrganizationsResult execute(SaveOrganizationAction action, ExecutionContext context)
            throws ActionException {
        organizationService.save(action.getOrganization());
        return new OrganizationsResult(Collections.<Organization>emptyList());
    }

    @Override
    public Class<SaveOrganizationAction> getActionType() {
        return SaveOrganizationAction.class;
    }

    @Override
    public void undo(SaveOrganizationAction action, OrganizationsResult result, ExecutionContext context)
            throws ActionException {
        // Not undoable
    }
}
