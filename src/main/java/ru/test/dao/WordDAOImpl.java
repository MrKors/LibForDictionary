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
        session.update(word);
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
        return (List<Word>) session.createQuery("FROM Word").list();
    }

    @Override
    public List<Word> searchByKeyOrTranslation(String originValue) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(
                "SELECT distinct t.word " +
                        "FROM Translation t " +
                        "WHERE t.word.originValue like :originValue or t.translation like :originValue"
        );
        query.setParameter("originValue", "%" + originValue + "%");
        return query.list();
    }

    @Override
    public List<Word> searchByKeyOrTranslationAndDictionaryID(String originValue, long id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(
                "SELECT distinct t.word " +
                        "FROM Translation t " +
                        "WHERE (t.word.originValue like :originValue or t.translation like :originValue) and t.word.dictionary.id =:id"
        );
        query.setParameter("originValue", "%" + originValue + "%");
        query.setParameter("id", id);
        System.out.println(query.list());
        return query.list();
    }

    @Override
    public List<Word> showWordsListByDictionary(long id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Word WHERE dictionary.id = :id");
        query.setParameter("id", id);
        return query.list();
    }
}
