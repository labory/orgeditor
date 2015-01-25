package com.carcasser.orgeditor.client.application;

import com.carcasser.orgeditor.client.application.organization.OrganizationPresenter;
import com.carcasser.orgeditor.client.place.NameTokens;
import com.carcasser.orgeditor.client.place.TokenParameters;
import com.carcasser.orgeditor.shared.Organization;
import com.carcasser.orgeditor.shared.dispatch.FindAllOrganizationsAction;
import com.carcasser.orgeditor.shared.dispatch.OrganizationsResult;
import com.carcasser.orgeditor.shared.dispatch.RemoveOrganizationAction;
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
import com.gwtplatform.mvp.shared.proxy.PlaceRequest.Builder;

import java.util.List;

/**
 * Organizations presenter.
 */
public class OrganizationsPresenter extends Presenter<OrganizationsPresenter.MyView, OrganizationsPresenter.MyProxy>
        implements OrganizationsUiHandlers {

    private OrganizationPresenter organizationPresenter;

    public void onCreate() {
        placeManager.revealPlace(new Builder().nameToken(NameTokens.EDIT_ORGS).build());
    }

    public void onEdit(Organization org) {
        PlaceRequest responsePlaceRequest = new PlaceRequest.Builder()
                .nameToken(NameTokens.EDIT_ORGS)
                .with(TokenParameters.ORG_NAME, org.getName())
                .build();
        placeManager.revealPlace(responsePlaceRequest);
    }

    public void onDelete(Organization org) {
        dispatcher.execute(new RemoveOrganizationAction(org.getName()), new AsyncCallback<OrganizationsResult>() {
            public void onFailure(Throwable caught) {
            }
            public void onSuccess(OrganizationsResult result) {
                onReset();
            }
        });
    }

    public void onDetails(Organization org) {
        PlaceRequest responsePlaceRequest = new PlaceRequest.Builder()
                .nameToken(NameTokens.DETAILS_ORGS_ADDRESS)
                .with(TokenParameters.ORG_NAME, org.getName())
                .build();
        placeManager.revealPlace(responsePlaceRequest);
    }

    /**
     * {@link OrganizationsPresenter}'s proxy.
     */
    @ProxyStandard
    @NameToken(NameTokens.HOME)
    public interface MyProxy extends ProxyPlace<OrganizationsPresenter> {
    }

    /**
     * {@link OrganizationsPresenter}'s view.
     */
    public interface MyView extends View, HasUiHandlers<OrganizationsUiHandlers> {
        void displayOrganizations(List<Organization> organizations);

        void setError(String errorText);
    }

    private final PlaceManager placeManager;
    private final DispatchAsync dispatcher;

    @Inject
    OrganizationsPresenter(EventBus eventBus,
                          MyView view,
                          MyProxy proxy,
                          PlaceManager placeManager,
                          DispatchAsync dispatcher,
                          OrganizationPresenter organizationPresenter) {
        super(eventBus, view, proxy, RevealType.Root);

        this.placeManager = placeManager;
        this.dispatcher = dispatcher;
        this.organizationPresenter = organizationPresenter;

        getView().setUiHandlers(this);
    }

    protected void onReset() {
        super.onReset();
        dispatcher.execute(new FindAllOrganizationsAction(), new AsyncCallback<OrganizationsResult>() {
            public void onFailure(Throwable caught) {
            }
            public void onSuccess(OrganizationsResult result) {
                getView().displayOrganizations(result.getOrganizations());
            }
        });
    }
}