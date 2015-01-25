package com.carcasser.orgeditor.server.dao;

import com.carcasser.orgeditor.shared.Organization;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Organization DAO.
 */
@Repository
public class OrganizationDaoImpl extends HibernateDaoSupport implements OrganizationDao {

    @Autowired
    public void anyMethodName(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

    @SuppressWarnings("unchecked")
    public List<Organization> findAll() {
        return getSessionFactory().getCurrentSession().createQuery("from Organization").list();
    }

    public Organization findByName(String name) {
        return (Organization) getSessionFactory().getCurrentSession()
                .createQuery("from Organization org where org.name = ?").setString(0, name).uniqueResult();
    }

    public void save(Organization org) {
        getSessionFactory().getCurrentSession().saveOrUpdate(org);
    }

    public void removeByName(String name) {
        getSessionFactory().getCurrentSession().createQuery("delete from Organization org where org.name = ?").setString(0, name).executeUpdate();
    }
}