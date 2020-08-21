package ru.test.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Word {

    @Id
    private String originValue;

    @OneToMany (mappedBy = "word", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<Translation> translationList;

    @ManyToOne
    @JoinColumn (name = "dictionary_id")
    private Dictionary dictionary;

    public Word() {
    }

    public Word(String originValue, List<Translation> translationList, Dictionary dictionary) {
        this.originValue = originValue;
        this.translationList = translationList;
        this.dictionary = dictionary;
    }

    public Dictionary getDictionary() {
        return dictionary;
    }

    public void setDictionary(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public String getOriginValue() {
        return originValue;
    }

    public void setOriginValue(String originValue) {
        this.originValue = originValue;
    }

    public List<Translation> getTranslationList() {
        return translationList;
    }

    public void setTranslationList(List<Translation> translation) {
        this.translationList = translation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word = (Word) o;
        return originValue.equals(word.originValue) &&
                Objects.equals(translationList, word.translationList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(originValue, translationList);
    }

    @Override
    public String toString() {
        return "Word{" +
                "originValue='" + originValue + '\'' +
                ", translation='" + translationList + '\'' +
                '}';
    }
}
