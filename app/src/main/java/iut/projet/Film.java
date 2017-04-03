package iut.projet;

/**
 * Created by E155441H on 30/03/17.
 */
public class Film {

    private String title;
    private String desc;

    public Film(String title, String desc){
        this.title=title;
        this.desc=desc;
    }

    public String gettitle() {
        return title;
    }

    public String getNaiss() {
        return desc;
    }
}
