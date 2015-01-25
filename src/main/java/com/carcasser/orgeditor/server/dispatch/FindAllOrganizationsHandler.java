package com.carcasser.orgeditor.server.dispatch;

import com.carcasser.orgeditor.server.service.OrganizationService;
import com.carcasser.orgeditor.shared.dispatch.FindAllOrganizationsAction;
import com.carcasser.orgeditor.shared.dispatch.OrganizationsResult;
import com.gwtplatform.dispatch.rpc.server.ExecutionContext;
import com.gwtplatform.dispatch.rpc.server.actionhandler.AbstractActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

import javax.inject.Inject;

public class FindAllOrganizationsHandler extends AbstractActionHandler<FindAllOrganizationsAction, OrganizationsResult> {

    @Inject
    private OrganizationService organizationService;

    public FindAllOrganizationsHandler() {
        super(FindAllOrganizationsAction.class);
    }

    @Override
    public OrganizationsResult execute(FindAllOrganizationsAction action, ExecutionContext context)
            throws ActionException {

        return new OrganizationsResult(organizationService.findAll());
    }

    @Override
    public Class<FindAllOrganizationsAction> getActionType() {
        return FindAllOrganizationsAction.class;
    }

    @Override
    public void undo(FindAllOrganizationsAction action, OrganizationsResult result, ExecutionContext context)
            throws ActionException {
        // Not undoable
    }
}
