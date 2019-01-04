package com.ua.kramarenko104.controller;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.ua.kramarenko104.model.*;
import org.apache.log4j.Logger;

public class Game {

    private static final Path WHO_FILE_PATH = Paths.get(".", "/src/main/resources/nouns.txt").toAbsolutePath();
    private static final Path VERB_FILE_PATH = Paths.get(".", "/src/main/resources/verbs.txt").toAbsolutePath();
    private static final Path WHERE_FILE_PATH = Paths.get(".", "/src/main/resources/where.txt").toAbsolutePath();
    private static final Path WHY_FILE_PATH = Paths.get(".", "/src/main/resources/why.txt").toAbsolutePath();
    private static Logger logger = Logger.getLogger(Game.class);

    private List<RunnableWord> wordsList;

    public Game() {
        initSources();
    }

    private void initSources() {

        wordsList = new ArrayList<>();

        // 'WHO'
        // first resource for sentence' words: local file 'nouns.txt'
        RunnableWord who = new Who(WHO_FILE_PATH);
        wordsList.add(who);

        // 'WHAT DOES'
        // next resource for sentence' words: local MySQL database, table 'whatDoes'
        RunnableWord whatDoes = new WhatDoes(VERB_FILE_PATH);
        wordsList.add(whatDoes);

        // 'WHERE'
        // next resource for sentence' words: local strings' list
        RunnableWord where = new Where(WHERE_FILE_PATH);
        wordsList.add(where);

        // 'WHY'
        // next resource for sentence' words: local MySQL database, table 'reasons'
        RunnableWord why = new Why(WHY_FILE_PATH);
        wordsList.add(why);

        for(RunnableWord word: wordsList){
            word.fillWithValues();
        }
    }

    public String createSentence(){

        CountDownLatch startLatch = new CountDownLatch(4);
        ExecutorService pool = Executors.newCachedThreadPool();
        // run threads for word's parallel creation
        for(RunnableWord word: wordsList){
            word.setCountDownLatch(startLatch);
            pool.execute(word);
        }

        // wait until all threads finish their work
        try {
            startLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        pool.shutdown();
        logger.debug("all 4 threads finish their work:");

        // collect results from all threads
        StringBuilder sentence = new StringBuilder();
        for(RunnableWord word: wordsList){
            sentence.append(word.getWord()).append(" ");
        }
        sentence.append("\n----------------------------------------------");

        return sentence.toString();
    }

    public void exit(){
        for(RunnableWord word: wordsList){
            word.close();
        }
    }
}
