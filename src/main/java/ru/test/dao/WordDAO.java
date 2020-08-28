package ru.test.dao;

import ru.test.model.Word;

import java.util.List;

public interface WordDAO {

    void addWord (Word word);

    Word findByKey (String key);

    void deleteByKey (String key);

    List<Word> show();

    List<Word> searchByKeyOrTranslation(String originValue);

    List<Word> searchByKeyOrTranslationAndDictionaryID(String originValue, long id);

    List<Word> showWordsListByDictionary (long id);
}
