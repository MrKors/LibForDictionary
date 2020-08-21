package ru.test.model;

import java.util.HashMap;
import java.util.Map;

//@Entity
public class Criteria {

    private final Map<String,String> CRITERIA_MAP = new HashMap<>();

    {
        CRITERIA_MAP.put("alphabetic", "[a-zA-Z]+");
        CRITERIA_MAP.put("numeric", "\\d+");
    }

    public Map<String, String> getCriteriaMap() {
        return CRITERIA_MAP;
    }

    //    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;
//
//    @Column
//    private String consistence;
//
//    @Column
//    private int length;
//
//    public Criteria() {
//    }
//
//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//
//    public String getConsistence() {
//        return consistence;
//    }
//
//    public void setConsistence(String consistence) {
//        this.consistence = consistence;
//    }
//
//    public int getLength() {
//        return length;
//    }
//
//    public void setLength(int length) {
//        this.length = length;
//    }
}
