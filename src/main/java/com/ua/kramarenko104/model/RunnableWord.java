package com.ua.kramarenko104.model;

import java.io.Closeable;
import java.util.concurrent.CountDownLatch;

public interface RunnableWord extends Runnable {

    void fillWithValues();

    String getWord();

    void addWord(String word);

    void setCountDownLatch(CountDownLatch cdl);

    void close();

}
