import java.nio.file.Paths;

public class RunApp {

    public static void main(String[] args) {

        // expected result:
        // sentence like this one: "Who?Сантехник Василий -- how?задумчиво --
        // what does?сидит -- where? в бигудях на кухне -- ?why потому что пришла весна"
        initSources();

    }

    private static void initSources() {

        // first resource for sentence' words: file with nouns
        // fill out local file with nouns ///////////////////////////////////////////
        String NOUNS_FILE_PATH = Paths.get(".").toAbsolutePath().normalize().toString() + "/src/main/resources/nouns.txt";
        Who who = new Who(NOUNS_FILE_PATH);
        who.clearFile();
        who.addNoun("Шумный сосед сверху");
        who.addNoun("Программист Сергей");
        who.addNoun("МарьИванна");
        who.addNoun("Депутат Иван Петрович Видпочивайло");
        who.addNoun("Сантехник Василий");
        who.addNoun("Кот Эдуард");
        who.addNoun("Диктор телевидения Евгений");
        who.addNoun("Девочка Маша");
        who.addNoun("Сосед-дебошир Гоша");
        who.addNoun("Слон из зоопарка");
        who.addNoun("Загадочный мужчина в кепке");
        who.addNoun("Флегматичный олень Бенедикт");
        who.addNoun("Дама в норковом манто");
        System.out.println("RANDOM who: "+ Who.getRandomWordFromFile());

        //////////////


        // next resource for sentence' words: list with objects
        // fill out local list with objects ///////////////////////////////////////////
        Where where = new Where();
        where.clearList();
        where.addObject("с бутылкой водки на кухне");
        where.addObject("под зонтом на улице");
        where.addObject("в печали на работе");
        where.addObject("под деревом по имени липа");
        where.addObject("на курсах кройки и шитья");
        where.addObject("на заседании больших боссов");
        where.addObject("в кустах с пистолетом в засаде");
        where.addObject("в шикарном ресторане");
        where.addObject("в собственном пентхаузе");
        where.addObject("в бигудях на кухне");
        where.addObject("с последней гривней в кармане");
        where.addObject("в библиотеке");
        where.addObject("на диване с рюмкой чая");
        where.addObject("на горе Фудзияма");
        where.addObject("на развалинах часовни");
        where.addObject("на полярной станции 'Академик Вернадский'");
        where.addObject("у барной стойки в ресторане 'Арагви'");
        System.out.println("RANDOM where: "+ Where.getRandomWhere());

    }
}
