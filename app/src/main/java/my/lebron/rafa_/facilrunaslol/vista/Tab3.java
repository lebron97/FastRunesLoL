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

public class Tab3 extends android.support.v4.app.Fragment {

    private View view;
    private ConstraintLayout c;
    private AdView adView;
    //private CheckBox checkBox, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6, checkBox7, checkBox8, checkBox9, checkBox10, checkBox11, checkBox12;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.tab3, container, false);

        c = view.findViewById(R.id.c);

        adView = view.findViewById(R.id.adView5);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        new CargaFondo().execute();

        /*checkBox = view.findViewById(R.id.checkBox);
        checkBox2 = view.findViewById(R.id.checkBox2);
        checkBox3 = view.findViewById(R.id.checkBox3);
        checkBox4 = view.findViewById(R.id.checkBox4);
        checkBox5 = view.findViewById(R.id.checkBox5);
        checkBox6 = view.findViewById(R.id.checkBox6);
        checkBox7 = view.findViewById(R.id.checkBox7);
        checkBox8 = view.findViewById(R.id.checkBox8);
        checkBox9 = view.findViewById(R.id.checkBox9);
        checkBox10 = view.findViewById(R.id.checkBox10);
        checkBox11 = view.findViewById(R.id.checkBox11);
        checkBox12 = view.findViewById(R.id.checkBox12);*/

        /*checkBox.setBackgroundResource(R.drawable.bba);
        checkBox2.setBackgroundResource(R.drawable.bbb);
        checkBox3.setBackgroundResource(R.drawable.bbc);
        checkBox4.setBackgroundResource(R.drawable.ba);
        checkBox5.setBackgroundResource(R.drawable.bb);
        checkBox6.setBackgroundResource(R.drawable.bc);
        checkBox7.setBackgroundResource(R.drawable.bd);
        checkBox8.setBackgroundResource(R.drawable.be);
        checkBox9.setBackgroundResource(R.drawable.bf);
        checkBox10.setBackgroundResource(R.drawable.bg);
        checkBox11.setBackgroundResource(R.drawable.bh);
        checkBox12.setBackgroundResource(R.drawable.bi);*/

        return view;
    }
    private class CargaFondo extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            //c.setBackgroundResource(R.drawable.fondoaleatoriobrujeria);
            return null;
        }

        @Override
        protected void onPreExecute() {
            //super.onPreExecute();
            c.setBackgroundResource(R.drawable.fondoaleatoriobrujeria);
        }
    }

}
