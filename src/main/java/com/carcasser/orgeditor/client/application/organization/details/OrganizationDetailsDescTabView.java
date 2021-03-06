package com.carcasser.orgeditor.client.application.organization.details;

import com.carcasser.orgeditor.shared.Organization;
import com.google.gwt.dom.client.HeadingElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;


/**
 * The view implementation for {@link com.carcasser.orgeditor.client.application.organization.details.OrganizationDetailsDescTabPresenter} .
 */
public class OrganizationDetailsDescTabView extends ViewImpl implements OrganizationDetailsDescTabPresenter.MyView {

    @UiField
    HeadingElement description;

    interface Binder extends UiBinder<Widget, OrganizationDetailsDescTabView> {
    }

    @Inject
    OrganizationDetailsDescTabView(Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
    }

    public void setOrganization(Organization organization) {
        description.setInnerText(organization.getDescription());
    }
}