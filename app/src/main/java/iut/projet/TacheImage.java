package iut.projet;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by E155441H on 03/04/17.
 */
public class TacheImage extends AsyncTask<String, String, Bitmap> {

    ProgressBar progression;
    ImageView view;

    public TacheImage(ImageView view){
        this.view=view;
        this.progression = new RatingBar(view.getContext());
    }

    protected void onPreExecute() {
        progression.setVisibility(ProgressBar.VISIBLE);
    }

    protected Bitmap doInBackground(String... args) {
        try {
            if(!(args[0].equalsIgnoreCase("")||args[0].equalsIgnoreCase("null"))){
                return BitmapFactory.decodeStream((InputStream)new URL(args[0]).getContent());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void onProgressUpdate(String... args) {
        progression.setProgress(0);
    }

    protected void onPostExecute(Bitmap image) {
        progression.setVisibility(ProgressBar.INVISIBLE);
        if(image!=null){
            view.setImageBitmap(image);
        }
    }
}
