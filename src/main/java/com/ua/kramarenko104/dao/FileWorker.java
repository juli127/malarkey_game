package com.ua.kramarenko104.dao;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import static java.nio.file.StandardOpenOption.*;
import java.util.Collections;
import java.util.List;

public class FileWorker implements SourceWorker{

    private Path sourceFilePath;

    public FileWorker(Path sourceFilePath) {
        this.sourceFilePath = sourceFilePath;
    }

    @Override
    public void addWord(String word) {
        if (!wordIsPresentInFile(word)) {
            try (BufferedWriter writer = Files.newBufferedWriter(sourceFilePath, WRITE, APPEND)) {
                writer.write(word + "\n");
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getRandomWord() {
        List<String> linesList = Collections.emptyList();
        try {
            linesList = Files.readAllLines(sourceFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int pos = (int) (Math.random() * linesList.size());
        return linesList.get(pos);
    }

    public void clearFile() {
        try (BufferedWriter writer = Files.newBufferedWriter(sourceFilePath, WRITE)) {
            writer.write("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean wordIsPresentInFile(String searchWord) {
        try {
            List<String> linesList = Files.readAllLines(sourceFilePath);
            for(String word : linesList){
                if(word != null){
                    if(word.trim().equalsIgnoreCase(searchWord)){
                        //logger.debug("Already present: " + searchWord);
                        return true;
                    }
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
