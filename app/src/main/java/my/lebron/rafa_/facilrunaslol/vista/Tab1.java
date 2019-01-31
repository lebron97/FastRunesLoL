package my.lebron.rafa_.facilrunaslol.vista;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import my.lebron.rafa_.facilrunaslol.R;


/**
 * Created by Lebron on 21/02/2018.
 */

public class Tab1 extends android.support.v4.app.Fragment {

    private View view;
    private ConstraintLayout c;
    private AdView adView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.tab1, container, false);

        c = view.findViewById(R.id.c);

        adView = view.findViewById(R.id.adView3);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        new CargaFondo().execute();

        //unbindDrawables(view);

        return view;
    }

    private class CargaFondo extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            // c.setBackgroundResource(R.drawable.fondoaleatoriodominacion);
            return null;
        }

        @Override
        protected void onPreExecute() {
            //super.onPreExecute();
            c.setBackgroundResource(R.drawable.fondoaleatorioprecision);
        }
    }

    /*private void unbindDrawables(View view)
    {
        if (view.getBackground() != null)
        {
            view.getBackground().setCallback(null);
        }
        if (view instanceof ViewGroup && !(view instanceof AdapterView))
        {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++)
            {
                unbindDrawables(((ViewGroup) view).getChildAt(i));
            }
            ((ViewGroup) view).removeAllViews();
        }
    }*/


}
