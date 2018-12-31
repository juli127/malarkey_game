package com.ua.kramarenko104.model;

import com.ua.kramarenko104.dao.DAO;

public class WhatDoes implements WordResource {

    private DAO dao;
    private String sourceFilePath;

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

    public void close() {
        dao.close();
    }
}
