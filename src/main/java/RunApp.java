import java.nio.file.Paths;

public class RunApp {

    public static void main(String[] args) {

        String NOUNS_FILE_PATH = Paths.get(".").toAbsolutePath().normalize().toString() + "/src/main/resources/nouns.txt";
        System.out.println(NOUNS_FILE_PATH);
        FileProcessing fileProcess = new FileProcessing(NOUNS_FILE_PATH);

        fileProcess.addNoun("Маша");
        System.out.println(fileProcess.getNouns().toString());
        fileProcess.addNoun("Таня");
        System.out.println(fileProcess.getNouns().toString());
    }
}
