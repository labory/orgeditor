package com.carcasser.orgeditor.client.application.organization;

import com.carcasser.orgeditor.client.place.NameTokens;
import com.carcasser.orgeditor.shared.Organization;
import com.carcasser.orgeditor.shared.dispatch.GetOrganizationAction;
import com.carcasser.orgeditor.shared.dispatch.OrganizationsResult;
import com.carcasser.orgeditor.shared.dispatch.SaveOrganizationAction;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rpc.shared.DispatchAsync;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;

/**
 * Organization presenter.
 */
public class OrganizationPresenter extends Presenter<OrganizationPresenter.MyView, OrganizationPresenter.MyProxy>
        implements OrganizationUiHandlers {

    private Organization organization;

    public void createNew() {
        organization = new Organization();
    }

    public void edit(Organization organization) {
    }

    public void onSave(Organization organization) {
        GWT.log("org = " + organization);
        dispatcher.execute(new SaveOrganizationAction(organization), new AsyncCallback<OrganizationsResult>() {
            public void onFailure(Throwable caught) {
            }
            public void onSuccess(OrganizationsResult result) {
                placeManager.revealPlace(new PlaceRequest.Builder().nameToken(NameTokens.HOME).build());
            }
        });
    }

    public void onCancel() {
        placeManager.revealPlace(new PlaceRequest.Builder().nameToken(NameTokens.HOME).build());
    }

    /**
     * {@link OrganizationPresenter}'s proxy.
     */
    @ProxyStandard
    @NameToken(NameTokens.EDIT_ORGS)
    public interface MyProxy extends ProxyPlace<OrganizationPresenter> {
    }

    /**
     * {@link OrganizationPresenter}'s view.
     */
    public interface MyView extends View, HasUiHandlers<OrganizationUiHandlers> {
        void edit(Organization org);
    }

    private final PlaceManager placeManager;
    private final DispatchAsync dispatcher;

    @Inject
    OrganizationPresenter(EventBus eventBus,
                         MyView view,
                         MyProxy proxy,
                         DispatchAsync dispatcher,
                         PlaceManager placeManager) {
        super(eventBus, view, proxy, RevealType.Root);

        this.placeManager = placeManager;
        this.dispatcher = dispatcher;

        getView().setUiHandlers(this);
    }

    protected void onReset() {
        super.onReset();
    }

    public void prepareFromRequest(PlaceRequest request) {
        String orgName = request.getParameter("name", null);
        //boolean createNew = Strings.isNullOrEmpty(orgName);
        boolean createNew = orgName == null;

        if (!createNew) {
            dispatcher.execute(new GetOrganizationAction(orgName), new AsyncCallback<OrganizationsResult>() {
                public void onFailure(Throwable caught) {
                }
                public void onSuccess(OrganizationsResult result) {
                    getView().edit(result.getOrganization());
                }
            });
        } else {
            getView().edit(new Organization());
        }
    }
}