package com.carcasser.orgeditor.server.service;

import com.carcasser.orgeditor.server.dao.OrganizationDao;
import com.carcasser.orgeditor.shared.Organization;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * Organization service implementation.
 */
@Transactional
@Service
public class OrganizationServiceImpl implements OrganizationService {

    @Inject
    private OrganizationDao organizationDao;

    public List<Organization> findAll() {
        return organizationDao.findAll();
    }

    public Organization findByName(String name) {
        return organizationDao.findByName(name);
    }

    public void save(Organization org) {
        organizationDao.save(org);
    }

    public void removeByName(String name) {
        organizationDao.removeByName(name);
    }
}
