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
        clearFile();
    }

    private void clearFile() {
        // for testing /////
        try (FileWriter writer = new FileWriter(filePath, false)) {
            writer.write("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void fillWithValues(){
        addNoun("Шумный сосед сверху");
        addNoun("Программист Сергей");
        addNoun("МарьИванна");
        addNoun("Депутат Иван Петрович Видпочивайло");
        addNoun("Сантехник Василий");
        addNoun("Кот Эдуард");
        addNoun("Диктор телевидения Евгений");
        addNoun("Девочка Маша");
        addNoun("Сосед-дебошир Гоша");
        addNoun("Слон из зоопарка");
        addNoun("Загадочный мужчина в кепке");
        addNoun("Флегматичный олень Бенедикт");
        addNoun("Дама в норковом манто");
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

    @Override
    public void close() {
    }

    private void addNoun(String noun) {
        if (!wordIsPresentInFile(noun)) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
                writer.write(new StringBuilder(noun).append("\n").toString());
                writer.flush();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
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


