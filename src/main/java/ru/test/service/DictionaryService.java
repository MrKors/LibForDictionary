package ru.test.service;

import ru.test.model.Dictionary;

import java.util.List;

public interface DictionaryService {

    void addDictionary (Dictionary dictionary);

    void deleteDictionary (Dictionary dictionary);

    Dictionary findById (long id);

    List<Dictionary> showDictionaries ();
}
