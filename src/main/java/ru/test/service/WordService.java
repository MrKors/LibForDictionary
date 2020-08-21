package ru.test.service;

import ru.test.model.Word;

import java.util.List;

public interface WordService {

    void addWord (Word word);

    Word findByKey (String key);

    void deleteByKey (String key);

    List<Word> show();
}
