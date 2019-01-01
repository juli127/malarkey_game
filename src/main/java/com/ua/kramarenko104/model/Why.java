package com.ua.kramarenko104.model;

import com.ua.kramarenko104.dao.DBWorker;
import org.apache.log4j.Logger;

public class Why extends RunnableWord implements Runnable {

    private static Logger logger = Logger.getLogger(Why.class);
    private DBWorker DBWorker;

    public Why(String sourceFilePath) {
        super(sourceFilePath);
        DBWorker = new DBWorker("reasons", "reason");
        this.sourceFilePath = sourceFilePath;
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
    public void addWord(String word) {
        DBWorker.addWord(word);
    }

    @Override
    public void close() {
        DBWorker.close();
    }
}
