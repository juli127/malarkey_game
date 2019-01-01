package com.ua.kramarenko104;

import com.ua.kramarenko104.controller.Game;
import org.apache.log4j.Logger;

public class RunApp {

    private static Logger logger = Logger.getLogger(RunApp.class);

    public static void main(String[] args) {
        Game game = new Game();
        String sentence = game.createSentence();
        logger.debug(sentence);
    }

}
