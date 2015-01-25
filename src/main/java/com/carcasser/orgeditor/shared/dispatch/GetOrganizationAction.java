package com.carcasser.orgeditor.shared.dispatch;

import com.gwtplatform.dispatch.rpc.shared.UnsecuredActionImpl;

/**
 * Get organization.
 */
public class GetOrganizationAction extends UnsecuredActionImpl<OrganizationsResult> {

    private String name;

    public GetOrganizationAction(String name) {
        this.name = name;
    }

    private GetOrganizationAction() {
    }

    public String getName() {
        return name;
    }
}
