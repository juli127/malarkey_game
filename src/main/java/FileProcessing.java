import java.io.*;

public class FileProcessing {

    String filePath;

    public FileProcessing(String filePath) {
        this.filePath = filePath;

        // for testing /////
        try (FileWriter writer = new FileWriter(filePath, false)) {
            writer.write("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean wordIsPresentInFile(String searchWord) {
        String[] lines = null;
        try (FileInputStream fis = new FileInputStream(new File(filePath))){
            byte[] content = new byte[fis.available()];
            fis.read(content);
            lines = new String(content, "UTF-8").split("\n");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String noun : lines) {
            if (noun.equalsIgnoreCase(searchWord)) {
                //System.out.println("Already present: " + searchWord);
                return true;
            }
        }
        return false;
    }

    public String getRandomWordFromFile() {
        String[] lines = null;
        try (FileInputStream fis = new FileInputStream(new File(filePath))){
            byte[] content = new byte[fis.available()];
            fis.read(content);
            lines = new String(content, "UTF-8").split("\n");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int pos = (int) (Math.random() * lines.length);
        return lines[pos];
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


