import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public class Who {

    static String filePath;

    public Who(String filePath) {
        this.filePath = filePath;
    }

    public void clearFile(){
        // for testing /////
        try (FileWriter writer = new FileWriter(filePath, false)) {
            writer.write("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean wordIsPresentInFile(String searchWord) {
        List<String>  linesList = null;
        try (FileReader fr = new FileReader(filePath)){
            BufferedReader br = new BufferedReader(fr);
            linesList = br.lines().collect(Collectors.toList());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String noun : linesList) {
            if (noun.equalsIgnoreCase(searchWord)) {
                //System.out.println("Already present: " + searchWord);
                return true;
            }
        }
        return false;
    }

    public static String getRandomWordFromFile() {
        List<String>  linesList = null;
        try (FileReader fr = new FileReader(filePath)){
            BufferedReader br = new BufferedReader(fr);
            linesList = br.lines().collect(Collectors.toList());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int pos = (int) (Math.random() * linesList.size());
        return linesList.get(pos);
    }

    public void addNoun(String noun) {
        if (!wordIsPresentInFile(noun)) {
            try (FileWriter writer = new FileWriter(filePath, true)) {
                writer.write(new StringBuilder(noun).append("\n").toString());
                writer.flush();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}


