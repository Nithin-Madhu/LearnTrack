package com.airtribe.learntrack.util;

public class IdGenerator {

    private final String prefix;

    private long lastId = 0;

    public IdGenerator(String prefix) {
        this.prefix = prefix;
    }

    public String nextId(){
        return prefix + String.format("%03d",++lastId);
    }
}
