import java.nio.file.Paths;
import java.util.Set;

public class RunApp {

    public static void main(String[] args) {

        initSources();

    }

    private static void initSources() {

        // first resource for sentence' words: file with nouns
        // fill out local file with nouns ///////////////////////////////////////////
        String NOUNS_FILE_PATH = Paths.get(".").toAbsolutePath().normalize().toString() + "/src/main/resources/nouns.txt";
        FileProcessing fileProcess = new FileProcessing(NOUNS_FILE_PATH);

        fileProcess.addNoun("девочка Маша");
        fileProcess.addNoun("программист Сергей");
        fileProcess.addNoun("МарьИванна");
        fileProcess.addNoun("депутат Иван Петрович Видпочивайло");
        fileProcess.addNoun("сантехник Василий");
        fileProcess.addNoun("кот Эдуард");
        fileProcess.addNoun("сантехник Василий");
        fileProcess.addNoun("диктор телевидения Евгений");
        fileProcess.addNoun("мой начальник");
        fileProcess.addNoun("девочка Маша");
        fileProcess.addNoun("сосед-дебошир Гоша");
        fileProcess.addNoun("слон в зоопарке");
        fileProcess.addNoun("загадочный мужчина в кепке");
        fileProcess.addNoun("дама в Мерседесе");
        System.out.println("RANDOM name: "+ fileProcess.getRandomWordFromFile());

        //////////////




    }
}
