package com.ua.kramarenko104.model;

import org.apache.log4j.Logger;
import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public class Who implements WordResource {

    private static String filePath;
    private static Logger logger = Logger.getLogger(Who.class);

    public Who(String filePath) {
        this.filePath = filePath;
        //clearFile();
    }

    // for testing
    // + possibility to clear file from GUI
    private void clearFile() {
        try (FileWriter writer = new FileWriter(filePath, false)) {
            writer.write("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getRandomWord() {
        List<String> linesList = null;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            linesList = br.lines().collect(Collectors.toList());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int pos = (int) (Math.random() * linesList.size());
        return linesList.get(pos);
    }

    // possibility to add noun from GUI
    public void addNoun(String noun) {
        if (!wordIsPresentInFile(noun)) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
                writer.write(new StringBuilder(noun).append("\n").toString());
                writer.flush();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    @Override
    public void close() {
    }

    // file already is filled out, do nothing here
    @Override
    public void fillWithValues(){
    }

    private boolean wordIsPresentInFile(String searchWord) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = null;
            while((line = br.readLine()) != null){
                if(line.equalsIgnoreCase(searchWord)){
                    logger.debug("Already present: " + searchWord);
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


