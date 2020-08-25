package ru.test.service;

import ru.test.model.Translation;
import ru.test.model.Word;

import java.util.List;

public interface TranslationService {

    void addTranslation (Translation translation);

    void updateTranslation (Translation translation);

    void deleteTranslation(Translation translation);

    Translation findById (long id);

    Translation findByNameAndWord (String name, Word word);

    List<Translation> showTranslations ();
}
