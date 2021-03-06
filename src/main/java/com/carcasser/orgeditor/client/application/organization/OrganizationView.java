package com.carcasser.orgeditor.client.application.organization;

import com.carcasser.orgeditor.client.application.organization.widget.OrganizationEditor;
import com.carcasser.orgeditor.client.resources.AppMessages;
import com.carcasser.orgeditor.shared.Organization;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.HeadingElement;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.validation.client.impl.Validation;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

/**
 * Organization view.
 */
public class OrganizationView extends ViewWithUiHandlers<OrganizationUiHandlers> implements OrganizationPresenter.MyView, Editor<Organization> {

    interface Binder extends UiBinder<Widget, OrganizationView> {
    }

    interface Driver extends SimpleBeanEditorDriver<Organization, OrganizationView> {
    }

    @UiField
    HeadingElement actionName;

    @UiField(provided = true)
    final OrganizationEditor organizationEditor;

    private final Driver driver;
    private final AppMessages messages;

    @Inject
    OrganizationView(Binder uiBinder,
                     Driver driver,
                     AppMessages messages,
                     OrganizationEditor organizationEditor,
                     EventBus eventBus) {

        this.driver = driver;
        this.organizationEditor = organizationEditor;
        this.messages = messages;

        initWidget(uiBinder.createAndBindUi(this));

        driver.initialize(this);
    }

    public void edit(Organization org) {
        actionName.setInnerText(org.getId() == null ? messages.actionCreateOrg() : messages.actionEditOrg());
        driver.edit(org);
        driver.setConstraintViolations(Collections.<ConstraintViolation<?>>emptyList());
    }

    @UiHandler("save")
    void onSaveClicked(ClickEvent event) {
        Organization organization = driver.flush();
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Organization>> constraintViolationSet = validator.validate(organization);
        driver.setConstraintViolations(new ArrayList<ConstraintViolation<?>>(constraintViolationSet));
        if (!driver.hasErrors()) {
            GWT.log("save organization ...");
            getUiHandlers().onSave(organization);
            GWT.log("saved organization");
        }
    }

    @UiHandler("close")
    void onCancelClicked(ClickEvent event) {
        getUiHandlers().onCancel();
    }
}