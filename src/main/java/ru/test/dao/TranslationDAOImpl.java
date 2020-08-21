package ru.test.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.test.model.Translation;

import java.util.List;

@Repository
public class TranslationDAOImpl implements TranslationDAO{

    private SessionFactory sessionFactory;
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addTranslation(Translation translation) {
        sessionFactory.getCurrentSession().saveOrUpdate(translation);
    }

    @Override
    public void deleteTranslation(Translation translation) {
        sessionFactory.getCurrentSession().delete(translation);
    }

    @Override
    public Translation findById(long id) {
        return sessionFactory.getCurrentSession().get(Translation.class, id);
    }

    @Override
    public List<Translation> showTranslations() {
        Session session = sessionFactory.getCurrentSession();
        List<Translation> translationList = session.createQuery("FROM Translation ").list();
        return translationList;
    }
}
