package ru.test.dao;

import ru.test.model.Word;

import java.util.List;

public interface WordDAO {

    void addWord (Word word);

    Word findByKey (String key);

    void deleteByKey (String key);

    List<Word> show();

    List<Word> searchByKeyAndTranslation (String originValue, String translation);
}
