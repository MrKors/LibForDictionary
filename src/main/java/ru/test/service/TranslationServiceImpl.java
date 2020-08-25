package ru.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.test.dao.TranslationDAO;
import ru.test.model.Translation;
import ru.test.model.Word;

import java.util.List;
@Service
public class TranslationServiceImpl implements TranslationService{

    private TranslationDAO translationDAO;
    @Autowired
    public void setTranslationDAO(TranslationDAO translationDAO) {
        this.translationDAO = translationDAO;
    }

    @Override
    @Transactional
    public void addTranslation(Translation translation) {
        translationDAO.addTranslation(translation);
    }

    @Override
    @Transactional
    public void updateTranslation(Translation translation) {
        translationDAO.updateTranslation(translation);
    }

    @Override
    @Transactional
    public void deleteTranslation(Translation translation) {
        translationDAO.deleteTranslation(translation);
    }

    @Override
    @Transactional
    public Translation findById(long id) {
        return translationDAO.findById(id);
    }

    @Override
    @Transactional
    public Translation findByNameAndWord(String name, Word word) {
        return translationDAO.findByNameAndWord(name, word);
    }

    @Override
    @Transactional
    public List<Translation> showTranslations() {
        return translationDAO.showTranslations();
    }
}
