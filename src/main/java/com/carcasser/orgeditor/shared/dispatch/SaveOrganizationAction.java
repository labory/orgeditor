package com.carcasser.orgeditor.shared.dispatch;

import com.carcasser.orgeditor.shared.Organization;
import com.gwtplatform.dispatch.rpc.shared.UnsecuredActionImpl;

/**
 * Save organization..
 */
public class SaveOrganizationAction extends UnsecuredActionImpl<OrganizationsResult> {

    private Organization organization;

    public SaveOrganizationAction(Organization organization) {
        this.organization = organization;
    }

    private SaveOrganizationAction() {
    }

    public Organization getOrganization() {
        return organization;
    }
}
