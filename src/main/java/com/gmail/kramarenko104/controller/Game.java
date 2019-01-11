package com.gmail.kramarenko104.controller;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.gmail.kramarenko104.model.*;
import org.apache.log4j.Logger;

public class Game {

    private static final Path WHO_FILE_PATH = Paths.get(".", "/src/main/resources/nouns.txt").toAbsolutePath();
    private static final Path VERB_FILE_PATH = Paths.get(".", "/src/main/resources/verbs.txt").toAbsolutePath();
    private static final Path WHERE_FILE_PATH = Paths.get(".", "/src/main/resources/where.txt").toAbsolutePath();
    private static final Path WHY_FILE_PATH = Paths.get(".", "/src/main/resources/why.txt").toAbsolutePath();
    private RunnableWord who;
    private RunnableWord whatDoes;
    private RunnableWord where;
    private RunnableWord why;

    private static Logger logger = Logger.getLogger(Game.class);

    private List<RunnableWord> wordsList;

    public Game() {
        initSources();
    }

    private void initSources() {

       //logger.debug("Start application, run initSources:");
        wordsList = new ArrayList<>();

        // 'WHO'
        // first resource for sentence' words: local file 'nouns.txt'

        who = new Who(WHO_FILE_PATH);
        wordsList.add(who);
        logger.debug("'who' source was created from file 'nouns.txt'");

        // 'WHAT DOES'
        // next resource for sentence' words: local MySQL database, table 'whatDoes'
        whatDoes = new WhatDoes(VERB_FILE_PATH);
        wordsList.add(whatDoes);
        logger.debug("'what' source was created from MySQL database, table 'actions'");

        // 'WHERE'
        // next resource for sentence' words: local strings' list
        where = new Where(WHERE_FILE_PATH);
        wordsList.add(where);
        logger.debug("'where' source was created from local strings' list");

        // 'WHY'
        // next resource for sentence' words: local MySQL database, table 'reasons'
        why = new Why(WHY_FILE_PATH);
        wordsList.add(why);
        logger.debug("'why' source was created from MySQL database, table 'reasons'");

        for(RunnableWord word: wordsList){
            word.fillWithValues();
        }
        logger.debug("All sources were filled out with data from /resources text files");
        logger.debug("----------------------------------------------------------------");
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
        logger.debug(sentence);
        logger.debug("----------------------------------------------");

        return sentence.toString();
    }

    public RunnableWord getWho() {
        return who;
    }

    public RunnableWord getWhatDoes() {
        return whatDoes;
    }

    public RunnableWord getWhere() {
        return where;
    }

    public RunnableWord getWhy() {
        return why;
    }

    public void exit(){
        for(RunnableWord word: wordsList){
            word.close();
        }
        logger.debug("Close all resources and exit");
        System.exit(0);
    }
}