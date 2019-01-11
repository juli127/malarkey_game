package com.gmail.kramarenko104.dao;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import static java.nio.file.StandardOpenOption.*;

public class FileWorker implements SourceWorker {

    private Path sourceFilePath;

    public FileWorker(Path sourceFilePath) {
        this.sourceFilePath = sourceFilePath;
    }

    @Override
    public void addWord(String word) {
        if (!wordIsPresentInFile(word)) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(sourceFilePath.toFile(), true))) {
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
        try (BufferedReader br = new BufferedReader(new FileReader(sourceFilePath.toFile()))) {
            linesList = br.lines().collect(Collectors.toList());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int pos = (int)(Math.random() * linesList.size());
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
        try (BufferedReader br = new BufferedReader(new FileReader(sourceFilePath.toFile()))) {
            String word = "";
            while((word = br.readLine()) != null){
                if(word.trim().equalsIgnoreCase(searchWord)){
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
