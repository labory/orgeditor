package com.carcasser.orgeditor.shared.dispatch;

import com.carcasser.orgeditor.shared.Organization;
import com.gwtplatform.dispatch.rpc.shared.Result;

import java.util.ArrayList;
import java.util.List;

/**
 * The result of a {@link com.carcasser.orgeditor.shared.dispatch.FindAllOrganizationsAction} action.
 */
public class OrganizationsResult implements Result {
    private ArrayList<Organization> organizations;

    public OrganizationsResult(List<Organization> organizations) {
        this.organizations = new ArrayList<Organization>(organizations);
    }

    /**
     * For serialization only.
     */
    @SuppressWarnings("unused")
    private OrganizationsResult() {
    }

    public List<Organization> getOrganizations() {
        return organizations;
    }

    public Organization getOrganization() {
        return organizations.isEmpty() ? null : organizations.iterator().next();
    }
}
