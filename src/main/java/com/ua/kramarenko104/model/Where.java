package com.ua.kramarenko104.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Where implements WordResource {

    static List<String> listWhere;
    String sourceFilePath;

    public Where(String sourceFilePath) {
        listWhere = new ArrayList<>();
        this.sourceFilePath = sourceFilePath;
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
    public void close() {
    }

}
