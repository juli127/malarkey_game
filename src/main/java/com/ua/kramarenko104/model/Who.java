package com.ua.kramarenko104.model;

import com.ua.kramarenko104.dao.FileWorker;
import org.apache.log4j.Logger;
import java.util.concurrent.CountDownLatch;

public class Who implements RunnableWord {

    private static Logger logger = Logger.getLogger(Who.class);
    private FileWorker fileWorker;
    private String resultWord;
    private CountDownLatch cdl;

    public Who(String sourceFilePath) {
        this.fileWorker = new FileWorker(sourceFilePath);
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

    @Override
    public void setCountDownLatch(CountDownLatch cdl){
        this.cdl = cdl;
    }

    public void close() {
    }
}


