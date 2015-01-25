package com.carcasser.orgeditor.server.dispatch;

import com.carcasser.orgeditor.server.service.OrganizationService;
import com.carcasser.orgeditor.shared.dispatch.GetOrganizationAction;
import com.carcasser.orgeditor.shared.dispatch.OrganizationsResult;
import com.gwtplatform.dispatch.rpc.server.ExecutionContext;
import com.gwtplatform.dispatch.rpc.server.actionhandler.AbstractActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

import javax.inject.Inject;
import java.util.Arrays;

public class GetOrganizationHandler extends AbstractActionHandler<GetOrganizationAction, OrganizationsResult> {

    @Inject
    private OrganizationService organizationService;

    public GetOrganizationHandler() {
        super(GetOrganizationAction.class);
    }

    @Override
    public OrganizationsResult execute(GetOrganizationAction action, ExecutionContext context)
            throws ActionException {

        return new OrganizationsResult(Arrays.asList(organizationService.findByName(action.getName())));
    }

    @Override
    public Class<GetOrganizationAction> getActionType() {
        return GetOrganizationAction.class;
    }

    @Override
    public void undo(GetOrganizationAction action, OrganizationsResult result, ExecutionContext context)
            throws ActionException {
        // Not undoable
    }
}
