package com.carcasser.orgeditor.client.application.organization.widget;

import com.carcasser.orgeditor.shared.Organization;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

/**
 * Organization editor.
 */
public class OrganizationEditor extends Composite implements Editor<Organization> {
    interface Binder extends UiBinder<Widget, OrganizationEditor> {
    }

    @UiField
    TextBox name;

    @UiField
    TextBox address;

    @UiField
    TextBox description;

    @Inject
    OrganizationEditor(Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
    }
}
