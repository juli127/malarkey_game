package com.ua.kramarenko104.model;

import com.ua.kramarenko104.dao.DAO;

public class WhatDoes implements WordResource {

    DAO dao;
    String sourceFilePath;

    public WhatDoes(String sourceFilePath) {
        dao = new DAO("actions", "action");
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
