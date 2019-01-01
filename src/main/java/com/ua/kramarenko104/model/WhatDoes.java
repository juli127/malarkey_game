package com.ua.kramarenko104.model;

import com.ua.kramarenko104.dao.DBWorker;
import org.apache.log4j.Logger;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class WhatDoes implements WordProcessing, Runnable {

    private static Logger logger = Logger.getLogger(WhatDoes.class);
    private DBWorker DBWorker;
    private String sourceFilePath;
    private String resultWord;
    private CountDownLatch cdl;

    public WhatDoes(String sourceFilePath, CountDownLatch cdl) {
        DBWorker = new DBWorker("actions", "action");
        this.sourceFilePath = sourceFilePath;
        this.cdl = cdl;
    }

    @Override
    public void run() {
        resultWord = DBWorker.getRandomWord();
        logger.debug("[" + Thread.currentThread().getName() + "] " + resultWord);
        cdl.countDown();
    }

    @Override
    public void fillWithValues() {
        DBWorker.init();
        DBWorker.fillWithValues(sourceFilePath);
    }

    @Override
    public String getWord() {
        return resultWord;
    }

    @Override
    public void addWord(String word) {
        DBWorker.addWord(word);
    }

    public void close() {
        DBWorker.close();
    }
}
