package ru.test.model;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.List;

@Entity
public class Dictionary {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank (message = "Field 'Dictionary name' must not be empty")
    private String name;

    @NotEmpty (message = "Select dictionary type")
    private String consistenceCriteria;

    @Min(value = 2, message = "Value does not less than 2")
    private int lengthCriteria;

    @OneToMany (mappedBy = "dictionary", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Word> words;

    public Dictionary() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Word> getWords() {
        return words;
    }

    public void setWords(List<Word> words) {
        this.words = words;
    }

    public String getConsistenceCriteria() {
        return consistenceCriteria;
    }

    public void setConsistenceCriteria(String consistenceCriteria) {
        this.consistenceCriteria = consistenceCriteria;
    }

    public int getLengthCriteria() {
        return lengthCriteria;
    }

    public void setLengthCriteria(int lengthCriteria) {
        this.lengthCriteria = lengthCriteria;
    }

//    @Override
//    public String toString() {
//        return name;
//    }
}
