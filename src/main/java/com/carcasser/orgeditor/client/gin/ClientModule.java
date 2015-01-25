package com.carcasser.orgeditor.client.gin;

import com.gwtplatform.dispatch.rpc.client.gin.RpcDispatchAsyncModule;
import com.gwtplatform.mvp.client.annotations.DefaultPlace;
import com.gwtplatform.mvp.client.annotations.ErrorPlace;
import com.gwtplatform.mvp.client.annotations.GaAccount;
import com.gwtplatform.mvp.client.annotations.UnauthorizedPlace;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.gin.DefaultModule;
import com.gwtplatform.mvp.client.proxy.DefaultPlaceManager;
import com.carcasser.orgeditor.client.application.ApplicationModule;
import com.carcasser.orgeditor.client.place.NameTokens;

public class ClientModule extends AbstractPresenterModule {
    private static final String ANALYTICS_ACCOUNT = "UA-8319339-6";

    @Override
    protected void configure() {
        install(new DefaultModule(DefaultPlaceManager.class));
        install(new RpcDispatchAsyncModule());
        install(new ApplicationModule());

        bindConstant().annotatedWith(DefaultPlace.class).to(NameTokens.HOME);
        bindConstant().annotatedWith(ErrorPlace.class).to(NameTokens.HOME);
        bindConstant().annotatedWith(UnauthorizedPlace.class).to(NameTokens.HOME);

        bindConstant().annotatedWith(GaAccount.class).to(ANALYTICS_ACCOUNT);
    }
}
