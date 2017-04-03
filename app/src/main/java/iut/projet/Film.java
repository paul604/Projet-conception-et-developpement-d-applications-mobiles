package iut.projet;

/**
 * Created by E155441H on 30/03/17.
 */
public class Film {

    private int id;
    private String title;
    private String desc;
    private String tagline;
    private String poster_path;

    public Film(int id, String title, String desc, String poster_path, String tagline){
        this.id =id;
        this.title=title;
        this.desc=desc;
        this.tagline=tagline;
        this.poster_path=poster_path;
    }

    public Film(int id, String title, String desc, String poster_path){
        this(id, title, desc, poster_path, null);
    }

    public int getId(){
        return id;
    }

    public String gettitle() {
        return title;
    }

    public String getNaiss() {
        return desc;
    }

    public String getTagline(){
        return tagline;
    }

    public String getPoster_path() {
        return poster_path;
    }
}
