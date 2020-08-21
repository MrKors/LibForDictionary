package ru.test.dao;

import ru.test.model.Dictionary;

import java.util.List;

public interface DictionaryDAO {

    void addDictionary (Dictionary dictionary);

    void deleteDictionary (Dictionary dictionary);

    Dictionary findById (long id);

    List<Dictionary> showDictionaries ();
}
