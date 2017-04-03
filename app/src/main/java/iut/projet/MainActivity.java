package iut.projet;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //setSpiner
        final Spinner spinner = (Spinner) findViewById(R.id.spinnerNbrMax);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.nbMax, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        assert spinner != null;
        spinner.setAdapter(adapter);

        //event
        Button button = (Button) findViewById(R.id.submit);
        assert button != null;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //get txt du choix
                EditText txtChoix = (EditText) findViewById(R.id.txtChoix);
                assert txtChoix != null;
                String choix = txtChoix.getText().toString();
                if(choix.equalsIgnoreCase("")){
                    return;
                }

                //get nb voulu
                Spinner spinnerNbMax = (Spinner)findViewById(R.id.spinnerNbrMax);
                assert spinnerNbMax != null;
                String nbS = (String) spinnerNbMax.getItemAtPosition(spinnerNbMax.getSelectedItemPosition());
                Integer nb = Integer.parseInt(nbS);

                Intent i = new Intent(MainActivity.this,ActiviteListeFilm.class);
                i.putExtra("choix",choix);
                i.putExtra("nb", nb);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) { // menu setings
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }
}
