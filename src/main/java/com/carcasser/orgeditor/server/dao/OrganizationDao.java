package com.carcasser.orgeditor.server.dao;

import com.carcasser.orgeditor.shared.Organization;

import java.util.List;

/**
 * Organization DAO interface.
 */
public interface OrganizationDao {

    List<Organization> findAll();

    Organization findByName(String name);

    void save(Organization org);

    void removeByName(String name);
}
