import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Where {

    static List<String> listWhere;


    public Where() {
        listWhere = new ArrayList<>();
    }

    public void clearList(){
        // for testing /////
        listWhere.clear();
    }

    public void addObject(String where){
        listWhere.add(where);
    }

    public static String getRandomStaff(){
        int pos = (int)(Math.random() * listWhere.size());
        return listWhere.get(pos);
    }

    public List<String> getList(){
        return listWhere;
    }
}
