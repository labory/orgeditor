package com.carcasser.orgeditor.client.application;

import com.carcasser.orgeditor.shared.Organization;
import com.gwtplatform.mvp.client.UiHandlers;

/**
 * Organizations ui handlers.
 */
public interface OrganizationsUiHandlers extends UiHandlers {

    void onCreate();

    void onEdit(Organization org);

    void onDelete(Organization org);

    void onDetails(Organization org);
}
