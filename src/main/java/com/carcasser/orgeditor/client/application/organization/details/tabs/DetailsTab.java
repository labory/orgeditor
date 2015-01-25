package com.carcasser.orgeditor.client.application.organization.details.tabs;

import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.gwtplatform.mvp.client.Tab;

/**
 * Details tab.
 */
public class DetailsTab extends Composite implements Tab {

    interface Binder extends UiBinder<Widget, DetailsTab> {
    }

    protected interface Style extends CssResource {
        String active();

        String inactive();
    }

    @Inject
    DetailsTab(Binder uiBinder, @Assisted DetailsTabData tabData) {
        super();

        this.priority = tabData.getPriority();
        this.token = tabData.getToken();

        initWidget(uiBinder.createAndBindUi(this));

        setText(tabData.getLabel());
    }

    @UiField
    Hyperlink hyperlink;
    @UiField
    Style style;

    private final float priority;
    private final String token;

    @Override
    public void activate() {
        removeStyleName(style.inactive());
        addStyleName(style.active());
    }

    @Override
    public Widget asWidget() {
        return this;
    }

    @Override
    public void deactivate() {
        removeStyleName(style.active());
        addStyleName(style.inactive());
    }

    @Override
    public float getPriority() {
        return priority;
    }

    public String getToken() {
        return token;
    }

    @Override
    public String getText() {
        return hyperlink.getText();
    }

    @Override
    public void setTargetHistoryToken(String historyToken) {
        hyperlink.setTargetHistoryToken(historyToken);
    }

    @Override
    public void setText(String text) {
        hyperlink.setText(text);
    }

    public boolean canUserAccess() {
        return true;
    }
}
