package com.ua.kramarenko104.controller;

import java.nio.file.Paths;
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
    private WhatDoes actions;
    private Where where;
    private Why why;

    public Game() {
        initSources();
    }

    private void initSources() {
        // 'WHO'
        // first resource for sentence' words: local file 'nouns.txt's
        who = new Who(WHO_FILE_PATH);
        who.fillWithValues();

        // 'WHAT DOES'
        // next resource for sentence' words: local MySQL database, table 'actions'
        actions = new WhatDoes(VERB_FILE_PATH);
        actions.fillWithValues();

        // 'WHERE'
        // next resource for sentence' words: local strings' list
        where = new Where(WHERE_FILE_PATH);
        where.fillWithValues();

        // 'WHY'
        // next resource for sentence' words: local MySQL database, table 'reasons'
        why = new Why(WHY_FILE_PATH);
        why.fillWithValues();
    }

    public String createSentence(){
        StringBuilder sentence = new StringBuilder();
        sentence.append(who.getRandomWord()).append(" ")
                .append(actions.getRandomWord()).append(" ")
                .append(where.getRandomWord()).append(" ")
                .append(why.getRandomWord());
        // close DBWorker resources
        actions.close();
        why.close();
        return sentence.toString();
    }
}
