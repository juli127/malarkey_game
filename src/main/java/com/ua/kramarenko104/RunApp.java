package com.ua.kramarenko104;

import java.nio.file.Paths;
import org.apache.log4j.Logger;

public class RunApp {

private static Logger logger = Logger.getLogger(RunApp.class);

    public static void main(String[] args) {

        // expected result:
        // sentence like this one: "Who?Сантехник Василий -- how?задумчиво --
        // what does?сидит -- where? в бигудях на кухне -- ?why потому что пришла весна"
        initSources();

    }

    private static void initSources() {

        StringBuilder sentence = new StringBuilder();

        // 'WHO'
        // first resource for sentence' words: local file nouns.txt
        String NOUNS_FILE_PATH = Paths.get(".", "/src/main/resources/nouns.txt").toAbsolutePath().normalize().toString();
        Who who = new Who(NOUNS_FILE_PATH);
        who.clearFile();
        who.init();
        sentence.append(Who.getRandomWordFromFile());

        // 'WHAT DOES'
        // next resource for sentence' words: local MySQL database
        WhatDoes actions = new WhatDoes();
        actions.init();
        String action = actions.getAction();
        actions.close();
        sentence.append(" ").append(action);

        // 'WHERE'
        // next resource for sentence' words: local strings' list
        Where where = new Where();
        where.init();
        sentence.append(" ").append(Where.getRandomWhere());

        logger.debug(sentence.toString());

    }
}
