package ru.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.test.dao.WordDAO;
import ru.test.model.Word;

import java.util.List;

@Service
public class WordServiceImpl implements WordService{
    private WordDAO wordDAO;

    @Autowired
    public void setWordDAO(WordDAO wordDAO) {
        this.wordDAO = wordDAO;
    }

    @Override
    @Transactional
    public void addWord(Word word) {
        wordDAO.addWord(word);
    }

    @Override
    @Transactional
    public Word findByKey(String key) {
        return wordDAO.findByKey(key);
    }

    @Override
    @Transactional
    public void deleteByKey(String key) {
        wordDAO.deleteByKey(key);
    }

    @Override
    @Transactional
    public List<Word> show() {
        return wordDAO.show();
    }

    @Override
    @Transactional
    public List<Word> searchByKeyAndTranslation(String originValue, String translation) {
        return wordDAO.searchByKeyAndTranslation(originValue, translation);
    }
}
