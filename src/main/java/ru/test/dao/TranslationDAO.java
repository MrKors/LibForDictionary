package ru.test.dao;

import ru.test.model.Translation;

import java.util.List;

public interface TranslationDAO {

    void addTranslation (Translation translation);

    void deleteTranslation(Translation translation);

    Translation findById (long id);

    List<Translation> showTranslations ();
}
