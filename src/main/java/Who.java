import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public class Who {

    static String filePath;

    public Who(String filePath) {
        this.filePath = filePath;
    }

    public void clearFile() {
        // for testing /////
        try (FileWriter writer = new FileWriter(filePath, false)) {
            writer.write("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean wordIsPresentInFile(String searchWord) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = null;
            while((line = br.readLine()) != null){
                if(line.equalsIgnoreCase(searchWord)){
                    //System.out.println("Already present: " + searchWord);
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

    public static String getRandomWordFromFile() {
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
}


