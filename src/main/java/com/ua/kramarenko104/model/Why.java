package com.ua.kramarenko104.model;

import com.ua.kramarenko104.dao.DAO;

public class Why implements WordResource {

    DAO dao;
    String sourceFilePath;

    public Why(String sourceFilePath) {
        dao = new DAO("reasons", "reason");
        this.sourceFilePath = sourceFilePath;
    }

    @Override
    public void fillWithValues() {
        dao.init();
        dao.fillWithValues(sourceFilePath);
    }

    @Override
    public String getRandomWord() {
        return dao.getRandomWord();
    }

    @Override
    public void close() {
        dao.close();
    }
}
