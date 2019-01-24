package dao.impl;

import dao.BaseDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.List;

@Repository
public class BaseDaoImpl implements BaseDao {

    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }
    @Override
    public void save(Object bean) {
        getSession().save(bean);
    }

    @Override
    public void update(Object bean) {
        getSession().update(bean);
    }

    @Override
    public Object load(Class c, Serializable id) {
        return getSession().get(c, id);
    }

    @Override
    public <T> List<T> getAllList(Class<T> c) {
        Session session = getSession();
        CriteriaQuery<T> criteriaQuery = session.getCriteriaBuilder().createQuery(c);
        criteriaQuery.from(c);
        List<T> list = session.createQuery(criteriaQuery).getResultList();
        return list;
    }
}
