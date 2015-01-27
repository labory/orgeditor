package com.carcasser.orgeditor.client.application.organization.details.tabs;

import com.carcasser.orgeditor.client.place.TokenParameters;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.LIElement;
import com.google.gwt.dom.client.UListElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.Tab;
import com.gwtplatform.mvp.client.TabData;
import com.gwtplatform.mvp.client.TabPanel;

import java.util.ArrayList;
import java.util.List;

/**
 * Details tab panel.
 */
public class DetailsTabPanel extends Composite implements TabPanel {

    public interface Binder extends UiBinder<Widget, DetailsTabPanel> {
    }

    private final TabFactory tabFactory;

    @Inject
    DetailsTabPanel(Binder binder,
                    TabFactory tabFactory) {
        this.tabFactory = tabFactory;

        initWidget(binder.createAndBindUi(this));
    }

    Tab currentActiveTab;

    @UiField
    public FlowPanel tabContentContainer;
    @UiField
    public UListElement tabPanel;

    private final List<DetailsTab> tabList = new ArrayList<DetailsTab>();

    @Override
    public Tab addTab(TabData tabData, String historyToken) {
        GWT.log("tab data added = " + tabData);
        DetailsTabData detailsTabData = (DetailsTabData) tabData;
        DetailsTab newTab = createNewTab(detailsTabData);
        GWT.log("tab added = " + newTab);
        int beforeIndex;
        for (beforeIndex = 0; beforeIndex < tabList.size(); ++beforeIndex) {
            if (newTab.getPriority() < tabList.get(beforeIndex).getPriority()) {
                break;
            }
        }
        final LIElement li = Document.get().createLIElement();
        li.appendChild(newTab.asWidget().getElement());
        newTab.setLiElement(li);

        tabPanel.insertBefore(li, tabPanel.getChild(beforeIndex));
        tabList.add(beforeIndex, newTab);
        newTab.setTargetHistoryToken(historyToken);
        setTabVisibility(newTab);
        return newTab;
    }

    @Override
    public void removeTab(Tab tab) {
        tabPanel.removeChild(tab.asWidget().getElement());
        tabList.remove(tab);
    }

    @Override
    public void removeTabs() {
        for (Tab tab : tabList) {
            tabPanel.removeChild(tab.asWidget().getElement());
        }
        tabList.clear();
    }

    @Override
    public void setActiveTab(Tab tab) {
        if (currentActiveTab != null) {
            currentActiveTab.deactivate();
        }
        if (tab != null) {
            tab.activate();
        }
        currentActiveTab = tab;
    }

    @Override
    public void changeTab(Tab tab, TabData tabData, String historyToken) {
        tab.setText(tabData.getLabel());
        tab.setTargetHistoryToken(historyToken);
    }

    public void setPanelContent(IsWidget panelContent) {
        tabContentContainer.clear();
        if (panelContent != null) {
            tabContentContainer.add(panelContent);
        }
    }

    public void refreshTabs() {
        for (DetailsTab tab : tabList) {
            setTabVisibility(tab);
        }
    }

    public void setHistoryToken(String token) {
        for (DetailsTab tab : tabList) {
            tab.setTargetHistoryToken(tab.getToken() + ";" + TokenParameters.ORG_NAME + "=" + token);
        }
    }

    private void setTabVisibility(DetailsTab tab) {
        boolean visible = (tab == currentActiveTab) || tab.canUserAccess();
        tab.setVisible(visible);
    }

    protected DetailsTab createNewTab(DetailsTabData tabData) {
        return tabFactory.createDetailTab(tabData);
    }
}
