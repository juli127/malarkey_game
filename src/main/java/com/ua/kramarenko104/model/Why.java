package com.ua.kramarenko104.model;

import com.ua.kramarenko104.dao.DBWorker;

public class Why implements WordResource {

    private DBWorker DBWorker;
    private String sourceFilePath;

    public Why(String sourceFilePath) {
        DBWorker = new DBWorker("reasons", "reason");
        this.sourceFilePath = sourceFilePath;
    }

    @Override
    public void fillWithValues() {
        DBWorker.init();
        DBWorker.fillWithValues(sourceFilePath);
    }

    @Override
    public String getRandomWord() {
        return DBWorker.getRandomWord();
    }

    @Override
    public void addWord(String word) {
        DBWorker.addWord(word);
    }

    public void close() {
        DBWorker.close();
    }
}
