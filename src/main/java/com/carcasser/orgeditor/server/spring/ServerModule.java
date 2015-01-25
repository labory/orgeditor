package com.carcasser.orgeditor.server.spring;

import com.carcasser.orgeditor.server.dispatch.FindAllOrganizationsHandler;
import com.carcasser.orgeditor.server.dispatch.GetOrganizationHandler;
import com.carcasser.orgeditor.server.dispatch.RemoveOrganizationHandler;
import com.carcasser.orgeditor.server.dispatch.SaveOrganizationHandler;
import com.carcasser.orgeditor.shared.dispatch.FindAllOrganizationsAction;
import com.carcasser.orgeditor.shared.dispatch.GetOrganizationAction;
import com.carcasser.orgeditor.shared.dispatch.RemoveOrganizationAction;
import com.carcasser.orgeditor.shared.dispatch.SaveOrganizationAction;
import com.gwtplatform.dispatch.rpc.server.actionvalidator.ActionValidator;
import com.gwtplatform.dispatch.rpc.server.spring.HandlerModule;
import com.gwtplatform.dispatch.rpc.server.spring.LoggerFactoryBean;
import com.gwtplatform.dispatch.rpc.server.spring.actionvalidator.DefaultActionValidator;
import com.gwtplatform.dispatch.rpc.server.spring.configuration.DefaultModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Module which binds the handlers and configurations.
 */
@Configuration
@Import(DefaultModule.class)
@ComponentScan(basePackages = "com.gwtplatform.dispatch.rpc.server.spring")
@ImportResource("classpath*:META-INF/spring/applicationContext.xml")
public class ServerModule extends HandlerModule {
    public ServerModule() {
    }

    @Bean
    public FindAllOrganizationsHandler getFindAllOrganizationsHandler() {
        return new FindAllOrganizationsHandler();
    }

    @Bean
    public GetOrganizationHandler getOrganizationHandler() {
        return new GetOrganizationHandler();
    }

    @Bean
    public SaveOrganizationHandler getSaveOrganizationHandler() {
        return new SaveOrganizationHandler();
    }

    @Bean
    public RemoveOrganizationHandler getRemoveOrganizationHandler() {
        return new RemoveOrganizationHandler();
    }

    @Bean
    public ActionValidator getDefaultActionValidator() {
        return new DefaultActionValidator();
    }

    @Bean
    public LoggerFactoryBean getLogger() {
        Logger logger = Logger.getAnonymousLogger();
        logger.setLevel(Level.FINEST);
        return new LoggerFactoryBean(logger);
    }

    protected void configureHandlers() {
        bindHandler(FindAllOrganizationsAction.class, FindAllOrganizationsHandler.class);
        bindHandler(GetOrganizationAction.class, GetOrganizationHandler.class);
        bindHandler(SaveOrganizationAction.class, SaveOrganizationHandler.class);
        bindHandler(RemoveOrganizationAction.class, RemoveOrganizationHandler.class);
    }
}
