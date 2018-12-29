package com.ua.kramarenko104;

import java.nio.file.Paths;
import org.apache.log4j.Logger;

public class RunApp {

private static Logger logger = Logger.getLogger(RunApp.class);

    public static void main(String[] args) {

        // expected result:
        // sentence like this one: "com.ua.kramarenko104.Who?Сантехник Василий -- how?задумчиво --
        // what does?сидит -- where? в бигудях на кухне -- ?why потому что пришла весна"
        initSources();

    }

    private static void initSources() {

        StringBuilder sentence = new StringBuilder();

        // first resource for sentence' words: file with nouns
        // fill out local file with nouns ///////////////////////////////////////////
        String NOUNS_FILE_PATH = Paths.get(".", "/src/main/resources/nouns.txt").toAbsolutePath().normalize().toString();
        Who who = new Who(NOUNS_FILE_PATH);
        who.clearFile();
        who.addNoun("Шумный сосед сверху");
        who.addNoun("Программист Сергей");
        who.addNoun("МарьИванна");
        who.addNoun("Депутат Иван Петрович Видпочивайло");
        who.addNoun("Сантехник Василий");
        who.addNoun("Кот Эдуард");
        who.addNoun("Диктор телевидения Евгений");
        who.addNoun("Девочка Маша");
        who.addNoun("Сосед-дебошир Гоша");
        who.addNoun("Слон из зоопарка");
        who.addNoun("Загадочный мужчина в кепке");
        who.addNoun("Флегматичный олень Бенедикт");
        who.addNoun("Дама в норковом манто");
        sentence.append(Who.getRandomWordFromFile());

        //////////////
        // what does
        // take actions from local MySQL database
        WhatDoes verb = new WhatDoes();
        verb.addAction("торгует апельсинами");
        verb.addAction("покупает лотерейный билет");
        verb.addAction("сажает картошку и помидоры");
        verb.addAction("красит губы в алый цвет");
        verb.addAction("стрижется налысо");
        verb.addAction("отжимается 100 раз");
        verb.addAction("поднимает штангу");
        verb.addAction("грызет семечки");
        verb.addAction("рожает пятого ребенка");
        verb.addAction("сутками сидит в позе лотоса");
        verb.addAction("ест ложками красную икру");
        verb.addAction("медитирует");
        verb.addAction("слоняется без дела");
        verb.addAction("не спит по ночам");
        verb.addAction("третирует соседей");
        verb.addAction("заводит трех питбулей");
        verb.addAction("красит волосы в синий цвет");
        verb.addAction("сочиняет стихи");
        verb.addAction("громко и фальшиво поет");
        verb.addAction("ест торт перед сном");
        String action = verb.getAction();
        verb.close();
        sentence.append(" ").append(action);

        // next resource for sentence' words: list with objects
        // fill out local list with objects ///////////////////////////////////////////
        Where where = new Where();
        where.clearList();
        where.addObject("с бутылкой водки на кухне");
        where.addObject("на пляжах Рио-де-Жанейро");
        where.addObject("в печали на работе");
        where.addObject("под деревом по имени липа");
        where.addObject("на курсах кройки и шитья");
        where.addObject("на заседании больших боссов");
        where.addObject("в фойе театра оперетты");
        where.addObject("в шикарном ресторане");
        where.addObject("в собственном пентхаузе");
        where.addObject("в бигудях на кухне");
        where.addObject("у синего-синего моря");
        where.addObject("в библиотеке");
        where.addObject("на диване с рюмкой чая");
        where.addObject("на горе Фудзияма");
        where.addObject("на развалинах часовни");
        where.addObject("на полярной станции 'Академик Вернадский'");
        where.addObject("у барной стойки в ресторане 'Арагви'");
        sentence.append(" ").append(Where.getRandomWhere());
        logger.debug(sentence.toString());

    }
}
