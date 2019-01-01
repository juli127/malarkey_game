package com.ua.kramarenko104.controller;

import java.nio.file.Paths;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

import com.ua.kramarenko104.model.WhatDoes;
import com.ua.kramarenko104.model.Where;
import com.ua.kramarenko104.model.Who;
import com.ua.kramarenko104.model.Why;
import org.apache.log4j.Logger;

public class Game {

    private static final String WHO_FILE_PATH = Paths.get(".", "/src/main/resources/nouns.txt").toAbsolutePath().normalize().toString();
    private static final String VERB_FILE_PATH = Paths.get(".", "/src/main/resources/verbs.txt").toAbsolutePath().normalize().toString();
    private static final String WHERE_FILE_PATH = Paths.get(".", "/src/main/resources/where.txt").toAbsolutePath().normalize().toString();
    private static final String WHY_FILE_PATH = Paths.get(".", "/src/main/resources/why.txt").toAbsolutePath().normalize().toString();
    private static Logger logger = Logger.getLogger(Game.class);

    private Who who;
    private WhatDoes whatDoes;
    private Where where;
    private Why why;
    private CountDownLatch cdl;

    public Game() {
        initSources();
    }

    private void initSources() {
        this.cdl = new CountDownLatch(4);

        // 'WHO'
        // first resource for sentence' words: local file 'nouns.txt's
        who = new Who(WHO_FILE_PATH, cdl);
        who.fillWithValues();

        // 'WHAT DOES'
        // next resource for sentence' words: local MySQL database, table 'whatDoes'
        whatDoes = new WhatDoes(VERB_FILE_PATH, cdl);
        whatDoes.fillWithValues();

        // 'WHERE'
        // next resource for sentence' words: local strings' list
        where = new Where(WHERE_FILE_PATH, cdl);
        where.fillWithValues();

        // 'WHY'
        // next resource for sentence' words: local MySQL database, table 'reasons'
        why = new Why(WHY_FILE_PATH, cdl);
        why.fillWithValues();
    }

    public String createSentence(){
        StringBuilder sentence = new StringBuilder();

        // threads for word's parallel creation
        new Thread(who).start();
        new Thread(whatDoes).start();
        new Thread(where).start();
        new Thread(why).start();

        // wait until all thread finish their work
        try {
            cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.debug("all 4 thread finish their work:");

        // collect result of all threads
        sentence.append(who.getWord()).append(" ")
                .append(whatDoes.getWord()).append(" ")
                .append(where.getWord()).append(" ")
                .append(why.getWord()).append("\n")
                .append("----------------------------------------------");

        // close DBWorker resources
        whatDoes.close();
        why.close();
        return sentence.toString();
    }
}
