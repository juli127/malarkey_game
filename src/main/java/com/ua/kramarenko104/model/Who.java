package com.ua.kramarenko104.model;

import com.ua.kramarenko104.dao.FileWorker;
import org.apache.log4j.Logger;

public class Who implements WordResource {

    private static Logger logger = Logger.getLogger(Who.class);
    private FileWorker fileWorker;

    public Who(String sourceFilePath) {
        this.fileWorker = new FileWorker(sourceFilePath);
        //clearFile();
    }

    @Override
    public String getRandomWord() {
        return fileWorker.getRandomWord();
    }

    @Override
    public void addWord(String word) {
        fileWorker.addWord(word);
    }

    // file already is filled out, do nothing here
    @Override
    public void fillWithValues(){
    }

    // for testing
    // + possibility to clear file from GUI
    private void clearFile() {
        fileWorker.clearFile();
    }
}


