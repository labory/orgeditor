package com.carcasser.orgeditor.client.application.organization.details;

import com.carcasser.orgeditor.client.application.organization.details.tabs.DetailsTabData;
import com.carcasser.orgeditor.client.place.NameTokens;
import com.carcasser.orgeditor.client.resources.AppMessages;
import com.carcasser.orgeditor.shared.Organization;
import com.carcasser.orgeditor.shared.dispatch.OrganizationsResult;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.TabData;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.TabInfo;
import com.gwtplatform.mvp.client.proxy.TabContentProxyPlace;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;

/**
 * OrganizationDetailsAddressTabPresenter.
 */
public class OrganizationDetailsAddressTabPresenter extends Presenter<OrganizationDetailsAddressTabPresenter.MyView, OrganizationDetailsAddressTabPresenter.MyProxy> {
    /**
     * {@link OrganizationDetailsPresenter}'s proxy.
     */
    @ProxyCodeSplit
    @NameToken(NameTokens.DETAILS_ORGS_ADDRESS)
    public interface MyProxy extends TabContentProxyPlace<OrganizationDetailsAddressTabPresenter> {
    }

    @TabInfo(container = OrganizationDetailsPresenter.class)
    static TabData getTabData() {
        AppMessages messages = GWT.create(AppMessages.class);
        return new DetailsTabData(messages.detailsAddress(), 0, NameTokens.DETAILS_ORGS_ADDRESS);
    }

    /**
     * {@link OrganizationDetailsPresenter}'s view.
     */
    public interface MyView extends View {
        void setOrganization(Organization organization);
    }

    private OrganizationDetailsPresenter parent;

    @Inject
    OrganizationDetailsAddressTabPresenter(EventBus eventBus,
                                        MyView view,
                                        MyProxy proxy,
                                        OrganizationDetailsPresenter parent) {
        super(eventBus, view, proxy, OrganizationDetailsPresenter.SLOT_SET_TAB_CONTENT);
        this.parent = parent;
    }

    public void prepareFromRequest(PlaceRequest request) {
        parent.prepareFromRequest(request, new AsyncCallback<OrganizationsResult>() {
            public void onFailure(Throwable caught) {
            }
            public void onSuccess(OrganizationsResult result) {
                getView().setOrganization(result.getOrganization());
            }
        });
    }
}
