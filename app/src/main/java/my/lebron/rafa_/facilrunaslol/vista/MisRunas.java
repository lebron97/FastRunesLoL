package my.lebron.rafa_.facilrunaslol.vista;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

import my.lebron.rafa_.facilrunaslol.POJOs.RunasPrincipales;
import my.lebron.rafa_.facilrunaslol.POJOs.RunasSecundarias;
import my.lebron.rafa_.facilrunaslol.R;
import my.lebron.rafa_.facilrunaslol.sqlitedatabase.GestorBD;


public class MisRunas extends AppCompatActivity {

    private ImageView iv, iv2, iv3, iv4, iv5, iv6, iv7, iv8, iv9, iv10, iv11, iv12, iv13, iv14, iv15, iv16, iv17, iv18, iv19, iv20, iv21;
    private Cursor c, cS;
    private GestorBD gBD;
    private String id_runas;
    private int tipo_runa, tipo_runa_secundaria;
    private ArrayList<RunasPrincipales> datosRunas;
    private ArrayList<RunasSecundarias> datosRunasS;
    private RunasCreadas rc;
    private Activity act = this;
    private View v;
    private PantallaListaRunas plr = new PantallaListaRunas();
    InterstitialAd interstitialAd; // Publicidad Pantalla Completa


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_runas);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        gBD = new GestorBD(getBaseContext());
        rc = new RunasCreadas();



        v = this.findViewById(android.R.id.content);

        Snackbar.make(v, "Toca dos veces para guardar", Snackbar.LENGTH_SHORT)
                .addCallback(new Snackbar.Callback() {
                    @Override
                    public void onDismissed(Snackbar snackbar, int event) {
                        super.onDismissed(snackbar, event);

                        Snackbar.make(v, "Pulsa atrás para cancelar Runas", Snackbar.LENGTH_SHORT)
                                .addCallback(new Snackbar.Callback() {
                                    @Override
                                    public void onDismissed(Snackbar snackbar, int event) {
                                        super.onDismissed(snackbar, event);
                                        //ESTO ES EL EVENTO DEL DOUBLE TAP
                                        //final Context context = this;
                                        final GestureDetector.SimpleOnGestureListener listener = new GestureDetector.SimpleOnGestureListener() {
                                            @Override
                                            public boolean onDoubleTap(MotionEvent e) {
                                                takeScreenshot();

                                                interstitialAd = new InterstitialAd(getBaseContext());
                                                interstitialAd.setAdUnitId(getString(R.string.id_publicidad_full));
                                                AdRequest adRequest1 = new AdRequest.Builder().build();
                                                interstitialAd.loadAd(adRequest1);
                                                interstitialAd.setAdListener(new AdListener() {
                                                    @Override
                                                    public void onAdLoaded() {
                                                        super.onAdLoaded();
                                                        interstitialAd.show();
                                                    }
                                                });

                                                Intent i = new Intent(getBaseContext(), PantallaCampeon.class);
                                                startActivity(i);
                                                finish();
                                                return true;
                                            }

                                            /* @Override
                                               public void onLongPress(MotionEvent e) {
                                               Toast.makeText(context, "onLongPress", Toast.LENGTH_SHORT).show();
            }*/
                                        };

                                        final GestureDetector detector = new GestureDetector(getBaseContext(), listener);

                                        detector.setOnDoubleTapListener(listener);
                                        detector.setIsLongpressEnabled(true);

                                        getWindow().getDecorView().setOnTouchListener(new View.OnTouchListener() {
                                            @Override
                                            public boolean onTouch(View view, MotionEvent event) {
                                                return detector.onTouchEvent(event);
                                            }
                                        });

                                    }
                                }).show();
                    }
                }).show();


        tipo_runa = getIntent().getExtras().getInt("tipo_runa");
        tipo_runa_secundaria = getIntent().getExtras().getInt("tipo_runa_secundaria");

        datosRunas = new ArrayList<>();
        datosRunasS = new ArrayList<>();

        id_runas = getIntent().getExtras().getString("id_runas");

        iv = findViewById(R.id.imageView);
        iv2 = findViewById(R.id.imageView2);
        iv3 = findViewById(R.id.imageView3);
        iv4 = findViewById(R.id.imageView4);
        iv5 = findViewById(R.id.imageView5);
        iv6 = findViewById(R.id.imageView6);
        iv7 = findViewById(R.id.imageView7);
        iv8 = findViewById(R.id.imageView8);
        iv9 = findViewById(R.id.imageView9);
        iv10 = findViewById(R.id.imageView10);
        iv11 = findViewById(R.id.imageView11);
        iv12 = findViewById(R.id.imageView12);
        iv13 = findViewById(R.id.imageView13);
        iv14 = findViewById(R.id.imageView14);
        iv15 = findViewById(R.id.imageView15);
        iv16 = findViewById(R.id.imageView16);
        iv17 = findViewById(R.id.imageView17);
        iv18 = findViewById(R.id.imageView18);
        iv19 = findViewById(R.id.imageView19);
        iv20 = findViewById(R.id.imageView20);
        iv21 = findViewById(R.id.imageView21);


        c = gBD.cargarCursorRunaPPagRunas(id_runas);
        cS = gBD.cargarCursorRunaSPagRunas(id_runas);

        cargaMisRunas();
        cargaMisRunasS();

        colocacionDeRunas();
        colocacionDeRunasS();

        //Toast t = Toast.makeText(getBaseContext(), tipo_runa + "", Toast.LENGTH_SHORT);
        //t.show();
    }

    public void rellenaMisRunas(Cursor c) {
        datosRunas.add(new RunasPrincipales(c.getString(0), c.getInt(1), c.getInt(2)));
    }

    public void cargaMisRunas() {

        if (c.moveToFirst()) {
            do {
                rellenaMisRunas(c);
            } while (c.moveToNext());
        }

        c.close();

    }

    public void rellenaMisRunasS(Cursor c) {
        datosRunasS.add(new RunasSecundarias(c.getString(0), c.getInt(1), c.getInt(2)));
    }

    public void cargaMisRunasS() {

        if (cS.moveToFirst()) {
            do {
                rellenaMisRunasS(cS);
            } while (cS.moveToNext());
        }

        c.close();

    }

    public void colocacionDeRunas() {

        for (int i = 0; i < datosRunas.size(); i++) {
            if (datosRunas.get(i).getPosicion() == 1) {
                if (tipo_runa == 1)
                    iv.setBackgroundResource(R.drawable.pa);
                else if (tipo_runa == 2)
                    iv.setBackgroundResource(R.drawable.da);
                else if (tipo_runa == 3)
                    iv.setBackgroundResource(R.drawable.bba);
                else if (tipo_runa == 4)
                    iv.setBackgroundResource(R.drawable.vva);
                else if (tipo_runa == 5)
                    iv.setBackgroundResource(R.drawable.iia);
            }
            if (datosRunas.get(i).getPosicion() == 2) {
                if (tipo_runa == 1)
                    iv2.setBackgroundResource(R.drawable.pb);
                else if (tipo_runa == 2)
                    iv2.setBackgroundResource(R.drawable.db);
                else if (tipo_runa == 3)
                    iv2.setBackgroundResource(R.drawable.bbb);
                else if (tipo_runa == 4)
                    iv2.setBackgroundResource(R.drawable.vvb);
                else if (tipo_runa == 5)
                    iv2.setBackgroundResource(R.drawable.iib);
            }
            if (datosRunas.get(i).getPosicion() == 3) {
                if (tipo_runa == 1)
                    iv3.setBackgroundResource(R.drawable.pc);
                else if (tipo_runa == 2)
                    iv3.setBackgroundResource(R.drawable.dc);
                else if (tipo_runa == 3)
                    iv3.setBackgroundResource(R.drawable.bbc);
                else if (tipo_runa == 4)
                    iv3.setBackgroundResource(R.drawable.vvc);
                else if (tipo_runa == 5)
                    iv3.setBackgroundResource(R.drawable.iic);
            }
            if (datosRunas.get(i).getPosicion() == 4) {
                if (tipo_runa == 1)
                    iv4.setBackgroundResource(R.drawable.pd);
                else if (tipo_runa == 2)
                    iv4.setBackgroundResource(R.drawable.dd);
                else if (tipo_runa == 3)
                    iv4.setBackgroundResource(R.drawable.ba);
                else if (tipo_runa == 4)
                    iv4.setBackgroundResource(R.drawable.va);
                else if (tipo_runa == 5)
                    iv4.setBackgroundResource(R.drawable.ia);
            }
            if (datosRunas.get(i).getPosicion() == 5) {
                if (tipo_runa == 1)
                    iv5.setBackgroundResource(R.drawable.pe);
                else if (tipo_runa == 2)
                    iv5.setBackgroundResource(R.drawable.de);
                else if (tipo_runa == 3)
                    iv5.setBackgroundResource(R.drawable.bb);
                else if (tipo_runa == 4)
                    iv5.setBackgroundResource(R.drawable.vb);
                else if (tipo_runa == 5)
                    iv5.setBackgroundResource(R.drawable.ib);
            }
            if (datosRunas.get(i).getPosicion() == 6) {
                if (tipo_runa == 1)
                    iv6.setBackgroundResource(R.drawable.pf);
                else if (tipo_runa == 2)
                    iv6.setBackgroundResource(R.drawable.df);
                else if (tipo_runa == 3)
                    iv6.setBackgroundResource(R.drawable.bc);
                else if (tipo_runa == 4)
                    iv6.setBackgroundResource(R.drawable.vc);
                else if (tipo_runa == 5)
                    iv6.setBackgroundResource(R.drawable.ic);
            }
            if (datosRunas.get(i).getPosicion() == 7) {
                if (tipo_runa == 1)
                    iv7.setBackgroundResource(R.drawable.pg);
                else if (tipo_runa == 2)
                    iv7.setBackgroundResource(R.drawable.dg);
                else if (tipo_runa == 3)
                    iv7.setBackgroundResource(R.drawable.bd);
                else if (tipo_runa == 4)
                    iv7.setBackgroundResource(R.drawable.vd);
                else if (tipo_runa == 5)
                    iv7.setBackgroundResource(R.drawable.id);
            }
            if (datosRunas.get(i).getPosicion() == 13) {
                iv3.setBackgroundResource(R.drawable.ppd);
            }
            if (datosRunas.get(i).getPosicion() == 8) {
                if (tipo_runa == 1)
                    iv8.setBackgroundResource(R.drawable.ph);
                else if (tipo_runa == 2)
                    iv8.setBackgroundResource(R.drawable.dh);
                else if (tipo_runa == 3)
                    iv8.setBackgroundResource(R.drawable.be);
                else if (tipo_runa == 4)
                    iv8.setBackgroundResource(R.drawable.ve);
                else if (tipo_runa == 5)
                    iv8.setBackgroundResource(R.drawable.ie);
            }
            if (datosRunas.get(i).getPosicion() == 9) {
                if (tipo_runa == 1)
                    iv9.setBackgroundResource(R.drawable.pi);
                else if (tipo_runa == 2)
                    iv9.setBackgroundResource(R.drawable.di);
                else if (tipo_runa == 3)
                    iv9.setBackgroundResource(R.drawable.bf);
                else if (tipo_runa == 4)
                    iv9.setBackgroundResource(R.drawable.vg);
                else if (tipo_runa == 5)
                    iv9.setBackgroundResource(R.drawable.ifi);
            }
            if (datosRunas.get(i).getPosicion() == 10) {

                if (tipo_runa == 1)
                    iv10.setBackgroundResource(R.drawable.pj);
                else if (tipo_runa == 2)
                    iv10.setBackgroundResource(R.drawable.dj);
                else if (tipo_runa == 3)
                    iv10.setBackgroundResource(R.drawable.bg);
                else if (tipo_runa == 4)
                    iv10.setBackgroundResource(R.drawable.vh);
                else if (tipo_runa == 5)
                    iv10.setBackgroundResource(R.drawable.ig);
            }
            if (datosRunas.get(i).getPosicion() == 11) {
                if (tipo_runa == 1)
                    iv11.setBackgroundResource(R.drawable.pk);
                else if (tipo_runa == 2)
                    iv11.setBackgroundResource(R.drawable.dk);
                else if (tipo_runa == 3)
                    iv11.setBackgroundResource(R.drawable.bh);
                else if (tipo_runa == 4)
                    iv11.setBackgroundResource(R.drawable.vi);
                else if (tipo_runa == 5)
                    iv11.setBackgroundResource(R.drawable.ih);
            }
            if (datosRunas.get(i).getPosicion() == 12) {
                if (tipo_runa == 1)
                    iv12.setBackgroundResource(R.drawable.pl);
                else if (tipo_runa == 2)
                    iv12.setBackgroundResource(R.drawable.dl);
                else if (tipo_runa == 3)
                    iv12.setBackgroundResource(R.drawable.bi);
                else if (tipo_runa == 4)
                    iv12.setBackgroundResource(R.drawable.vj);
                else if (tipo_runa == 5)
                    iv12.setBackgroundResource(R.drawable.ii);
            }

        }
    }

    public void colocacionDeRunasS() {

        for (int i = 0; i < datosRunasS.size(); i++) {

            if (datosRunasS.get(i).getPosicion() == 4) {
                if (tipo_runa_secundaria == 1)
                    iv13.setBackgroundResource(R.drawable.pd);
                else if (tipo_runa_secundaria == 2)
                    iv13.setBackgroundResource(R.drawable.dd);
                else if (tipo_runa_secundaria == 3)
                    iv13.setBackgroundResource(R.drawable.ba);
                else if (tipo_runa_secundaria == 4)
                    iv13.setBackgroundResource(R.drawable.va);
                else if (tipo_runa_secundaria == 5)
                    iv13.setBackgroundResource(R.drawable.ia);
            }
            if (datosRunasS.get(i).getPosicion() == 5) {
                if (tipo_runa_secundaria == 1)
                    iv14.setBackgroundResource(R.drawable.pe);
                else if (tipo_runa_secundaria == 2)
                    iv14.setBackgroundResource(R.drawable.de);
                else if (tipo_runa_secundaria == 3)
                    iv14.setBackgroundResource(R.drawable.bb);
                else if (tipo_runa_secundaria == 4)
                    iv14.setBackgroundResource(R.drawable.vb);
                else if (tipo_runa_secundaria == 5)
                    iv14.setBackgroundResource(R.drawable.ib);
            }
            if (datosRunasS.get(i).getPosicion() == 6) {
                if (tipo_runa_secundaria == 1)
                    iv15.setBackgroundResource(R.drawable.pf);
                else if (tipo_runa_secundaria == 2)
                    iv15.setBackgroundResource(R.drawable.df);
                else if (tipo_runa_secundaria == 3)
                    iv15.setBackgroundResource(R.drawable.bc);
                else if (tipo_runa_secundaria == 4)
                    iv15.setBackgroundResource(R.drawable.vc);
                else if (tipo_runa_secundaria == 5)
                    iv15.setBackgroundResource(R.drawable.ic);
            }
            if (datosRunasS.get(i).getPosicion() == 7) {
                if (tipo_runa_secundaria == 1)
                    iv16.setBackgroundResource(R.drawable.pg);
                else if (tipo_runa_secundaria == 2)
                    iv16.setBackgroundResource(R.drawable.dg);
                else if (tipo_runa_secundaria == 3)
                    iv16.setBackgroundResource(R.drawable.bd);
                else if (tipo_runa_secundaria == 4)
                    iv16.setBackgroundResource(R.drawable.vd);
                else if (tipo_runa_secundaria == 5)
                    iv16.setBackgroundResource(R.drawable.id);
            }
            /*if (datosRunasS.get(i).getPosicion() == 13) {
                iv17.setBackgroundResource(R.drawable.ve);
            }*/
            if (datosRunasS.get(i).getPosicion() == 8) {
                if (tipo_runa_secundaria == 1)
                    iv17.setBackgroundResource(R.drawable.ph);
                else if (tipo_runa_secundaria == 2)
                    iv17.setBackgroundResource(R.drawable.dh);
                else if (tipo_runa_secundaria == 3)
                    iv17.setBackgroundResource(R.drawable.be);
                else if (tipo_runa_secundaria == 4)
                    iv17.setBackgroundResource(R.drawable.ve);
                else if (tipo_runa_secundaria == 5)
                    iv17.setBackgroundResource(R.drawable.ie);
            }
            if (datosRunasS.get(i).getPosicion() == 9) {
                if (tipo_runa_secundaria == 1)
                    iv18.setBackgroundResource(R.drawable.pi);
                else if (tipo_runa_secundaria == 2)
                    iv18.setBackgroundResource(R.drawable.di);
                else if (tipo_runa_secundaria == 3)
                    iv18.setBackgroundResource(R.drawable.bf);
                else if (tipo_runa_secundaria == 4)
                    iv18.setBackgroundResource(R.drawable.vg);
                else if (tipo_runa_secundaria == 5)
                    iv18.setBackgroundResource(R.drawable.ifi);
            }
            if (datosRunasS.get(i).getPosicion() == 10) {

                if (tipo_runa_secundaria == 1)
                    iv19.setBackgroundResource(R.drawable.pj);
                else if (tipo_runa_secundaria == 2)
                    iv19.setBackgroundResource(R.drawable.dj);
                else if (tipo_runa_secundaria == 3)
                    iv19.setBackgroundResource(R.drawable.bg);
                else if (tipo_runa_secundaria == 4)
                    iv19.setBackgroundResource(R.drawable.vh);
                else if (tipo_runa_secundaria == 5)
                    iv19.setBackgroundResource(R.drawable.ig);
            }
            if (datosRunasS.get(i).getPosicion() == 11) {
                if (tipo_runa_secundaria == 1)
                    iv20.setBackgroundResource(R.drawable.pk);
                else if (tipo_runa_secundaria == 2)
                    iv20.setBackgroundResource(R.drawable.dk);
                else if (tipo_runa_secundaria == 3)
                    iv20.setBackgroundResource(R.drawable.bh);
                else if (tipo_runa_secundaria == 4)
                    iv20.setBackgroundResource(R.drawable.vi);
                else if (tipo_runa_secundaria == 5)
                    iv20.setBackgroundResource(R.drawable.ih);
            }
            if (datosRunasS.get(i).getPosicion() == 12) {
                if (tipo_runa_secundaria == 1)
                    iv21.setBackgroundResource(R.drawable.pl);
                else if (tipo_runa_secundaria == 2)
                    iv21.setBackgroundResource(R.drawable.dl);
                else if (tipo_runa_secundaria == 3)
                    iv21.setBackgroundResource(R.drawable.bi);
                else if (tipo_runa_secundaria == 4)
                    iv21.setBackgroundResource(R.drawable.vj);
                else if (tipo_runa_secundaria == 5)
                    iv21.setBackgroundResource(R.drawable.ii);
            }


        }
    }


    //METODO QUE TOMA LA CAPTURA DE PANTALLA Y LO GUARDA EN LA CARPETA DE APLICACION
    private void takeScreenshot() {
        //Date now = new Date();
        //android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", now);

        try {
            // image naming and path  to include sd card  appending name you choose for file
            String mPath = Environment.getExternalStorageDirectory().toString() + "/" + "FastRunesLOL" + "/" + id_runas + ".jpg";

            // create bitmap screen capture
            View v1 = getWindow().getDecorView().getRootView();
            v1.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(v1.getDrawingCache());
            v1.setDrawingCacheEnabled(false);

            File imageFile = new File(mPath);

            FileOutputStream outputStream = new FileOutputStream(imageFile);
            int quality = 100;
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
            outputStream.flush();
            outputStream.close();

            //rc.openScreenshot(imageFile);
        } catch (Throwable e) {
            // Several error may come out with file handling or DOM
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();

        interstitialAd = new InterstitialAd(getBaseContext());
        interstitialAd.setAdUnitId(getString(R.string.id_publicidad_full));
        AdRequest adRequest1 = new AdRequest.Builder().build();
        interstitialAd.loadAd(adRequest1);
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                interstitialAd.show();
            }
        });

        gBD.eliminarRuna(id_runas);
        gBD.eliminarRunaPPorPag(id_runas);
        gBD.eliminarRunaSPorPag(id_runas);
        Intent i = new Intent(getBaseContext(), PantallaCampeon.class);
        startActivity(i);
        finish();

    }

    @Override
    protected void onRestart() {
        super.onRestart();

        gBD.eliminarRuna(id_runas);
        gBD.eliminarRunaPPorPag(id_runas);
        gBD.eliminarRunaSPorPag(id_runas);
        Intent i = new Intent(getBaseContext(), PantallaCampeon.class);
        startActivity(i);
        finish();

    }
}
