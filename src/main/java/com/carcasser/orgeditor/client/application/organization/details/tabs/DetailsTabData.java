package com.carcasser.orgeditor.client.application.organization.details.tabs;

import com.gwtplatform.mvp.client.TabDataBasic;

/**
 * Details tab data.
 */
public class DetailsTabData extends TabDataBasic {

    private String token;

    public DetailsTabData(String label, float priority, String token) {
        super(label, priority);
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
