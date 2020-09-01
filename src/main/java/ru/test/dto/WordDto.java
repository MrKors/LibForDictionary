package ru.test.dto;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import ru.test.model.Word;

import javax.validation.constraints.NotNull;

public class WordDto {

    @NotEmpty(message = "Origin value mustn't be empty")
    private String originValue;

    @NotBlank(message = "Translation mustn't be empty")
    private String translation;

    @NotNull(message = "Select one dictionary")
    private Long dictionary;

    private Word word;

    public WordDto() {
    }

    public WordDto(Word word) {
        this.word = word;
    }

    public WordDto(String originValue, String translation, Long dictionary) {
        this.originValue = originValue;
        this.translation = translation;
        this.dictionary = dictionary;
    }

    public Word getWord() {
        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }

    public String getOriginValue() {
        return originValue;
    }

    public void setOriginValue(String originValue) {
        this.originValue = originValue;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public Long getDictionary() {
        return dictionary;
    }

    public void setDictionary(Long dictionary) {
        this.dictionary = dictionary;
    }
}
