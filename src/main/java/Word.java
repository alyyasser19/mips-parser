package main.java;

public class Word {
    private String Key;
    private Object Value;

    public Word(String key, Object value) {
        Key = key;
        Value = value;
    }

    public String getKey() {
        return Key;
    }

    public Object getValue() {
        return Value;
    }

    public void setValue(Object value) {
        Value = value;
    }

}
