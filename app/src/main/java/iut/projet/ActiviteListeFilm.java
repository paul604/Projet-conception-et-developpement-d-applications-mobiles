package iut.projet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;

import iut.projet.adapteur.FilmAdapteur;

public class ActiviteListeFilm extends AppCompatActivity {

    private String choix;
    private int nb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activite_liste_film);

        //get Vall
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
                        "https://api.themoviedb.org/3/search/movie"+getParam(),
                        new Response.Listener<String>() {
                            public void onResponse(String response) {
                                try {

                                    final ArrayList<Film> lp = new ArrayList<>();

                                    JSONObject json = new JSONObject(response);
                                    JSONArray array = json.getJSONArray("results");
                                    for (int i = 0; i<array.length() && i<nb; i++) {
                                        JSONObject film = array.getJSONObject(i);
                                        String desc = film.getString("overview");
                                        if(desc.equals("")){
                                            desc="NC";
                                        }
                                        lp.add(new Film(film.getInt("id"),film.getString("title"), desc, film.getString("poster_path")));
                                    }

                                    ListView lv = (ListView) findViewById(R.id.ListView1);

                                    assert lv != null;


                                    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                                            Film film = lp.get(i);

                                            Intent intent = new Intent(ActiviteListeFilm.this,ActivityDetFilm.class);
                                            intent.putExtra("id",film.getId());
                                            intent.putExtra("img",film.getPoster_path());
                                            startActivity(intent);
                                        }
                                    });

                                    lv.setAdapter(new FilmAdapteur(ActiviteListeFilm.this, lp));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }},
                        new Response.ErrorListener() {
                            public void onErrorResponse(VolleyError error) {
                                Log.e("VOLLEY", error.getMessage());
                            }}
                );
        System.out.println(stringRequest.getUrl());
        queue.add(stringRequest);
    }

    private String getParam() {
        int nbFilms = (int) Math.ceil(nb/20);
        if(nbFilms==0){
            nbFilms=1;
        }
        System.out.println(nbFilms);

        StringBuilder params = new StringBuilder()
                .append("?api_key=a719a84bea8cbaa79d9e9934832b24d3")
                .append("&language=")
                .append(Locale.getDefault().toString().replace("_", "-"))
                .append("&query=")
                .append(choix.replace(" ","%20"))
                .append("&page=")
                .append(nbFilms);
        return params.toString();
    }
}
