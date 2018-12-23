import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class FileProcessing {

    String filePath;

    public FileProcessing(String filePath) {
        this.filePath = filePath;
    }

    public Set<String> getNouns() {
        Set<String> nouns = new HashSet<>();
        StringBuilder noun = new StringBuilder();
        try (FileReader reader = new FileReader(filePath)) {
            int c;
            while((c=reader.read())!=-1){
                noun.append((char)c);
            }
            nouns.add(noun.toString());
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return nouns;
    }

    public void addNoun(String noun) {
        try(FileWriter writer = new FileWriter(filePath, true)) {
            writer.write(new StringBuilder(noun).append(" ").toString());
            writer.flush();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }
}


