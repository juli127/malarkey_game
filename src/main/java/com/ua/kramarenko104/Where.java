package com.ua.kramarenko104;

import java.util.ArrayList;
import java.util.List;

public class Where {

    static List<String> listWhere;


    public Where() {
        listWhere = new ArrayList<>();
    }

    public void init(){
        clearList();
        listWhere.add("с бутылкой водки в кармане пальто");
        listWhere.add("на пляже Рио-де-Жанейро");
        listWhere.add("в приемной своего шефа");
        listWhere.add("под деревом по имени липа");
        listWhere.add("на курсах кройки и шитья");
        listWhere.add("на заседании больших боссов");
        listWhere.add("в фойе театра оперетты");
        listWhere.add("в пункте приема стеклотары");
        listWhere.add("в собственном пентхаузе");
        listWhere.add("на территории завода Укрпромбытсбытгазвнас");
        listWhere.add("у синего-синего моря");
        listWhere.add("в библиотеке");
        listWhere.add("на диване с рюмкой чая");
        listWhere.add("на горе Фудзияма");
        listWhere.add("на развалинах часовни");
        listWhere.add("на полярной станции 'Академик Вернадский'");
        listWhere.add("у барной стойки в ресторане 'Арагви'");
    }

    private void clearList(){
        // for testing /////
        listWhere.clear();
    }

    public static String getRandomWord(){
        int pos = (int)(Math.random() * listWhere.size());
        return listWhere.get(pos);
    }

}
