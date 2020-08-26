package ru.test.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.test.model.Word;

import java.util.List;

@Repository
public class WordDAOImpl implements WordDAO{
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addWord(Word word) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(word);
    }

    @Override
    public Word findByKey(String key) {
        Query query = sessionFactory.getCurrentSession().createQuery("FROM Word WHERE originValue= :key");
        query.setParameter("key", key);
        return (Word) query.uniqueResult();
    }

    @Override
    public void deleteByKey(String key) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Word WHERE originValue= :key");
        query.setParameter("key", key);
        Word word = (Word) query.uniqueResult();
        if (word!=null){
            session.delete(word);
        }
    }

    @Override
    public List<Word> show() {
        Session session = sessionFactory.getCurrentSession();
        List<Word> wordList = session.createQuery("FROM Word").list();
        return wordList;
    }

    @Override
    public List<Word> searchByKeyAndTranslation(String originValue, String translation) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Word WHERE originValue like :originValue");
//        or t.translation like :translation%
        query.setParameter("originValue", "%" + originValue + "%");
        System.out.println(query.list());
//        query.setParameter("translation", translation);
        return query.list();
    }
}
