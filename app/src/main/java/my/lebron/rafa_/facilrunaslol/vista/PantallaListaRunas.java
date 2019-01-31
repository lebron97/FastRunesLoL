package my.lebron.rafa_.facilrunaslol.vista;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

import java.io.File;
import java.util.ArrayList;

import my.lebron.rafa_.facilrunaslol.POJOs.Runas;
import my.lebron.rafa_.facilrunaslol.R;
import my.lebron.rafa_.facilrunaslol.sqlitedatabase.GestorBD;


public class PantallaListaRunas extends AppCompatActivity implements RewardedVideoAdListener {

    private ListView ltRunas;
    private Cursor c;
    private GestorBD gBD;
    private ArrayList<Runas> datosRunas;
    private ArrayAdapter<Runas> arrayAdapter;
    private Activity act = this;
    private AlertDialog.Builder builder;
    private String id_campeon;
    private CoordinatorLayout cl;
    private int aleatorio, contador = 0, aux;
    private int[] fondos = {R.drawable.fondoaleatorioprecision, R.drawable.fondoaleatoriodominacion, R.drawable.fondoaleatoriobrujeria, R.drawable.fondoaleatoriovalor, R.drawable.fondoaleatorioinspiracion};
    private AdView adView;
    private RewardedVideoAd mRewardedVideoAd;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    private EditText input;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_lista_runas);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        prefs = getSharedPreferences("CantidadRunas", Context.MODE_PRIVATE);
        editor = prefs.edit();

        contador = prefs.getInt("contador", 0);

        //Toast.makeText(this, contador + "", Toast.LENGTH_SHORT).show();

        new creaCarpeta().execute();

        adView = findViewById(R.id.adView2);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        datosRunas = new ArrayList<>();
        gBD = new GestorBD(getBaseContext());
        id_campeon = getIntent().getExtras().getString("id_campeon");

        input = new EditText(getBaseContext());
        cl = findViewById(R.id.cl);

        aleatorio = getIntent().getExtras().getInt("aleatorio");

        cl.setBackgroundResource(fondos[aleatorio]);
        c = gBD.cargarCursorRunaCampeon(id_campeon);

        ltRunas = findViewById(R.id.ltRunas);

        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
        mRewardedVideoAd.setRewardedVideoAdListener(this);


        loadRewardedVideoAd();

        ltRunas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, final long id) {
                Intent i = new Intent(view.getContext(), RunasCreadas.class);
                i.putExtra("id_runas", datosRunas.get(position).getId_runas() + "");
                startActivity(i);

            }

        });

        FloatingActionButton btnAddPagRunas = findViewById(R.id.btnAddPagRunas);
        btnAddPagRunas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                contador++;

                editor.putInt("contador", contador);
                editor.commit();

                if (contador >= 6) {
                    if (mRewardedVideoAd.isLoaded()) {
                        mRewardedVideoAd.show();
                    }

                }

                //Toast.makeText(getBaseContext(), contador + "", Toast.LENGTH_SHORT).show();

                else {
                    final LinearLayout layout = new LinearLayout(act);
                    layout.setOrientation(LinearLayout.VERTICAL);

                    builder = new AlertDialog.Builder(act);
                    builder.setMessage("Insertar nueva pagina de Runas");

                    builder.setPositiveButton("Insertar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            gBD.insertarRuna(id_campeon, input.getText().toString());

                            arrayAdapter.clear();
                            c = gBD.cargarCursorRunaCampeon(id_campeon);
                            cargaRunas();

                            Intent i = new Intent(act, RamaDeRunas.class);
                            i.putExtra("id_runas", datosRunas.get(datosRunas.size() - 1).getId_runas() + "");
                            startActivity(i);

                            input.setText("");

                            layout.removeAllViews();

                        }
                    });

                    builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            input.setText("");

                            layout.removeAllViews();
                            dialog.cancel();
                        }
                    });


                    if (input.getParent() != null)
                        ((ViewGroup) input.getParent()).removeAllViews();
                    input.setTextColor(Color.BLACK);
                    input.setHintTextColor(Color.GRAY);
                    input.setHint("Nombre");
                    layout.addView(input);


                    builder.setView(layout);
                    AlertDialog ventanita = builder.create();
                    ventanita.setCancelable(false);
                    ventanita.show();
                }
            }
        });



        ltRunas.setLongClickable(true);

        ltRunas.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           final int pos, long id) {

                builder = new AlertDialog.Builder(act);
                builder.setMessage("Borrar Página de Runas");

                builder.setPositiveButton("Borrar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        borrarPaginaDeRunas(datosRunas.get(pos).getId_runas() + "");

                        arrayAdapter.clear();
                        c = gBD.cargarCursorRunaCampeon(id_campeon);
                        cargaRunas();

                    }
                });

                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.cancel();
                    }
                });

                AlertDialog ventanita = builder.create();
                ventanita.setCancelable(false);
                ventanita.show();

                return true;
            }
        });


        datosRunas = new ArrayList<>();

        new carga().execute();

    }

    public void rellenaRunas(Cursor c) {
        datosRunas.add(new Runas(c.getInt(0), c.getInt(1), c.getString(2)));
    }

    public void cargaRunas() {

        if (c.moveToFirst()) {
            do {
                rellenaRunas(c);
            } while (c.moveToNext());
        }

        arrayAdapter = new ArrayAdapter<>(
                getBaseContext(),
                R.layout.listrow, R.id.textView2,
                datosRunas);

        ltRunas.setAdapter(arrayAdapter);

        c.close();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(getBaseContext(), PantallaCampeon.class);
        startActivity(i);
        finish();
    }

    private class carga extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            cargaRunas();

            return null;
        }
    }

    private void loadRewardedVideoAd() {
        mRewardedVideoAd.loadAd(getString(R.string.id_publicidad_video),
                new AdRequest.Builder().build());
    }

    @Override
    public void onRewarded(RewardItem reward) {
        //Toast.makeText(this, "onRewarded! currency: " + reward.getType() + "  amount: " +
                //reward.getAmount(), Toast.LENGTH_SHORT).show();
        // Reward the user.

        contador = 0;
        editor.putInt("contador", contador);
        editor.commit();

        final LinearLayout layout = new LinearLayout(act);
        layout.setOrientation(LinearLayout.VERTICAL);

        builder = new AlertDialog.Builder(act);
        builder.setMessage("Insertar nueva pagina de Runas");

        builder.setPositiveButton("Insertar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                gBD.insertarRuna(id_campeon, input.getText().toString());

                arrayAdapter.clear();
                c = gBD.cargarCursorRunaCampeon(id_campeon);
                cargaRunas();

                Intent i = new Intent(act, RamaDeRunas.class);
                i.putExtra("id_runas", datosRunas.get(datosRunas.size() - 1).getId_runas() + "");
                startActivity(i);

                input.setText("");

                layout.removeAllViews();

            }
        });

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                input.setText("");

                layout.removeAllViews();
                dialog.cancel();
            }
        });


        if (input.getParent() != null)
            ((ViewGroup) input.getParent()).removeAllViews();
        input.setTextColor(Color.BLACK);
        input.setHintTextColor(Color.GRAY);
        input.setHint("Nombre");
        layout.addView(input);


        builder.setView(layout);
        AlertDialog ventanita = builder.create();
        ventanita.setCancelable(false);
        ventanita.show();
    }

    @Override
    public void onRewardedVideoAdLeftApplication() {
        //Toast.makeText(this, "onRewardedVideoAdLeftApplication",
                //Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoAdClosed() {
        //Toast.makeText(this, "onRewardedVideoAdClosed", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(act, PantallaCampeon.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int errorCode) {
        //Toast.makeText(this, "onRewardedVideoAdFailedToLoad", Toast.LENGTH_SHORT).show();
        contador = 0;
        editor.putInt("contador", contador);
        editor.commit();
    }

    @Override
    public void onRewardedVideoAdLoaded() {
        //Toast.makeText(this, "onRewardedVideoAdLoaded", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoAdOpened() {
       // Toast.makeText(this, "onRewardedVideoAdOpened", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoStarted() {
        //Toast.makeText(this, "onRewardedVideoStarted", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoCompleted() {
       // Toast.makeText(this, "onRewardedVideoCompleted", Toast.LENGTH_SHORT).show();
    }


    public void borrarPaginaDeRunas(String id_runa) {
        //Esto debe borrar la pagina de runas con todas las runas que tenga dentro.
        gBD.eliminarRuna(id_runa);
        gBD.eliminarRunaPPorPag(id_runa);
        gBD.eliminarRunaSPorPag(id_runa);
        String mPath = Environment.getExternalStorageDirectory().toString() + "/" + "FastRunesLOL" + "/" + id_runa + ".jpg";
        File file = new File(mPath);
        /*boolean deleted =*/
        file.delete();
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        arrayAdapter.clear();
        c = gBD.cargarCursorRunaCampeon(id_campeon);
        cargaRunas();
    }

    private class creaCarpeta extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            File folder = new File(Environment.getExternalStorageDirectory(), "FastRunesLOL");
            boolean success = true;
            if (!folder.exists()) {
                folder.mkdirs();
            }
            return null;
        }
    }
}
