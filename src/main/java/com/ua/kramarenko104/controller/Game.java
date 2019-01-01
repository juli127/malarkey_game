package com.ua.kramarenko104.controller;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import com.ua.kramarenko104.model.*;
import org.apache.log4j.Logger;

public class Game {

    private static final String WHO_FILE_PATH = Paths.get(".", "/src/main/resources/nouns.txt").toAbsolutePath().normalize().toString();
    private static final String VERB_FILE_PATH = Paths.get(".", "/src/main/resources/verbs.txt").toAbsolutePath().normalize().toString();
    private static final String WHERE_FILE_PATH = Paths.get(".", "/src/main/resources/where.txt").toAbsolutePath().normalize().toString();
    private static final String WHY_FILE_PATH = Paths.get(".", "/src/main/resources/why.txt").toAbsolutePath().normalize().toString();
    private static Logger logger = Logger.getLogger(Game.class);

    private CountDownLatch cdl;
    private List<RunnableWord> listWords;

    public Game() {
        initSources();
    }

    private void initSources() {

        listWords = new ArrayList<>();

        // 'WHO'
        // first resource for sentence' words: local file 'nouns.txt's
        RunnableWord who = new Who(WHO_FILE_PATH);
        listWords.add(who);

        // 'WHAT DOES'
        // next resource for sentence' words: local MySQL database, table 'whatDoes'
        RunnableWord whatDoes = new WhatDoes(VERB_FILE_PATH);
        listWords.add(whatDoes);

        // 'WHERE'
        // next resource for sentence' words: local strings' list
        RunnableWord where = new Where(WHERE_FILE_PATH);
        listWords.add(where);

        // 'WHY'
        // next resource for sentence' words: local MySQL database, table 'reasons'
        RunnableWord why = new Why(WHY_FILE_PATH);
        listWords.add(why);

        for(RunnableWord w: listWords){
            w.fillWithValues();
        }
    }

    public String createSentence(){

        this.cdl = new CountDownLatch(4);
        for(RunnableWord w: listWords){
            w.setCountDownLatch(cdl);
        }

        // threads for word's parallel creation
        for(RunnableWord w: listWords){
            new Thread(w).start();
        }

        // wait until all thread finish their work
        try {
            cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.debug("all 4 thread finish their work:");

        // collect result of all threads
        StringBuilder sentence = new StringBuilder();
        for(RunnableWord w: listWords){
            sentence.append(w.getWord()).append(" ");
        }
        sentence.append("\n----------------------------------------------");

        return sentence.toString();
    }

    public void exit(){
        for(RunnableWord w: listWords){
            w.close();
        }
    }
}
