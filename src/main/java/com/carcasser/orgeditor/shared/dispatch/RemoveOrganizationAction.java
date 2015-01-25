package com.carcasser.orgeditor.shared.dispatch;

import com.gwtplatform.dispatch.rpc.shared.UnsecuredActionImpl;

/**
 * Remove organization.
 */
public class RemoveOrganizationAction extends UnsecuredActionImpl<OrganizationsResult> {

    private String name;

    public RemoveOrganizationAction(String name) {
        this.name = name;
    }

    private RemoveOrganizationAction() {
    }

    public String getName() {
        return name;
    }
}
