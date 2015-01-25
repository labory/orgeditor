package com.carcasser.orgeditor.client.application.organization.details;

import com.carcasser.orgeditor.client.application.organization.details.tabs.DetailsTabPanel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Tab;
import com.gwtplatform.mvp.client.TabData;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;

/**
 * Organization details view.
 */
public class OrganizationDetailsView extends ViewWithUiHandlers<OrganizationDetailsUiHandlers> implements OrganizationDetailsPresenter.MyView {

    interface Binder extends UiBinder<Widget, OrganizationDetailsView> {
    }

    @UiField(provided = true)
    DetailsTabPanel tabPanel;

    @Inject
    OrganizationDetailsView(Binder uiBinder,
                     DetailsTabPanel tabPanel,
                     EventBus eventBus) {

        this.tabPanel = tabPanel;
        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public Tab addTab(TabData tabData, String historyToken) {
        return tabPanel.addTab(tabData, historyToken);
    }

    @Override
    public void removeTab(Tab tab) {
        tabPanel.removeTab(tab);
    }

    @Override
    public void removeTabs() {
        tabPanel.removeTabs();
    }

    @Override
    public void setActiveTab(Tab tab) {
        tabPanel.setActiveTab(tab);
    }

    @Override
    public void changeTab(Tab tab, TabData tabData, String historyToken) {
        tabPanel.changeTab(tab, tabData, historyToken);
    }

    public void setHistoryToken(String token) {
        tabPanel.setHistoryToken(token);
    }

    @Override
    public void setInSlot(Object slot, IsWidget content) {
        if (slot == OrganizationDetailsPresenter.SLOT_SET_TAB_CONTENT) {
            tabPanel.setPanelContent(content);
        } else {
            super.setInSlot(slot, content);
        }
    }

    @UiHandler("back")
    void onBackClicked(ClickEvent event) {
        getUiHandlers().onBack();
    }
}