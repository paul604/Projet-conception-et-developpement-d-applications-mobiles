package iut.projet;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import iut.projet.adapteur.FilmAdapteur;

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

        //HTTP
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest =
                new StringRequest(
                        Request.Method.GET,
                        "https://api.themoviedb.org/3/search/movie",
                        new Response.Listener<String>() {
                            public void onResponse(String response) {

                                ArrayList<Film> lp = new ArrayList<>();
                                lp.add(new Film());
                                lp.add(new Film());
                                lp.add(new Film());
                                ListView lv = (ListView) findViewById(R.id.ListView1);
                                lv.setAdapter(new FilmAdapteur(this, lp));
                            }},
                        new Response.ErrorListener() {
                            public void onErrorResponse(VolleyError error) {
                                Log.e("VOLLEY", error.getMessage());
                            }}
                )
                {
                    protected Map<String,String> getParams(){
                        Map<String,String> params = new HashMap<>();
                        params.put("api_key", "a719a84bea8cbaa79d9e9934832b24d3");
                        params.put("language", Locale.getDefault().toString().replace("_","-"));
                        params.put("query", "");
                        return params;
                    }
                };
        queue.add(stringRequest);
    }
}
