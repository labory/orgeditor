package com.carcasser.orgeditor.client.application;

import com.carcasser.orgeditor.client.application.organization.OrganizationPresenter;
import com.carcasser.orgeditor.client.application.organization.OrganizationView;
import com.carcasser.orgeditor.client.application.organization.details.OrganizationDetailsAddressTabPresenter;
import com.carcasser.orgeditor.client.application.organization.details.OrganizationDetailsAddressTabView;
import com.carcasser.orgeditor.client.application.organization.details.OrganizationDetailsDescTabPresenter;
import com.carcasser.orgeditor.client.application.organization.details.OrganizationDetailsDescTabView;
import com.carcasser.orgeditor.client.application.organization.details.OrganizationDetailsPresenter;
import com.carcasser.orgeditor.client.application.organization.details.OrganizationDetailsView;
import com.carcasser.orgeditor.client.application.organization.details.tabs.DetailsTabPanel;
import com.carcasser.orgeditor.client.application.organization.details.tabs.TabFactory;
import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.google.inject.Singleton;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class ApplicationModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        bindPresenter(OrganizationsPresenter.class, OrganizationsPresenter.MyView.class, OrganizationsView.class,
                OrganizationsPresenter.MyProxy.class);
        bindPresenter(OrganizationPresenter.class, OrganizationPresenter.MyView.class, OrganizationView.class,
                OrganizationPresenter.MyProxy.class);
        bindPresenter(OrganizationDetailsPresenter.class, OrganizationDetailsPresenter.MyView.class, OrganizationDetailsView.class,
                OrganizationDetailsPresenter.MyProxy.class);

        bindPresenter(OrganizationDetailsAddressTabPresenter.class, OrganizationDetailsAddressTabPresenter.MyView.class, OrganizationDetailsAddressTabView.class,
                OrganizationDetailsAddressTabPresenter.MyProxy.class);

        bindPresenter(OrganizationDetailsDescTabPresenter.class, OrganizationDetailsDescTabPresenter.MyView.class, OrganizationDetailsDescTabView.class,
                OrganizationDetailsDescTabPresenter.MyProxy.class);

        // Singleton binders
        bind(DetailsTabPanel.Binder.class).in(Singleton.class);
        install(new GinFactoryModuleBuilder().build(TabFactory.class));
    }
}
