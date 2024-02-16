package com.javarush.dao;

import com.javarush.domain.City;
import org.hibernate.SessionFactory;

import org.hibernate.query.Query;
import java.util.List;

public class CityDAO {

    private final SessionFactory sessionFactory;

    public CityDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<City> getItems(int offset, int limit) {
        Query<City> query = sessionFactory.getCurrentSession().createQuery("select c from City c");
        query.setMaxResults(limit);
        query.setFirstResult(offset);
        return query.list();
    }

    public int getTotalCount() {
        Query<Long> query = sessionFactory.getCurrentSession().createQuery("select count(c) FROM City c");
        return Math.toIntExact(query.uniqueResult());
    }

    public List<City> getAll(){
        Query<City> query = sessionFactory.getCurrentSession().createQuery("SELECT c from City c");
        return query.list();
    }

    public City getById(Integer id) {
        Query<City> query = sessionFactory.getCurrentSession().createQuery("select c from City c join fetch c.country where c.id = :ID", City.class);
        query.setParameter("ID", id);
        return query.getSingleResult();
    }

}
