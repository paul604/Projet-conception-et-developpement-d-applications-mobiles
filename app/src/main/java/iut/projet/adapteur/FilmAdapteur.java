package iut.projet.adapteur;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import iut.projet.Film;
import iut.projet.R;

/**
 * Created by E155441H on 30/03/17.
 */
public class FilmAdapteur extends ArrayAdapter<Film> {

    public FilmAdapteur(Activity context, ArrayList<Film> items) {
        super(context, R.layout.layout_activite_liste_film, items);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View row=convertView;
        ViewHolder viewHolder;
        if (row ==null) {
            Activity context = (Activity) getContext();
            LayoutInflater inflater = context.getLayoutInflater();
            row=inflater.inflate(R.layout.row, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.title = (TextView) row.findViewById(R.id.title);
            viewHolder.desc = (TextView) row.findViewById(R.id.desc);
            row.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder)row.getTag();
        }
        viewHolder.title.setText(getItem(position).gettitle());
        viewHolder.desc.setText(getItem(position).getNaiss());
        return(row);
    }

    static class ViewHolder {
        public TextView title;
        public TextView desc;
    }
}
