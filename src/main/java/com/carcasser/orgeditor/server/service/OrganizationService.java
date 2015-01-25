package com.carcasser.orgeditor.server.service;

import com.carcasser.orgeditor.shared.Organization;

import java.util.List;

/**
 * Organization service interface.
 */
public interface OrganizationService {
    List<Organization> findAll();

    Organization findByName(String name);

    void save(Organization org);

    void removeByName(String name);
}
