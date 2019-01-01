package com.ua.kramarenko104.dao;

import java.io.*;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class FileWorker implements SourceWorker{

    private String sourceFilePath;

    public FileWorker(String sourceFilePath) {
        this.sourceFilePath = sourceFilePath;
    }

    @Override
    public void addWord(String word) {
        if (!wordIsPresentInFile(word)) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(sourceFilePath, true))) {
                writer.write(word + "\n");
                writer.flush();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    @Override
    public String getRandomWord() {
        List<String> linesList = Collections.emptyList();
        try (BufferedReader br = new BufferedReader(new FileReader(sourceFilePath))) {
            linesList = br.lines().collect(Collectors.toList());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int pos = (int) (Math.random() * linesList.size());
        return linesList.get(pos);
    }

    public void clearFile() {
        try (FileWriter writer = new FileWriter(sourceFilePath, false)) {
            writer.write("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean wordIsPresentInFile(String searchWord) {
        try (BufferedReader br = new BufferedReader(new FileReader(sourceFilePath))) {
            String line = null;
            while((line = br.readLine()) != null){
                if(line.trim().equalsIgnoreCase(searchWord)){
                    //logger.debug("Already present: " + searchWord);
                    return true;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

}
