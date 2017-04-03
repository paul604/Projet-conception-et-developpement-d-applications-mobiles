package iut.projet;

import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Locale;

public class ActivityDetFilm extends AppCompatActivity {

    int id;
    String img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_det_film);

        //get Vall
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                id = -1;
                img=null;
            } else {
                id = extras.getInt("id");
                img =  extras.getString("img");
            }
        } else {
            id = (int) savedInstanceState.getSerializable("id");
            img = (String) savedInstanceState.getSerializable("img");
        }

//        Toast.makeText(ActivityDetFilm.this, id, Toast.LENGTH_SHORT).show();
        System.out.println(id);

        //HTTP
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest =
                new StringRequest(
                        Request.Method.GET,
                        "https://api.themoviedb.org/3/movie/" + getParam(),
                        new Response.Listener<String>() {
                            public void onResponse(String response) {
                                try {

                                    JSONObject json = new JSONObject(response);

                                    TextView title = (TextView) findViewById(R.id.title);
                                    assert title != null;
                                    String titleT = json.getString("title");
                                    if(titleT.equals("")){
                                        titleT="NC";
                                    }
                                    title.setText(titleT);

                                    TextView tagLine = (TextView) findViewById(R.id.tagline);
                                    assert tagLine != null;
                                    String tagLineT = json.getString("tagline");
                                    if(tagLineT.equals("")){
                                        tagLineT="NC";
                                    }
                                    tagLine.setText(tagLineT);

                                    TextView overview = (TextView) findViewById(R.id.overview);
                                    assert overview != null;
                                    String overviewT = json.getString("overview");
                                    if(overviewT.equals("")){
                                        overviewT="NC";
                                    }
                                    overview.setText(overviewT);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            public void onErrorResponse(VolleyError error) {
                                Log.e("VOLLEY", error.getMessage());
                            }
                        }
                );

        //get img
        ImageView imgV = (ImageView)findViewById(R.id.imgG);
        new TacheImage(imgV).execute("http://image.tmdb.org/t/p/w185/"+img);

        queue.add(stringRequest);
    }

    private String getParam() {

        StringBuilder params = new StringBuilder()
                .append(id)
                .append("?api_key=a719a84bea8cbaa79d9e9934832b24d3")
                .append("&language=")
                .append(Locale.getDefault().toString().replace("_", "-"));
        return params.toString();
    }
}
