package com.carcasser.orgeditor.client.application.organization.details;

import com.carcasser.orgeditor.client.place.NameTokens;
import com.carcasser.orgeditor.client.place.TokenParameters;
import com.carcasser.orgeditor.shared.Organization;
import com.carcasser.orgeditor.shared.dispatch.GetOrganizationAction;
import com.carcasser.orgeditor.shared.dispatch.OrganizationsResult;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rpc.shared.DispatchAsync;
import com.gwtplatform.mvp.client.ChangeTabHandler;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.RequestTabsHandler;
import com.gwtplatform.mvp.client.TabContainerPresenter;
import com.gwtplatform.mvp.client.TabView;
import com.gwtplatform.mvp.client.annotations.ChangeTab;
import com.gwtplatform.mvp.client.annotations.ContentSlot;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.annotations.RequestTabs;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;

/**
 * Organization details presenter.
 */
public class OrganizationDetailsPresenter extends TabContainerPresenter<OrganizationDetailsPresenter.MyView, OrganizationDetailsPresenter.MyProxy>
        implements OrganizationDetailsUiHandlers {

    /**
     * This will be the event sent to our "unknown" child presenters, in order for them to register their tabs.
     */
    @RequestTabs
    public static final Type<RequestTabsHandler> SLOT_REQUEST_TABS = new Type<>();

    /**
     * Fired by child proxie's when their tab content is changed.
     */
    @ChangeTab
    public static final Type<ChangeTabHandler> SLOT_CHANGE_TAB = new Type<>();

    /**
     * Use this in leaf presenters, inside their {@link #revealInParent} method.
     */
    @ContentSlot
    public static final Type<RevealContentHandler<?>> SLOT_SET_TAB_CONTENT = new Type<>();

    @Override
    public void onBack() {
        GWT.log("backing");
        placeManager.revealPlace(new PlaceRequest.Builder().nameToken(NameTokens.HOME).build());
    }

    /**
     * {@link OrganizationDetailsPresenter}'s proxy.
     */
    @ProxyStandard
    @NameToken(NameTokens.DETAILS_ORGS)
    public interface MyProxy extends ProxyPlace<OrganizationDetailsPresenter> {
    }

    /**
     * {@link OrganizationDetailsPresenter}'s view.
     */
    public interface MyView extends TabView, HasUiHandlers<OrganizationDetailsUiHandlers> {
        void setHistoryToken(String token);
    }

    private final PlaceManager placeManager;
    private final DispatchAsync dispatcher;

    private Organization organization;

    @Inject
    OrganizationDetailsPresenter(EventBus eventBus,
                                 MyView view,
                                 MyProxy proxy,
                                 DispatchAsync dispatcher,
                                 PlaceManager placeManager) {
        super(eventBus, view, proxy, SLOT_SET_TAB_CONTENT, SLOT_REQUEST_TABS, SLOT_CHANGE_TAB, RevealType.Root);

        this.placeManager = placeManager;
        this.dispatcher = dispatcher;

        getView().setUiHandlers(this);
    }

    public void prepareFromRequest(PlaceRequest request, AsyncCallback <OrganizationsResult> callback) {
        String orgName = request.getParameter(TokenParameters.ORG_NAME, null);
        if (orgName != null) {
            dispatcher.execute(new GetOrganizationAction(orgName), callback);
            getView().setHistoryToken(orgName);
        }
    }

    public Organization getOrganization() {
        return organization;
    }
}