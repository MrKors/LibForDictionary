package ru.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.test.dao.TranslationDAO;
import ru.test.model.Translation;

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
    public List<Translation> showTranslations() {
        return translationDAO.showTranslations();
    }
}
