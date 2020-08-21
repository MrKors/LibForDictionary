package ru.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.test.dao.DictionaryDAO;
import ru.test.model.Dictionary;

import java.util.List;

@Service
public class DictionaryServiceImpl implements DictionaryService{

    private DictionaryDAO dictionaryDAO;

    @Autowired
    public void setDictionaryDAO(DictionaryDAO dictionaryDAO) {
        this.dictionaryDAO = dictionaryDAO;
    }

    @Override
    @Transactional
    public void addDictionary(Dictionary dictionary) {
        dictionaryDAO.addDictionary(dictionary);
    }

    @Override
    @Transactional
    public void deleteDictionary(Dictionary dictionary) {
        dictionaryDAO.deleteDictionary(dictionary);
    }

    @Override
    @Transactional
    public Dictionary findById(long id) {
        return dictionaryDAO.findById(id);
    }

    @Override
    @Transactional
    public List<Dictionary> showDictionaries() {
        return dictionaryDAO.showDictionaries();
    }
}
