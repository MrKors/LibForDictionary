package ru.test.model;

import javax.persistence.*;

@Entity
public class Translation {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;

    private String translation;

    @ManyToOne
    @JoinColumn (name = "word_originValue")
    private Word word;

    public Translation() {
    }

    public Translation(String translation, Word word) {
        this.translation = translation;
        this.word = word;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public Word getWord() {
        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }
}
