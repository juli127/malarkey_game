package com.ua.kramarenko104.model;

import com.ua.kramarenko104.dao.FileWorker;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Where extends RunnableWord implements Runnable {

    private static Logger logger = Logger.getLogger(Where.class);
    private static List<String> listWhere;
    private FileWorker fileWorker;

    public Where(String sourceFilePath) {
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
    public void fillWithValues(){
        listWhere.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(sourceFilePath))) {
            String line = "";
            while((line = br.readLine()) != null){
                listWhere.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    // add word from GUI to source file and local list
    public void addWord(String word) {
        listWhere.add(word);
        fileWorker.addWord(word);
    }
}
