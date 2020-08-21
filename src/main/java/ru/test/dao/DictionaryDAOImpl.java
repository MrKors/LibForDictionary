package ru.test.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.test.model.Dictionary;

import java.util.List;

@Repository
public class DictionaryDAOImpl implements DictionaryDAO{
    private SessionFactory sessionFactory;
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addDictionary(Dictionary dictionary) {
        sessionFactory.getCurrentSession().saveOrUpdate(dictionary);
    }

    @Override
    public void deleteDictionary(Dictionary dictionary) {
        sessionFactory.getCurrentSession().delete(dictionary);
    }

    @Override
    public Dictionary findById(long id) {
        return sessionFactory.getCurrentSession().get(Dictionary.class, id);
    }

    @Override
    public List<Dictionary> showDictionaries() {
        Session session = sessionFactory.getCurrentSession();
        List<Dictionary> dictionaryList = session.createQuery("FROM Dictionary").list();
        return dictionaryList;
    }
}
