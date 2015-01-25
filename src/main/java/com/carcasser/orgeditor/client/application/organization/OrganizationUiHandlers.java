package com.carcasser.orgeditor.client.application.organization;

import com.carcasser.orgeditor.shared.Organization;
import com.gwtplatform.mvp.client.UiHandlers;

/**
 * Organization ui handlers.
 */
public interface OrganizationUiHandlers extends UiHandlers {

    void createNew();

    void edit(Organization organization);

    void onSave(Organization organization);

    void onCancel();

}
