public class Noun {

    String name;
    boolean isMale;

    public Noun(String name, boolean isMale) {
        this.name = name;
        this.isMale = isMale;
    }

    public boolean isMale() {
        return isMale;
    }

    public String getName() {
        return name;
    }
}
