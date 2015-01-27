package com.carcasser.orgeditor.client.validation;

import com.carcasser.orgeditor.shared.Organization;
import com.google.gwt.core.client.GWT;
import com.google.gwt.validation.client.AbstractGwtValidatorFactory;
import com.google.gwt.validation.client.GwtValidation;
import com.google.gwt.validation.client.impl.AbstractGwtValidator;

import javax.validation.Validator;

/**
 * Validation factory.
 */
public class AppValidatorFactory extends AbstractGwtValidatorFactory {

    @GwtValidation(Organization.class)
    public interface GwtValidator extends Validator {
    }

    public AbstractGwtValidator createValidator() {
        return GWT.create(GwtValidator.class);
    }
}
