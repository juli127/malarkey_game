package com.ua.kramarenko104.model;

import com.ua.kramarenko104.dao.FileWorker;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Where implements WordResource {

    private static List<String> listWhere;
    private String sourceFilePath;
    private FileWorker fileWorker;

    public Where(String sourceFilePath) {
        listWhere = new ArrayList<>();
        this.sourceFilePath = sourceFilePath;
        this.fileWorker = new FileWorker(sourceFilePath);
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
    public String getRandomWord(){
        int pos = (int)(Math.random() * listWhere.size());
        return listWhere.get(pos);
    }

    @Override
    // add word from GUI to source file and local list
    public void addWord(String word) {
        listWhere.add(word);
        fileWorker.addWord(word);
    }

}
