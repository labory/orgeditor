package com.carcasser.orgeditor.server.dispatch;

import com.carcasser.orgeditor.server.service.OrganizationService;
import com.carcasser.orgeditor.shared.dispatch.OrganizationsResult;
import com.carcasser.orgeditor.shared.dispatch.RemoveOrganizationAction;
import com.gwtplatform.dispatch.rpc.server.ExecutionContext;
import com.gwtplatform.dispatch.rpc.server.actionhandler.AbstractActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

import javax.inject.Inject;
import java.util.Arrays;

/**
 * Remove organization.
 */
public class RemoveOrganizationHandler extends AbstractActionHandler<RemoveOrganizationAction, OrganizationsResult> {

    @Inject
    private OrganizationService organizationService;

    public RemoveOrganizationHandler() {
        super(RemoveOrganizationAction.class);
    }

    @Override
    public OrganizationsResult execute(RemoveOrganizationAction action, ExecutionContext context)
            throws ActionException {
        organizationService.removeByName(action.getName());
        return new OrganizationsResult(Arrays.asList(organizationService.findByName(action.getName())));
    }

    @Override
    public Class<RemoveOrganizationAction> getActionType() {
        return RemoveOrganizationAction.class;
    }

    @Override
    public void undo(RemoveOrganizationAction action, OrganizationsResult result, ExecutionContext context)
            throws ActionException {
        // Not undoable
    }
}
