package com.ua.kramarenko104.model;

import com.ua.kramarenko104.dao.FileWorker;
import org.apache.log4j.Logger;

import java.util.concurrent.CountDownLatch;

public class Who implements WordProcessing, Runnable  {

    private static Logger logger = Logger.getLogger(Who.class);
    private FileWorker fileWorker;
    private String resultWord;
    private CountDownLatch cdl;

    public Who(String sourceFilePath, CountDownLatch cdl) {
        this.fileWorker = new FileWorker(sourceFilePath);
        this.cdl = cdl;
    }

    @Override
    public void run() {
        resultWord = fileWorker.getRandomWord();
        logger.debug("[" + Thread.currentThread().getName() + "] " + resultWord);
        cdl.countDown();
    }

    public String getWord() {
        return resultWord;
    }

    @Override
    public void addWord(String word) {
        fileWorker.addWord(word);
    }

    // possibility to clear file from GUI
    private void clearFile() {
        fileWorker.clearFile();
    }

    // file already is filled out, do nothing here
    @Override
    public void fillWithValues(){
    }
}


