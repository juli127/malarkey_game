package com.ua.kramarenko104;

import java.nio.file.Paths;
import com.ua.kramarenko104.model.WordResource;
import com.ua.kramarenko104.model.WhatDoes;
import com.ua.kramarenko104.model.Where;
import com.ua.kramarenko104.model.Who;
import com.ua.kramarenko104.model.Why;
import org.apache.log4j.Logger;

public class RunApp {

private static Logger logger = Logger.getLogger(RunApp.class);
private static final String WHO_FILE_PATH = Paths.get(".", "/src/main/resources/nouns.txt").toAbsolutePath().normalize().toString();
private static final String VERB_FILE_PATH = Paths.get(".", "/src/main/resources/verbs.txt").toAbsolutePath().normalize().toString();
private static final String WHERE_FILE_PATH = Paths.get(".", "/src/main/resources/where.txt").toAbsolutePath().normalize().toString();
private static final String WHY_FILE_PATH = Paths.get(".", "/src/main/resources/why.txt").toAbsolutePath().normalize().toString();

    public static void main(String[] args) {
        
        initSources();
    }

    private static void initSources() {

        StringBuilder sentence = new StringBuilder();

        // 'WHO'
        // first resource for sentence' words: local file 'nouns.txt's
        WordResource who = new Who(WHO_FILE_PATH);
        who.fillWithValues();
        sentence.append(who.getRandomWord());

        // 'WHAT DOES'
        // next resource for sentence' words: local MySQL database, table 'actions'
        WordResource actions = new WhatDoes(VERB_FILE_PATH);
        actions.fillWithValues();
        sentence.append(" ").append(actions.getRandomWord());
        actions.close();

        // 'WHERE'
        // next resource for sentence' words: local strings' list
        WordResource where = new Where(WHERE_FILE_PATH);
        where.fillWithValues();
        sentence.append(" ").append(where.getRandomWord());

        // 'WHY'
        // next resource for sentence' words: local MySQL database, table 'reasons'
        WordResource why = new Why(WHY_FILE_PATH);
        why.fillWithValues();
        sentence.append(" ").append(why.getRandomWord());
        why.close();

        logger.debug(sentence.toString());

    }
}
