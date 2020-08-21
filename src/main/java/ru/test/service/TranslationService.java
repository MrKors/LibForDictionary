package ru.test.service;

import ru.test.model.Translation;

import java.util.List;

public interface TranslationService {

    void addTranslation (Translation translation);

    void deleteTranslation(Translation translation);

    Translation findById (long id);

    List<Translation> showTranslations ();
}
