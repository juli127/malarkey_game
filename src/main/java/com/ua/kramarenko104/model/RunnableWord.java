package com.ua.kramarenko104.model;

import java.util.concurrent.CountDownLatch;

public abstract class RunnableWord implements Runnable{

    protected CountDownLatch cdl;
    protected String resultWord;
    protected String sourceFilePath;

    public RunnableWord(String sourceFilePath) {
        this.sourceFilePath = sourceFilePath;
    }

    public abstract void addWord(String word);

    public abstract void run();

    public String getWord() {
        return resultWord;
    }

    public void setCountDownLatch(CountDownLatch cdl){
        this.cdl = cdl;
    }

    public void close(){}

    public void fillWithValues(){}

}
