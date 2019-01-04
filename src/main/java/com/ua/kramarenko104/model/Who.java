package com.ua.kramarenko104.model;

import com.ua.kramarenko104.dao.FileWorker;
import org.apache.log4j.Logger;
import java.nio.file.Path;

public class Who extends RunnableWord implements Runnable {

    private static Logger logger = Logger.getLogger(Who.class);
    private FileWorker fileWorker;

    public Who(Path sourceFilePath) {
        super(sourceFilePath);
        this.fileWorker = new FileWorker(sourceFilePath);
    }

    @Override
    public void run() {
        resultWord = fileWorker.getRandomWord();
        logger.debug("[" + Thread.currentThread().getName() + "] " + resultWord);
        cdl.countDown();
    }

    @Override
    public void addWord(String word) {
        fileWorker.addWord(word);
    }

    // possibility to clear file from GUI
    private void clearFile() {
        fileWorker.clearFile();
    }

}


