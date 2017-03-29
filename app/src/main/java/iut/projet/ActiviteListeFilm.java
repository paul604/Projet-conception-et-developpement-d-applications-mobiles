package iut.projet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class ActiviteListeFilm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activite_liste_film);

        //get Vall
        String choix;
        int nb;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                choix= null;
                nb=5;
            } else {
                choix= extras.getString("choix");
                nb=extras.getInt("nb");
            }
        } else {
            choix= (String) savedInstanceState.getSerializable("choix");
            nb= (int) savedInstanceState.getSerializable("nb");
        }

        Toast.makeText(ActiviteListeFilm.this, choix +" "+ nb, Toast.LENGTH_SHORT).show();
    }
}
