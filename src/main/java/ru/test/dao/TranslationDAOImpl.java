package ru.test.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.test.model.Translation;
import ru.test.model.Word;

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
        sessionFactory.getCurrentSession().save(translation);
    }

    @Override
    public void updateTranslation(Translation translation) {
        sessionFactory.getCurrentSession().update(translation);
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
    public Translation findByNameAndWord(String name, Word word) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Translation t WHERE t.translation = :name and t.word = :word");
        query.setParameter("name", name);
        query.setParameter("word", word);

        return (Translation) query.uniqueResult();
    }

    @Override
    public List<Translation> showTranslations() {
        Session session = sessionFactory.getCurrentSession();
        List<Translation> translationList = session.createQuery("FROM Translation ").list();
        return translationList;
    }
}
