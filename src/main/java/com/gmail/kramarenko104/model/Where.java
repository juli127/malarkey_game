package com.gmail.kramarenko104.model;

import com.gmail.kramarenko104.dao.FileWorker;
import org.apache.log4j.Logger;
import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Where extends RunnableWord implements Runnable {

    private static Logger logger = Logger.getLogger(Where.class);
    private static List<String> listWhere;
    private FileWorker fileWorker;

    public Where(Path sourceFilePath) {
        super(sourceFilePath);
        listWhere = new ArrayList<>();
        this.fileWorker = new FileWorker(sourceFilePath);
    }

    @Override
    public void run() {
        int pos = (int)(Math.random() * listWhere.size());
        resultWord = listWhere.get(pos);
        logger.debug("[" + Thread.currentThread().getName() + "] " + resultWord);
        cdl.countDown();
    }

    @Override
    public void fillWithValues() {
        listWhere.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(sourceFilePath.toFile()))) {
            String word = "";
            while ((word = br.readLine()) != null) {
                listWhere.add(word);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    // add the new word from GUI to source file and local list
    public void addWord(String word) {
        super.addWord(word);
        listWhere.add(word);
        logger.debug("Phrase '" + word + "' was added\n" );
    }
}